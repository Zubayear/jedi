package com.lucienvirecourt.jedi.problems;

import java.util.Arrays;

public class DynamicProgrammingProblems {
  static int mod = (int) (1e9 + 7);

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

  public static int houseRobber(int[] nums) {
    // optimal
    if (nums.length == 2) return Math.max(nums[0], nums[1]);
    int current, prev = nums[0], prev2 = 0;
    int n = nums.length;
    for (int i = 1; i < n; ++i) {
      int take = nums[i];
      if (i > 1) take += prev2;
      int ignore = prev;
      current = Math.max(take, ignore);
      prev2 = prev;
      prev = current;
    }
    return prev;
//    return houseRobber(nums.length - 1, nums);
  }

  private static int houseRobber(int idx, int[] nums) {
    if (idx == 0) return nums[0]; // means we have not picked adjacent 1
    if (idx < 0) return 0;
    int take = nums[idx] + houseRobber(idx - 2, nums);
    int ignore = houseRobber(idx - 1, nums);
    return Math.max(take, ignore);
  }

  public static int houseRobberII(int[] nums) {
    int n = nums.length;
    int[] nums1 = new int[n - 1];
    int[] nums2 = new int[n - 1];
    for (int i = 0; i < n; ++i) {
      // 1 2 3 1
      if (i != 0) nums1[i - 1] = nums[i];
      if (i != n - 1) nums2[i] = nums[i];
    }
    return Math.max(houseRobber(nums1), houseRobber(nums2));
  }

  public static int minimumPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m + 1][n + 1];
    for (int[] d : dp) {
      Arrays.fill(d, -1);
    }
    return minimumPathSum(m - 1, n - 1, grid, dp);
  }

  private static int minimumPathSum(int i, int j, int[][] grid, int[][] dp) {
    if (i == 0 && j == 0) return grid[i][j];
    if (i < 0 || j < 0) return (int) 1e9;
    if (dp[i][j] != -1) return dp[i][j];
    int up = grid[i][j] + minimumPathSum(i - 1, j, grid, dp);
    int left = grid[i][j] + minimumPathSum(i, j - 1, grid, dp);
    return dp[i][j] = Math.min(up, left);
  }

  public static int minimumFallingPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int ans = (int) 1e9;
//    for (int j = 0; j < n; ++j) {
//      ans = Math.min(ans, minimumFallingPathSum(m - 1, j, grid));
//    }
    // optimal
    for (int j = 0; j < n; ++j) {
      ans = Math.min(ans, minimumFallingPathSumOptimal(grid, m - 1, j));
    }
    return ans;
  }

  private static int minimumFallingPathSum(int i, int j, int[][] grid) {
    if (j < 0 || j >= grid[0].length) return (int) 1e9;
    if (i == 0) return grid[i][j];
    int up = grid[i][j] + minimumFallingPathSum(i - 1, j, grid);
    int ld = grid[i][j] + minimumFallingPathSum(i - 1, j - 1, grid);
    int rd = grid[i][j] + minimumFallingPathSum(i - 1, j + 1, grid);
    return Math.min(up, Math.min(ld, rd));
  }

  private static int minimumFallingPathSumOptimal(int[][] grid, int a, int b) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];
