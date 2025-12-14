package com.lucienvirecourt.jedi.lld.consistanthashing;

import java.util.*;

public class ConsistentHashingDemo {

  static void main() {
    demonstrateBasicUsage();
    demonstrateReplication();
    demonstrateNodeAddition();
    demonstrateLoadDistribution();
  }

  /**
   * Basic usage example
   */
  private static void demonstrateBasicUsage() {
    System.out.println("=== Basic Usage ===\n");

    // Create ring with builder
    ConsistentHashRing<SimpleNode> ring =
      new ConsistentHashRingBuilder<SimpleNode>()
        .withVirtualNodes(150)
        .addNode(new SimpleNode("server-1"))
        .addNode(new SimpleNode("server-2"))
        .addNode(new SimpleNode("server-3"))
        .build();

    // Route keys
    String[] keys = {"user:1001", "user:1002", "user:1003", "session:abc"};

    for (String key : keys) {
      Node node = ring.getNode(key);
      System.out.printf("Key '%s' -> %s%n", key, node.getKey());
    }

    System.out.println();
  }

  /**
   * Demonstrate replication
   */
  private static void demonstrateReplication() {
    System.out.println("=== Replication Example ===\n");

    ConsistentHashRing<SimpleNode> ring = createRingWithNodes(5);

    String key = "important-data";
    int replicas = 3;

    List<SimpleNode> nodes = ring.getNodes(key, replicas);

    System.out.printf("Key '%s' replicated to %d nodes:%n", key, replicas);
    for (int i = 0; i < nodes.size(); i++) {
      System.out.printf("  Replica %d: %s%n", i + 1, nodes.get(i).getKey());
    }

    System.out.println();
  }

  /**
   * Demonstrate minimal disruption when adding nodes
   */
  private static void demonstrateNodeAddition() {
    System.out.println("=== Node Addition Impact ===\n");

    ConsistentHashRing<SimpleNode> ring = createRingWithNodes(3);

    // Generate test keys
    int keyCount = 1000;
    List<String> keys = generateKeys(keyCount);

    // Map keys to nodes (before)
    Map<String, String> beforeMapping = new HashMap<>();
    for (String key : keys) {
      Node node = ring.getNode(key);
      beforeMapping.put(key, node.getKey());
    }

    // Add a new node
    ring.addNode(new SimpleNode("server-4"));

    // Map keys to nodes (after)
    int remapped = 0;
    for (String key : keys) {
      Node node = ring.getNode(key);
      if (!node.getKey().equals(beforeMapping.get(key))) {
        remapped++;
      }
    }

    double remappedPercent = (remapped * 100.0) / keyCount;
    double expectedPercent = 100.0 / 4; // 1/N for N=4 nodes

    System.out.printf("Keys remapped: %d / %d (%.2f%%)%n",
      remapped, keyCount, remappedPercent);
    System.out.printf("Expected: ~%.2f%%\n", expectedPercent);
    System.out.printf("Efficiency: %.2f%%%n",
      (expectedPercent / remappedPercent) * 100);

    System.out.println();
  }

  /**
   * Analyze load distribution
   */
  private static void demonstrateLoadDistribution() {
    System.out.println("=== Load Distribution ===\n");

    // Test with different virtual node counts
    int[] virtualNodeCounts = {10, 50, 150, 500};

    for (int vnodes : virtualNodeCounts) {
      ConsistentHashRing<SimpleNode> ring =
        new ConsistentHashRingBuilder<SimpleNode>()
          .withVirtualNodes(vnodes)
          .build();

      // Add nodes
      for (int i = 1; i <= 5; i++) {
        ring.addNode(new SimpleNode("server-" + i));
      }

      // Test with keys
      Map<String, Integer> distribution = new HashMap<>();
      int keyCount = 10000;

      for (int i = 0; i < keyCount; i++) {
        String key = "key-" + i;
        Node node = ring.getNode(key);
        distribution.merge(node.getKey(), 1, Integer::sum);
      }

      // Calculate statistics
      double mean = keyCount / 5.0;
      double maxDeviation = distribution.values().stream()
        .mapToDouble(count -> Math.abs(count - mean) / mean * 100)
        .max()
        .orElse(0.0);

      System.out.printf("Virtual nodes: %3d -> Max deviation: %.2f%%%n",
        vnodes, maxDeviation);

      // Get ring statistics
      ConsistentHashRing.RingStatistics stats = ring.getStatistics();
      System.out.printf("  %s%n", stats);
    }

    System.out.println();
  }

  // ========================================================================
  // HELPER METHODS
  // ========================================================================

  private static ConsistentHashRing<SimpleNode> createRingWithNodes(int count) {
    ConsistentHashRingBuilder<SimpleNode> builder =
      new ConsistentHashRingBuilder<>();

    for (int i = 1; i <= count; i++) {
      builder.addNode(new SimpleNode("server-" + i));
    }

    return builder.build();
  }

  private static List<String> generateKeys(int count) {
    List<String> keys = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      keys.add("key-" + i);
    }
    return keys;
  }
}
