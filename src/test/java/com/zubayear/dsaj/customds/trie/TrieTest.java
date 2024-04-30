package com.zubayear.dsaj.customds.trie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class TrieTest {

    Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @AfterEach
    void tearDown() {
        trie = null;
    }

    @Test
    void insert() {
        List<String> words = Arrays.asList("football", "life", "storm", "polar bear", "polar");
        words.forEach(w -> trie.insert(w));
        // test1
        Assertions.assertTrue(trie.exists("football"));
        // test2
        Assertions.assertTrue(trie.startsWith("foot"));

        // test3
        List<String> actualPrefix = trie.getWordsWithPrefix("polar");
        List<String> expectedPrefix = Arrays.asList("polar bear", "polar");
        assertThat("List equality without order", actualPrefix, containsInAnyOrder(expectedPrefix.toArray()));

        // test4
        List<String> actualDfsResult = trie.dfs();
        assertThat("List equality without order", actualDfsResult, containsInAnyOrder(words.toArray()));

    }
}