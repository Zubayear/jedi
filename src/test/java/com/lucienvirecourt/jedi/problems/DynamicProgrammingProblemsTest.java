package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.DynamicProgrammingProblems.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicProgrammingProblemsTest {

  @Test
  void waysToJump() {
    assertEquals(3, climbStairs(3));
    assertEquals(8, climbStairs(5));
    assertEquals(13, climbStairs(6));
  }

  @Test
  void minCostClimbingStairsTest() {
    assertEquals(15, minCostClimbingStairs(new int[]{10, 15, 20}));
    assertEquals(6, minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
  }

  @Test
  void nthTribonacciNumberTest() {
    assertEquals(4, nthTribonacciNumber(4));
    assertEquals(1389537, nthTribonacciNumber(25));
  }
}
