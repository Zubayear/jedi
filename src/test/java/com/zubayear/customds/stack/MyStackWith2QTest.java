package com.zubayear.customds.stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStackWith2QTest {

    MyStackWith2Q ms;

    @BeforeEach
    void setUp() {
        ms = new MyStackWith2Q();
    }

    @AfterEach
    void tearDown() {
        ms = null;
    }

    @Test
    void push() {
        ms.push(3);
        ms.push(4);
        ms.push(2);
        ms.push(1);
        Assertions.assertEquals(ms.pop(), 1);
        Assertions.assertEquals(ms.pop(), 2);
    }
}