package com.lucienvirecourt.jedi.lld.consistanthashing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Builder for ConsistentHashRing
 * Demonstrates fluent API and configuration flexibility
 */
public class ConsistentHashRingBuilder<T extends Node> {
  private HashFunction hashFunction = new MD5HashFunction();
  private int virtualNodes = 150;
  private Collection<T> initialNodes = new ArrayList<>();

  public ConsistentHashRingBuilder<T> withHashFunction(HashFunction hashFunction) {
    this.hashFunction = Objects.requireNonNull(hashFunction);
    return this;
  }

  public ConsistentHashRingBuilder<T> withVirtualNodes(int virtualNodes) {
    this.virtualNodes = virtualNodes;
    return this;
  }

  public ConsistentHashRingBuilder<T> withNodes(Collection<T> nodes) {
    this.initialNodes = new ArrayList<>(nodes);
    return this;
  }

  public ConsistentHashRingBuilder<T> addNode(T node) {
    this.initialNodes.add(node);
    return this;
  }

  public ConsistentHashRing<T> build() {
    ConsistentHashRing<T> ring = new ConsistentHashRing<>(
      hashFunction, virtualNodes);

    if (!initialNodes.isEmpty()) {
      ring.addNodes(initialNodes);
    }

    return ring;
  }
}
