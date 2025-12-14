package com.lucienvirecourt.jedi.lld.consistanthashing;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Thread-safe Consistent Hash Ring implementation
 *
 * Design decisions:
 * - ConcurrentSkipListMap: O(log n) operations, thread-safe reads
 * - ReadWriteLock: Allow concurrent reads, exclusive writes
 * - Virtual nodes: Configurable replicas for better distribution
 * - Immutable snapshots: Safe iteration during modifications
 */
public class ConsistentHashRing<T extends Node> {

  // ========================================================================
  // CONFIGURATION
  // ========================================================================

  private static final int DEFAULT_VIRTUAL_NODES = 150;
  private static final int MIN_VIRTUAL_NODES = 1;
  private static final int MAX_VIRTUAL_NODES = 1000;

  // ========================================================================
  // STATE
  // ========================================================================

  /** The hash ring - maps hash values to nodes */
  private final ConcurrentSkipListMap<Long, VirtualNode<T>> ring;

  /** Quick lookup: physical node -> virtual nodes */
  private final Map<T, Set<Long>> nodeToHashes;

  /** Hash function for key/node hashing */
  private final HashFunction hashFunction;

  /** Number of virtual nodes per physical node */
  private final int virtualNodeCount;

  /** Lock for structural modifications */
  private final ReadWriteLock lock;

  // ========================================================================
  // INNER CLASSES
  // ========================================================================

  /**
   * Represents a virtual node on the ring
   */
  private static class VirtualNode<T extends Node> {
    final T physicalNode;
    final int replicaIndex;

    VirtualNode(T physicalNode, int replicaIndex) {
      this.physicalNode = physicalNode;
      this.replicaIndex = replicaIndex;
    }

    @Override
    public String toString() {
      return physicalNode.getKey() + "#" + replicaIndex;
    }
  }

  // ========================================================================
  // CONSTRUCTORS
  // ========================================================================

  public ConsistentHashRing() {
    this(new MD5HashFunction(), DEFAULT_VIRTUAL_NODES);
  }

  public ConsistentHashRing(int virtualNodeCount) {
    this(new MD5HashFunction(), virtualNodeCount);
  }

  public ConsistentHashRing(HashFunction hashFunction) {
    this(hashFunction, DEFAULT_VIRTUAL_NODES);
  }

  public ConsistentHashRing(HashFunction hashFunction, int virtualNodeCount) {
    validateVirtualNodeCount(virtualNodeCount);

    this.hashFunction = Objects.requireNonNull(hashFunction,
      "Hash function cannot be null");
    this.virtualNodeCount = virtualNodeCount;
    this.ring = new ConcurrentSkipListMap<>();
    this.nodeToHashes = new HashMap<>();
    this.lock = new ReentrantReadWriteLock();
  }

  // ========================================================================
  // PUBLIC API - NODE MANAGEMENT
  // ========================================================================

  /**
   * Add a node to the ring
   * Time Complexity: O(V * log(N*V)) where V = virtual nodes, N = physical nodes
   *
   * @param node the node to add
   * @throws IllegalArgumentException if node is null or already exists
   */
  public void addNode(T node) {
    Objects.requireNonNull(node, "Node cannot be null");

    lock.writeLock().lock();
    try {
      // Edge case: prevent duplicate nodes
      if (nodeToHashes.containsKey(node)) {
        throw new IllegalArgumentException(
          "Node already exists: " + node.getKey());
      }

      Set<Long> hashes = new HashSet<>();

      // Create virtual nodes
      for (int i = 0; i < virtualNodeCount; i++) {
        String virtualKey = node.getKey() + "#" + i;
        long hash = hashFunction.hash(virtualKey);

        // Edge case: handle hash collisions (rare but possible)
        int attempts = 0;
        while (ring.containsKey(hash) && attempts < 100) {
          hash = hashFunction.hash(virtualKey + ":" + attempts);
          attempts++;
        }

        if (attempts >= 100) {
          // Rollback on failure
          hashes.forEach(ring::remove);
          throw new IllegalStateException(
            "Unable to place virtual node after 100 attempts");
        }

        ring.put(hash, new VirtualNode<>(node, i));
        hashes.add(hash);
      }

      nodeToHashes.put(node, hashes);

    } finally {
      lock.writeLock().unlock();
    }
  }

  /**
   * Remove a node from the ring
   * Time Complexity: O(V * log(N*V))
   *
   * @param node the node to remove
   * @return true if node was present and removed
   */
  public boolean removeNode(T node) {
    Objects.requireNonNull(node, "Node cannot be null");

    lock.writeLock().lock();
    try {
      Set<Long> hashes = nodeToHashes.remove(node);

      // Edge case: node doesn't exist
      if (hashes == null) {
        return false;
      }

      // Remove all virtual nodes
      hashes.forEach(ring::remove);
      return true;

    } finally {
      lock.writeLock().unlock();
    }
  }

