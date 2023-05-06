package com.zubayear.customds.trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch); // go to next node
        }
        current.isTerminal = true;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) return false;
            current = current.children.get(ch);
        }
        return true;
    }

    public boolean exists(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) return false;
            current = current.children.get(ch);
        }
        return current.isTerminal;
    }

    public List<String> dfs(TrieNode node) {
        List<String> result = new ArrayList<>();
        dfsHelper(node, new StringBuilder(), result);
        return result;
    }

    public List<String> dfs(TrieNode node, String prefix) {
        List<String> result = new ArrayList<>();
        dfsHelper(node, new StringBuilder(prefix), result);
        return result;
    }

    private void dfsHelper(TrieNode root, StringBuilder runningWord, List<String> result) {
        if (root.isTerminal) {
            // we found the word
            result.add(runningWord.toString());
        }
        for (char c : root.children.keySet()) {
            runningWord.append(c); // a
            dfsHelper(root.children.get(c), runningWord, result); // a's child
            runningWord.deleteCharAt(runningWord.length() - 1); // remove last char i.e. apple remove e and explore from l
        }
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = findNodeForPrefix(prefix);
        if (node == null) return result;
        return dfs(node, prefix);
    }

    /**
     * @param prefix prefix to find the TrieNode corresponding to the prefix.
     * @return
     */
    private TrieNode findNodeForPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) return null;
        }
        return current; // this will return TireNode of p of app
    }
}
