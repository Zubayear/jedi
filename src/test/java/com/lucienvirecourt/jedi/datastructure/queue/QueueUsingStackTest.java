package com.lucienvirecourt.jedi.datastructure.queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueUsingStackTest {

  QueueUsingStack<Character> queue;

  @BeforeEach
  void setUp() {
    queue = new QueueUsingStack<>();
  }

  @AfterEach
  void tearDown() {
    queue = null;
  }

  @Test
  @DisplayName("Test queue using stack implementation")
  void testImplementation() {
    assertTrue(queue.empty());
    String str = "wish you were here";
    for (char c : str.toCharArray()) {
      if (c == ' ') {
        continue;
      }
      queue.enqueue(c);
    }
    assertEquals('w', queue.peek());
    assertEquals('w', queue.dequeue());
    assertFalse(queue.empty());
  }
}
