package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.DynamicProgrammingProblems.*;
import static com.lucienvirecourt.jedi.problems.DynamicProgrammingProblems.partitionCount;
import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  void canPartitionTest() {
    assertTrue(canPartition(new int[]{4,3,2,3,5,2,1}));
    assertFalse(canPartition(new int[]{1, 2, 3, 5}));
  }

  @Test
  void minimumDifferenceTest() {
    assertEquals(1, canPartitionOptimal(new int[]{2,7,4,1,8,1}));
    assertEquals(5, canPartitionOptimal(new int[]{31,26,33,21,40}));
  }

  @Test
  void partitionCountTest() {
    assertEquals(1, partitionCount(new int[]{2,2,2,2,3,4,5}, 9));
    // {4,1},{3,2},{2,3},{5},{3,2},{2,3},{1,4}
  }

  @Test
  void coinChangeTest() {
    assertEquals(3, coinChange(new int[]{1,2,5}, 11));
  }

  @Test
  void partitionCountWithGivenDiffTest() {
    assertEquals(5, findTargetSumWays(new int[]{1,1,1,1,1}, 3));
  }
}
