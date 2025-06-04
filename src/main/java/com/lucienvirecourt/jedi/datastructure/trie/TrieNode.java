package com.lucienvirecourt.jedi.datastructure.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TrieNode {
  protected Map<Character, TrieNode> children;
  protected boolean isTerminal;

  public TrieNode() {
    children = new HashMap<>();
    isTerminal = false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TrieNode trieNode)) return false;
    return isTerminal == trieNode.isTerminal && Objects.equals(children, trieNode.children);
  }

  @Override
  public int hashCode() {
    return Objects.hash(children, isTerminal);
  }

  @Override
  public String toString() {
    return "TrieNode{" +
      "children=" + children +
      ", isTerminal=" + isTerminal +
      '}';
  }
}
