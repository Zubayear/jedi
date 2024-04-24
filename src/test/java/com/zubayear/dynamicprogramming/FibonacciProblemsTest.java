package com.zubayear.dynamicprogramming;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciProblemsTest {

    FibonacciProblems fibonacciProblems;

    @BeforeEach
    void setUp() {
        fibonacciProblems = new FibonacciProblems();
    }

    @AfterEach
    void tearDown() {
        fibonacciProblems = null;
    }

    @Test
    void minCostClimbingStairs() {
        int i = fibonacciProblems.minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1});
        System.out.println(i);
    }
}