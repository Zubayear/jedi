package com.lucienvirecourt.jedi.datastructure.trie;

import java.util.*;

public class Trie {
  public TrieNode root;
  private int size = 0;

  public Trie() {
    root = new TrieNode();
  }

  // O(n) | O(n)
  public void insert(String word) {
    TrieNode current = root;
    for (char ch : word.toCharArray()) {
      current.children.putIfAbsent(ch, new TrieNode());
      current = current.children.get(ch);
    }
    if (!current.isTerminal) {
      current.isTerminal = true;
      size++;
    }
  }

  // O(k) | O(1)
  public boolean startsWith(String prefix) {
    TrieNode current = root;
    for (char ch : prefix.toCharArray()) {
      if (!current.children.containsKey(ch)) return false;
      current = current.children.get(ch);
    }
    return true;
  }

  // O(n) | O(1)
  public boolean exists(String word) {
    TrieNode current = root;
    for (char ch : word.toCharArray()) {
      if (!current.children.containsKey(ch)) return false;
      current = current.children.get(ch);
    }
    return current.isTerminal;
  }

  // O(m * L) | O(m * L)
  public List<String> dfs() {
    List<String> result = new ArrayList<>();
    StringBuilder currentWord = new StringBuilder();
    dfs(root, currentWord, result);
    return result;
  }

  private void dfs(TrieNode node, StringBuilder currentWord, List<String> result) {
    if (node == null) return;
    if (node.isTerminal) result.add(currentWord.toString());
    for (char c : node.children.keySet()) {
      currentWord.append(c);
      dfs(node.children.get(c), currentWord, result);
      currentWord.deleteCharAt(currentWord.length() - 1);
    }
  }

  // O(k + w * L') | O(w * L')
  public List<String> getWordsWithPrefix(String prefix) {
    List<String> result = new ArrayList<>();
    TrieNode node = findNodeForPrefix(prefix);
    if (node == null) return result;
    StringBuilder currentWord = new StringBuilder(prefix);
    dfs(node, currentWord, result);
    return result;
  }


  // O (k) | O(1)
  private TrieNode findNodeForPrefix(String prefix) {
    TrieNode current = root;
    for (char ch : prefix.toCharArray()) {
      if (!current.children.containsKey(ch)) return null;
      current = current.children.get(ch);
    }
    return current;
  }

  private static class Pair {
    TrieNode node;
    char ch;

    Pair(TrieNode node, char ch) {
      this.node = node;
      this.ch = ch;
    }
  }

  // O(1)
  public boolean remove(String word) {
    TrieNode current = root;
    Deque<Pair> stack = new ArrayDeque<>();
    for (char ch : word.toCharArray()) {
      TrieNode next = current.children.get(ch);
      if (next == null) return false;
      stack.push(new Pair(current, ch));
      current = next;
    }
    if (!current.isTerminal) return false;
    current.isTerminal = false;

    while (!stack.isEmpty()) {
      Pair top = stack.pop();
      TrieNode parent = top.node;
      char ch = top.ch;
      TrieNode child = parent.children.get(ch);
      if (child.children.isEmpty() && !child.isTerminal) {
        parent.children.remove(ch);
      } else break;
    }
    size--;
    return true;
  }

  // O(1) | O(1)
  public int size() {
    return size;
  }
}
