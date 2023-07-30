package com.zubayear.customds.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class _TrieTest {

    _Trie trie;

    @BeforeEach
    void init() {
        trie = new _Trie();
    }

    @Test
    void insertWord() {
        String[] strs = {"flower", "flow", "flight"};
        for (String str : strs) trie.insertWord(str);
        assertTrue(trie.searchWord("flow"));
        assertFalse(trie.searchWord("saitama"));
        assertTrue(trie.startsWith("fl"));
    }

    @Test
    void searchWord() {
    }

    @Test
    void startsWith() {
    }
}