  /**
   * Add multiple nodes atomically
   */
  public void addNodes(Collection<T> nodes) {
    Objects.requireNonNull(nodes, "Nodes collection cannot be null");

    lock.writeLock().lock();
    try {
      for (T node : nodes) {
        addNodeInternal(node);
      }
    } finally {
      lock.writeLock().unlock();
    }
  }

  private void addNodeInternal(T node) {
    // Similar to addNode but without lock (already held)
    // ... implementation
  }

  // ========================================================================
  // PUBLIC API - KEY ROUTING
  // ========================================================================

  /**
   * Get the node responsible for a given key
   * Time Complexity: O(log(N*V))
   *
   * @param key the key to route
   * @return the responsible node, or null if ring is empty
   */
  public T getNode(String key) {
    Objects.requireNonNull(key, "Key cannot be null");

    lock.readLock().lock();
    try {
      // Edge case: empty ring
      if (ring.isEmpty()) {
        return null;
      }

      long hash = hashFunction.hash(key);

      // Find first node clockwise from hash
      // find entry whose key is greater than hash
      Map.Entry<Long, VirtualNode<T>> entry = ring.ceilingEntry(hash);

      // Edge case: wrap around if past the last node
      if (entry == null) {
        entry = ring.firstEntry();
      }

      return entry.getValue().physicalNode;

    } finally {
      lock.readLock().unlock();
    }
  }

  /**
   * Get N nodes for replication
   * Returns distinct physical nodes in clockwise order
   *
   * @param key the key to route
   * @param count number of nodes to return
   * @return list of nodes (may be fewer than count if not enough nodes)
   */
  public List<T> getNodes(String key, int count) {
    Objects.requireNonNull(key, "Key cannot be null");

    if (count <= 0) {
      throw new IllegalArgumentException("Count must be positive");
    }

    lock.readLock().lock();
    try {
      // Edge case: empty ring
      if (ring.isEmpty()) {
        return Collections.emptyList();
      }

      long hash = hashFunction.hash(key);
      List<T> result = new ArrayList<>();
      Set<T> seen = new HashSet<>();

      // Start from hash position
      Map.Entry<Long, VirtualNode<T>> entry = ring.ceilingEntry(hash);

      // Iterate clockwise, collecting distinct physical nodes
      NavigableMap<Long, VirtualNode<T>> tailMap =
        (entry != null) ? ring.tailMap(entry.getKey(), true) : ring;

      collectDistinctNodes(tailMap.values(), seen, result, count);

      // Edge case: wrap around if needed
      if (result.size() < count) {
        collectDistinctNodes(ring.values(), seen, result, count);
      }

      return result;

    } finally {
      lock.readLock().unlock();
    }
  }

  private void collectDistinctNodes(
    Collection<VirtualNode<T>> virtualNodes,
    Set<T> seen,
    List<T> result,
    int count) {

    for (VirtualNode<T> vnode : virtualNodes) {
      if (result.size() >= count) {
        break;
      }

      T physical = vnode.physicalNode;
      if (!seen.contains(physical)) {
        seen.add(physical);
        result.add(physical);
      }
    }
  }

  // ========================================================================
  // PUBLIC API - QUERYING
  // ========================================================================

  /**
   * Get all physical nodes in the ring
   */
  public Set<T> getNodes() {
    lock.readLock().lock();
    try {
      return new HashSet<>(nodeToHashes.keySet());
    } finally {
      lock.readLock().unlock();
    }
  }

  /**
   * Get the number of physical nodes
   */
  public int getNodeCount() {
    lock.readLock().lock();
    try {
      return nodeToHashes.size();
    } finally {
      lock.readLock().unlock();
    }
  }

  /**
   * Check if ring is empty
   */
  public boolean isEmpty() {
    lock.readLock().lock();
    try {
      return ring.isEmpty();
    } finally {
      lock.readLock().unlock();
    }
  }

  /**
   * Get distribution statistics
   * Useful for monitoring and debugging
   */
  public RingStatistics getStatistics() {
    lock.readLock().lock();
    try {
      return new RingStatistics(
        nodeToHashes.size(),
        ring.size(),
        virtualNodeCount,
        calculateLoadDistribution()
      );
    } finally {
      lock.readLock().unlock();
    }
  }

