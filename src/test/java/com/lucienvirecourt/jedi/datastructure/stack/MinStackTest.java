package com.lucienvirecourt.jedi.datastructure.stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinStackTest {

  MinStack<Integer> minStack;

  @BeforeEach
  void setUp() {
    minStack = new MinStack<>();
  }

  @AfterEach
  void tearDown() {
    minStack = null;
  }

  @Test
  void testMinStackOperations() {
    int[] values = {1, 2, 3, 4, 5, -1, 0, -2, -90};

    for (int v : values) {
      minStack.push(v);
    }

    assertEquals(-90, minStack.getMin());
    minStack.pop();
    assertEquals(-2, minStack.getTop());
  }
}
