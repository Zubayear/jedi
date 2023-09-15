package com.zubayear.customds.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void reorganizeString() {
        Trie t = new Trie();
        String aab = t.reorganizeString("aabbcaabcd");
        System.out.println(aab);
    }
}