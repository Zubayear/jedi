package com.zubayear.customds.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    protected Map<Character, TrieNode> children;
    protected boolean isTerminal;

    public TrieNode() {
        children = new HashMap<>();
        isTerminal = false;
    }
}
