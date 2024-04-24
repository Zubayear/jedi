package com.zubayear.customds.stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackWith1QTest {

    MyStackWith1Q stack;

    @BeforeEach
    void setUp() {
        stack = new MyStackWith1Q();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    void push() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        int top = stack.top();
        System.out.println(top);
        int pop = stack.pop();
        System.out.println(pop);
    }
}