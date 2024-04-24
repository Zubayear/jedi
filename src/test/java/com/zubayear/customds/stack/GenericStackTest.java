package com.zubayear.customds.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericStackTest {
    GenericStack<Integer> genericStack;

    @BeforeEach
    void setUp() {
        genericStack = new GenericStack<>(5);
        genericStack.push(10);
        genericStack.push(20);
        genericStack.push(30);
    }

    @Test
    void push() {
        assertEquals(30, genericStack.peek());
    }

    @Test
    void pop() {
        assertEquals(30, genericStack.pop());
        assertEquals(20, genericStack.peek());
    }
}