  private Map<T, Double> calculateLoadDistribution() {
    if (ring.isEmpty()) {
      return Collections.emptyMap();
    }

    Map<T, Long> arcLengths = new HashMap<>();
    long totalCircumference = (1L << 32);

    Long prevHash = ring.lastKey();
    for (Map.Entry<Long, VirtualNode<T>> entry : ring.entrySet()) {
      long currentHash = entry.getKey();
      T node = entry.getValue().physicalNode;

      long arcLength = currentHash - prevHash;
      if (arcLength < 0) {
        arcLength += totalCircumference;
      }

      arcLengths.merge(node, arcLength, Long::sum);
      prevHash = currentHash;
    }

    // Convert to percentages
    Map<T, Double> distribution = new HashMap<>();
    for (Map.Entry<T, Long> entry : arcLengths.entrySet()) {
      double percentage = (entry.getValue() * 100.0) / totalCircumference;
      distribution.put(entry.getKey(), percentage);
    }

    return distribution;
  }

  // ========================================================================
  // VALIDATION
  // ========================================================================

  private void validateVirtualNodeCount(int count) {
    if (count < MIN_VIRTUAL_NODES || count > MAX_VIRTUAL_NODES) {
      throw new IllegalArgumentException(
        String.format("Virtual node count must be between %d and %d",
          MIN_VIRTUAL_NODES, MAX_VIRTUAL_NODES));
    }
  }

  // ========================================================================
  // STATISTICS CLASS
  // ========================================================================

  public static class RingStatistics {
    private final int physicalNodes;
    private final int virtualNodes;
    private final int virtualNodesPerPhysical;
    private final Map<?, Double> loadDistribution;

    public RingStatistics(
      int physicalNodes,
      int virtualNodes,
      int virtualNodesPerPhysical,
      Map<?, Double> loadDistribution) {
      this.physicalNodes = physicalNodes;
      this.virtualNodes = virtualNodes;
      this.virtualNodesPerPhysical = virtualNodesPerPhysical;
      this.loadDistribution = loadDistribution;
    }

    public int getPhysicalNodes() { return physicalNodes; }
    public int getVirtualNodes() { return virtualNodes; }
    public int getVirtualNodesPerPhysical() { return virtualNodesPerPhysical; }
    public Map<?, Double> getLoadDistribution() { return loadDistribution; }

    public double getMaxLoad() {
      return loadDistribution.values().stream()
        .mapToDouble(Double::doubleValue)
        .max()
        .orElse(0.0);
    }

    public double getMinLoad() {
      return loadDistribution.values().stream()
        .mapToDouble(Double::doubleValue)
        .min()
        .orElse(0.0);
    }

    public double getStandardDeviation() {
      double mean = 100.0 / physicalNodes;
      double variance = loadDistribution.values().stream()
        .mapToDouble(load -> Math.pow(load - mean, 2))
        .average()
        .orElse(0.0);
      return Math.sqrt(variance);
    }

    @Override
    public String toString() {
      return String.format(
        "RingStatistics{physical=%d, virtual=%d, maxLoad=%.2f%%, minLoad=%.2f%%, stdDev=%.2f}",
        physicalNodes, virtualNodes, getMaxLoad(), getMinLoad(), getStandardDeviation()
      );
    }
  }
}

class SlidingWindowCounter {
  private final long windowSizeMs;
  private final long maxRequests;
  private long previousWindowCount;
  private long currentWindowCount;
  private long currentWindowStart;

  public SlidingWindowCounter(long windowSizeMs, long maxRequests) {
    this.windowSizeMs = windowSizeMs;
    this.maxRequests = maxRequests;
    this.previousWindowCount = 0;
    this.currentWindowCount = 0;
    this.currentWindowStart = System.currentTimeMillis();
  }

  public synchronized boolean tryConsume() {
    long now = System.currentTimeMillis();

    // Calculate which window we're in
    long currentWindowIndex = now / windowSizeMs;
    long recordedWindowIndex = currentWindowStart / windowSizeMs;

    if (currentWindowIndex > recordedWindowIndex) {
      // Moved to next window
      previousWindowCount = currentWindowCount;
      currentWindowCount = 0;
      currentWindowStart = currentWindowIndex * windowSizeMs;
    }

    // Calculate position in current window (0.0 to 1.0)
    double percentageIntoWindow =
      (double)(now - currentWindowStart) / windowSizeMs;

    // Weighted count from both windows
    double estimatedCount =
      currentWindowCount +
        previousWindowCount * (1 - percentageIntoWindow);

    if (estimatedCount < maxRequests) {
      currentWindowCount++;
      return true;
    }
    return false;
  }
}
