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
    assertEquals(6, minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
  }

  @Test
  void nthTribonacciNumberTest() {
    assertEquals(4, nthTribonacciNumber(4));
    assertEquals(1389537, nthTribonacciNumber(25));
  }

  @Test
  void houseRobberTest() {
    assertEquals(4, houseRobber(new int[]{1, 2, 3, 1}));
    assertEquals(12, houseRobber(new int[]{2, 7, 9, 3, 1}));
  }

  @Test
  void houseRobberIITest() {
    assertEquals(3, houseRobberII(new int[]{2, 3, 2}));
    assertEquals(4, houseRobberII(new int[]{1, 2, 3, 1}));
    assertEquals(3, houseRobberII(new int[]{1, 2, 3}));
  }

  @Test
  void minimumPathSumTest() {
    assertEquals(7, minimumPathSum(new int[][]{
      {1, 3, 1},
      {1, 5, 1},
      {4, 2, 1}
    }));
    assertEquals(12, minimumPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
  }

  @Test
  void minimumFallingPathSumTest() {
    assertEquals(13, minimumFallingPathSum(new int[][]{
      {2, 1, 3},
      {6, 5, 4},
      {7, 8, 9}
    }));

    assertEquals(-59, minimumFallingPathSum(new int[][]{
      {-19, 57},
      {-40, -5},
    }));
  }
}
