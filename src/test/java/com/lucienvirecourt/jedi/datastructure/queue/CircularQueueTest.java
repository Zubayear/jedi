package com.lucienvirecourt.jedi.datastructure.queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularQueueTest {

  CircularQueue<Character> circularQueue;

  @BeforeEach
  void setUp() {
    circularQueue = new CircularQueue<>(500);
  }

  @AfterEach
  void tearDown() {
    circularQueue = null;
  }

  @Test
  void testCircularQueue() {
    String str = "to be or not to be";
    for (char ch : str.toCharArray()) {
      if (ch == ' ') {
        continue;
      }
      circularQueue.enqueue(ch);
    }

    assertFalse(circularQueue.isEmpty());
    assertEquals(13, circularQueue.size());
    Character dequeue = circularQueue.dequeue();
    assertEquals('t', dequeue);
    assertEquals('o', circularQueue.peek());

    assertEquals("[o,b,e,o,r,n,o,t,t,o,b,e]", circularQueue.queueStr());


    circularQueue.clear();
    assertTrue(circularQueue.isEmpty());

    circularQueue.enqueue('h');
    circularQueue.enqueue('e');
    circularQueue.enqueue('l');
    assertEquals('h', circularQueue.peek());

  }
}
