package com.zubayear.customds.queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleQueueTest {

    SimpleQueue simpleQueue;

    @BeforeEach
    void setUp() {
        simpleQueue = new SimpleQueue(5);
    }

    @AfterEach
    void tearDown() {
        simpleQueue = null;
    }

    @Test
    void enqueue() {
        simpleQueue.enqueue(4);
        simpleQueue.enqueue(40);
        simpleQueue.enqueue(48);
        int dequeue = simpleQueue.dequeue();
        System.out.println(dequeue);
        simpleQueue.print();
    }
}