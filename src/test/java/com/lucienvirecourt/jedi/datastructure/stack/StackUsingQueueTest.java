package com.lucienvirecourt.jedi.datastructure.stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackUsingQueueTest {

  StackUsingQueue<Integer> stack;

  @BeforeEach
  void setUp() {
    stack = new StackUsingQueue<>();
  }

  @AfterEach
  void tearDown() {
    stack = null;
  }

  @Test
  void testStackOperations() {
    assertNull(stack.pop());
    assertNull(stack.peek());
    stack.push(30);
    stack.push(39);
    stack.push(69);
    assertEquals(69, stack.pop());
    assertEquals(39, stack.peek());
  }
}