//    for (int j = 0; j < n; ++j) dp[0][j] = grid[0][j];
    System.arraycopy(grid[0], 0, dp[0], 0, n);
    for (int i = 1; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        int up = grid[i][j] + dp[i - 1][j];
        int ld = grid[i][j];
        if (j >= 1) ld += dp[i - 1][j - 1];
        else ld += (int) 1e9; // do we need it?
        int rd = grid[i][j];
        if (j < m - 1) rd += dp[i - 1][j + 1];
        else rd += (int) 1e9;
        dp[i][j] = Math.min(up, Math.min(ld, rd));
      }
    }
    System.out.println("dp " + Arrays.deepToString(dp));
    return dp[a][b];
  }

  public static boolean canPartition(int[] nums) {
    int sum = 0;
    for (int n : nums) {
      sum += n;
    }
    if (sum % 2 != 0) return false;
    return tryPartition(nums.length - 1, sum / 2, nums);
  }

  private static boolean tryPartition(int idx, int target, int[] nums) {
    if (target == 0) return true;
    if (idx == 0) return nums[0] == target;
    boolean skip = tryPartition(idx - 1, target, nums);
    boolean include = false;
    if (nums[idx] <= target) include = tryPartition(idx - 1, target - nums[idx], nums);
    return skip || include;
  }

  public static int canPartitionOptimal(int[] nums) {
    int sum = 0;
    for (int n : nums) sum += n;
    if (sum % 2 != 0) return 0;
    int n = nums.length, target = sum / 2;
    boolean[][] dp = new boolean[n][target + 1];
    for (int i = 0; i < n; ++i) dp[i][0] = true;
    dp[0][nums[0]] = true;
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < target; ++j) {
        boolean skip = dp[i - 1][j];
        boolean include = false;
        if (nums[i] <= j) include = dp[i - 1][j - nums[i]];
        dp[i][j] = skip || include;
      }
    }
    boolean[] lastRow = dp[n - 1];
    int result = (int) 1e9;
    for (int i = 0; i < lastRow.length; ++i) {
      if (lastRow[i]) {
        int absDiff = Math.abs(target - i);
        result = Math.min(result, absDiff);
      }
    }
    return result * 2;
  }

  /*
   * Partition count (p17)
   * */
  public static int partitionCount(int[] nums, int k) {
    int[][] dp = new int[nums.length][k];
    for (int[] d : dp) Arrays.fill(d, -1);
    return partitionCount(nums.length - 1, k, nums, dp);
  }

  private static int partitionCount(int idx, int target, int[] nums, int[][] dp) {
    if (idx == 0) {
      if (target == 0 && nums[0] == 0) return 2;
      if (target == 0 || target == nums[0]) return 1;
      return 0;
    }
    if (dp[idx][target] != -1) return dp[idx][target];
    int skip = partitionCount(idx - 1, target, nums, dp);
    int include = 0;
    if (nums[idx] <= target) include = 1 + partitionCount(idx - 1, target - nums[idx], nums, dp);
    return dp[idx][target] = skip + include;
  }

  /*
   * Count partitions with the given difference (p18)
   * */
  public static int findTargetSumWays(int[] nums, int target) {
    int sum = 0;
    for (int n : nums) sum += n;
    if (sum - target < 0 || (sum - target) % 2 != 0) return 0;
    int[][] dp = new int[nums.length][target+1];
    for (int[] d : dp) Arrays.fill(d, -1);
    return findTargetSumWays(nums.length - 1, (sum - target) / 2, nums, dp);
  }

  private static int findTargetSumWays(int idx, int target, int[] nums, int[][] dp) {
    if (idx == 0) {
      if (target == 0 && nums[0] == 0) return 2;
      if (target == 0 || target == nums[0]) return 1;
      return 0;
    }
    if (dp[idx][target] != -1) return dp[idx][target];
    int skip = findTargetSumWays(idx - 1, target, nums, dp);
    int include = 0;
    if (nums[idx] <= target) include = findTargetSumWays(idx - 1, target - nums[idx], nums, dp);
    return dp[idx][target] = (skip + include);
  }

  public static int coinChange(int[] coins, int amount) {
    int m = coins.length, n = amount + 1;
    int[][] dp = new int[m][n];
    for (int i = 0; i < n; ++i) {
      if (i % coins[0] == 0) dp[0][i] = i / coins[0];
      else dp[0][i] = (int) 1e9;
    }
    for (int i = 1; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        int skip = dp[i - 1][j];
        int include = (int) 1e9;
        if (coins[i] <= j) include = 1 + dp[i][j - coins[i]];
        dp[i][j] = Math.min(skip, include);
      }
    }
    int result = dp[m - 1][amount];
    if (result == (int) 1e9) return -1;
    return result;
  }

  public static int coinChangeOptimal(int[] coins, int amount) {
    int m = coins.length, n = amount + 1;
    int[] prev = new int[n], cur = new int[n];
    for (int i = 0; i < n; ++i) {
      if (i % coins[0] == 0) prev[i] = i / coins[0];
      else prev[i] = (int) 1e9;
    }
    for (int i = 1; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        int skip = prev[j];
        int include = (int) 1e9;
        if (coins[i] <= j) include = 1 + cur[j - coins[i]];
        cur[j] = Math.min(skip, include);
      }
      prev = cur;
    }
    int result = prev[amount];
    if (result == (int) 1e9) return -1;
    return result;
  }

}
