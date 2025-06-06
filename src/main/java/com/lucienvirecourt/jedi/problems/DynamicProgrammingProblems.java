package com.lucienvirecourt.jedi.problems;

import java.util.Arrays;

public class DynamicProgrammingProblems {
  public static int climbStairs(int n) {
    // memoization
    /*int[] cache = new int[n + 1];
    Arrays.fill(cache, -1);
    return climbStairs(n, cache);*/

    // optimal
    return climbStairsOptimal(n);
  }

  private static int climbStairsOptimal(int n) {
    int current, prev = 1, prev2 = 1;
    for (int i = 0; i < n - 1; i++) {
      current = prev + prev2;
      prev2 = prev;
      prev = current;
    }
    return prev;
  }

  private static int climbStairs(int n, int[] cache) {
    if (n == 0 || n == 1) return 1;
    if (cache[n] != -1) return cache[n];
    return cache[n] = climbStairs(n - 1, cache) + climbStairs(n - 2, cache);
  }

  public static int minCostClimbingStairs(int[] cost) {
    if (cost.length == 2) return Math.min(cost[0], cost[1]);
    return Math.min(
      minCostClimbingStairs(0, cost),
      minCostClimbingStairs(1, cost)
    );
  }

  private static int minCostClimbingStairs(int idx, int[] cost) {
    if (idx >= cost.length) return 0;
    return cost[idx] + Math.min(
      minCostClimbingStairs(idx + 1, cost),
      minCostClimbingStairs(idx + 2, cost)
    );
  }

  private static int minCostClimbingStairsOptimal(int[] cost) {
    if (cost.length == 2) return Math.min(cost[0], cost[1]);
    // optimal
    int n = cost.length + 1;
    int[] newCost = new int[n];
    int i = 0;
    for (; i < n - 1; i++) {
      newCost[i] = cost[i];
    }
    newCost[i] = 0;
    // 10 15 20 0
    for (int j = n - 3; j >= 0; --j) {
      newCost[j] += Math.min(newCost[j + 1], newCost[j + 2]);
    }
    return Math.min(newCost[0], newCost[1]);
  }

  public static int nthTribonacciNumber(int n) {
    int t0 = 0, t1 = 1, t2 = 1;
    for (int i = 3; i <= n; i++) {
      // 0  1  1  2
      // t0 t1 t2 c
      int current = t0 + t1 + t2;
      t0 = t1;
      t1 = t2;
      t2 = current;
    }
    return t2;
  }

  public static int houseRobber(int n) {
    return -1;
  }
}
