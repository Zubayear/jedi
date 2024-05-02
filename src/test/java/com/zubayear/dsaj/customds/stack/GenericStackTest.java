package com.zubayear.dsaj.customds.stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals('b', stack.peek(2));
        Assertions.assertEquals('b', stack.pop());
        Assertions.assertEquals('a', stack.peek());
        Assertions.assertEquals(1, stack.size());
        stack.clear();
        Assertions.assertTrue(stack.isEmpty());
    }
}