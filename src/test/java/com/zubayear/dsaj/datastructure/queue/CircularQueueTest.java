package com.zubayear.dsaj.datastructure.queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircularQueueTest {

    CircularQueue<Character> circularQueue;

    @BeforeEach
    void setUp() {
        circularQueue = new CircularQueue<>();
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

        Assertions.assertFalse(circularQueue.isEmpty());
        Assertions.assertEquals(13, circularQueue.size());
        Assertions.assertEquals('t', circularQueue.dequeue());
        Assertions.assertEquals('o', circularQueue.peek());

        Assertions.assertEquals("[o,b,e,o,r,n,o,t,t,o,b,e]", circularQueue.queueStr());


        circularQueue.clear();
        Assertions.assertTrue(circularQueue.isEmpty());

    }
}