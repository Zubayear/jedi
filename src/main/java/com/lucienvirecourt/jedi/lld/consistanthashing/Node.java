package com.lucienvirecourt.jedi.lld.consistanthashing;

import java.util.HashMap;
import java.util.Map;

public interface Node {
  String getKey();
  default NodeMetadata getMetadata() {
    return new NodeMetadata();
  }
}

class NodeMetadata {
  private final Map<String, Object> attributes;

  NodeMetadata() {
    this.attributes = new HashMap<>();
  }

  public void setAttribute(String key, Object value) {
    attributes.put(key, value);
  }

  public Object getAttribute(String key) {
    return attributes.get(key);
  }
}
