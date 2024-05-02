package com.zubayear.dsaj.datastructure.trie;

import java.util.*;

public class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        current.isTerminal = true;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return true;
    }

    public boolean exists(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return current.isTerminal;
    }

    public List<String> dfs() {
        List<String> result = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        dfs(root, currentWord, result);
        return result;
    }

    private void dfs(TrieNode node, StringBuilder currentWord, List<String> result) {
        if (node == null) {
            return;
        }
        if (node.isTerminal) {
            result.add(currentWord.toString());
        }
        for (char c : node.children.keySet()) {
            currentWord.append(c);
            dfs(node.children.get(c), currentWord, result);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = findNodeForPrefix(prefix);
        if (node == null) {
            return result;
        }
        StringBuilder currentWord = new StringBuilder(prefix);
        dfs(node, currentWord, result);
        return result;
    }


    private TrieNode findNodeForPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return null;
            }
            current = current.children.get(ch);
        }
        return current;
    }
}
