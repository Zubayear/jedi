package com.zubayear.customds.queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueUsingStackTest {

    QueueUsingStack queueUsingStack;

    @BeforeEach
    void setUp() {
        queueUsingStack = new QueueUsingStack();
    }

    @AfterEach
    void tearDown() {
        queueUsingStack = null;
    }

    @Test
    void push() {
        queueUsingStack.enqueue(1);
        queueUsingStack.enqueue(2);
        queueUsingStack.enqueue(3);

        int pop = queueUsingStack.dequeue();
        System.out.println(pop);

        queueUsingStack.print();
    }

    @Test
    void enqueueOptTest() {
        queueUsingStack.enqueueOpt(1);
        queueUsingStack.enqueueOpt(2);
        queueUsingStack.enqueueOpt(3);

        int i = queueUsingStack.dequeueOpt();
        System.out.println(i);

        queueUsingStack.printOpt();
    }
}