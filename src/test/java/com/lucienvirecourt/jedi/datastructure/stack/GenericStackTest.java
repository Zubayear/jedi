package com.lucienvirecourt.jedi.datastructure.stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GenericStackTest {

  GenericStack<Character> stack;

  @BeforeEach
  void setUp() {
    stack = new GenericStack<>();
  }

  @AfterEach
  void tearDown() {
    stack = null;
  }

  @Test
  void testStackOperations() {
    stack.push('a');
    stack.push('b');
    assertFalse(stack.isEmpty());
    assertEquals('b', stack.peek(2));
    assertEquals('b', stack.pop());
    assertEquals('a', stack.peek());
    assertEquals(1, stack.size());
    stack.clear();
    Assertions.assertTrue(stack.isEmpty());
  }
}
