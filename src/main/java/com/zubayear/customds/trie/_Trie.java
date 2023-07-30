package com.zubayear.customds.trie;

public class _Trie {
    _TrieNode root;

    public _Trie() {
        root = new _TrieNode();
    }

    public void insertWord(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        word = word.toLowerCase();
        _TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                _TrieNode node = new _TrieNode();
                current.children[index] = node; // establish link
                current = node; // go to next
            } else {
                current = current.children[index]; // move the pointer since char already exists
            }
        }
        current.terminal = true;
    }

    public boolean searchWord(String word) {
        _TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) return false;
            current = current.children[index];
        }
        return current.terminal;
    }

    public boolean startsWith(String prefix) {
        _TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) return false;
            current = current.children[index];
        }
        return !current.terminal;
    }
}
