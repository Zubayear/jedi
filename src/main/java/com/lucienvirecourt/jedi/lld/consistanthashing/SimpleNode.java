package com.lucienvirecourt.jedi.lld.consistanthashing;

import java.util.Objects;

public class SimpleNode implements Node {
  private final String key;
  private final NodeMetadata metadata;

  public SimpleNode(String key) {
    this.key = Objects.requireNonNull(key);
    this.metadata = new NodeMetadata();
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public NodeMetadata getMetadata() {
    return metadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SimpleNode that)) return false;
    return key.equals(that.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }

  @Override
  public String toString() {
    return "Node{" + key + "}";
  }
}
