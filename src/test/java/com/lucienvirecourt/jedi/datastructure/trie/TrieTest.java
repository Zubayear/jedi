package com.lucienvirecourt.jedi.datastructure.trie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

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
  void testOperations() {
    List<String> words = Arrays.asList("football", "life", "storm", "polar bear", "polar", "king", "kin", "kill");
    words.forEach(w -> trie.insert(w));

    assertTrue(trie.exists("football"));
    assertTrue(trie.startsWith("foot"));

    List<String> actualPrefix = trie.getWordsWithPrefix("ki");
    List<String> expectedPrefix = Arrays.asList("king", "kin", "kill");
    assertThat("List equality without order", actualPrefix, containsInAnyOrder(expectedPrefix.toArray()));

    List<String> actualDfsResult = trie.dfs();
    assertThat("DFS Test", actualDfsResult, containsInAnyOrder(words.toArray()));
    assertTrue(trie.remove("football"));
    assertFalse(trie.exists("football"));
    assertEquals(7, trie.size());
    assertTrue(trie.remove("kin"));
    assertEquals(6, trie.size());
  }
}
