package com.lucienvirecourt.jedi.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgrammingProblems {
  int mod = (int) (1e9 + 7);

  // O(n) | O(1)
  public int climbStairs(int n) {
    // memoization
    /*int[] cache = new int[n + 1];
    Arrays.fill(cache, -1);
    return climbStairs(n, cache);*/

    // optimal
    return climbStairsOptimal(n);
  }

  private int climbStairsOptimal(int n) {
    int current, prev = 1, prev2 = 1;
    for (int i = 0; i < n - 1; i++) {
      current = prev + prev2;
      prev2 = prev;
      prev = current;
    }
    return prev;
  }

  private int climbStairs(int n, int[] cache) {
    if (n == 0 || n == 1) return 1;
    if (cache[n] != -1) return cache[n];
    return cache[n] = climbStairs(n - 1, cache) + climbStairs(n - 2, cache);
  }

  public int minCostClimbingStairs(int[] cost) {
    if (cost.length == 2) return Math.min(cost[0], cost[1]);
    return Math.min(
      minCostClimbingStairs(0, cost),
      minCostClimbingStairs(1, cost)
    );
  }

  private int minCostClimbingStairs(int idx, int[] cost) {
    if (idx >= cost.length) return 0;
    return cost[idx] + Math.min(
      minCostClimbingStairs(idx + 1, cost),
      minCostClimbingStairs(idx + 2, cost)
    );
  }

  private int minCostClimbingStairsOptimal(int[] cost) {
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

  public int nthTribonacciNumber(int n) {
    if (n == 0) return 0;
    if (n == 1 || n == 2) return 1;
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

  public int houseRobber(int[] nums) {
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

  int houseRobber(int idx, int[] nums) {
    if (idx == 0) return nums[0]; // means we have not picked adjacent 1
    if (idx < 0) return 0;
    int take = nums[idx] + houseRobber(idx - 2, nums);
    int ignore = houseRobber(idx - 1, nums);
    return Math.max(take, ignore);
  }

  public int houseRobberII(int[] nums) {
    int n = nums.length;
    if (n == 1) return nums[0];
    int[] nums1 = new int[n - 1];
    int[] nums2 = new int[n - 1];
    for (int i = 0; i < n; ++i) {
      if (i != 0) nums1[i - 1] = nums[i];
      if (i != n - 1) nums2[i] = nums[i];
    }
    return Math.max(houseRobber(nums1), houseRobber(nums2));
  }

  public int minimumPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m + 1][n + 1];
    for (int[] d : dp) {
      Arrays.fill(d, -1);
    }
    return minimumPathSum(m - 1, n - 1, grid, dp);
  }

  private int minimumPathSum(int i, int j, int[][] grid, int[][] dp) {
    if (i == 0 && j == 0) return grid[i][j];
    if (i < 0 || j < 0) return (int) 1e9;
    if (dp[i][j] != -1) return dp[i][j];
    int up = grid[i][j] + minimumPathSum(i - 1, j, grid, dp);
    int left = grid[i][j] + minimumPathSum(i, j - 1, grid, dp);
    return dp[i][j] = Math.min(up, left);
  }

  public int minimumFallingPathSum(int[][] grid) {
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

  private int minimumFallingPathSum(int i, int j, int[][] grid) {
    if (j < 0 || j >= grid[0].length) return (int) 1e9;
    if (i == 0) return grid[i][j];
    int up = grid[i][j] + minimumFallingPathSum(i - 1, j, grid);
    int ld = grid[i][j] + minimumFallingPathSum(i - 1, j - 1, grid);
    int rd = grid[i][j] + minimumFallingPathSum(i - 1, j + 1, grid);
    return Math.min(up, Math.min(ld, rd));
  }

  private int minimumFallingPathSumOptimal(int[][] grid, int a, int b) {
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
    return dp[a][b];
  }

  /*
   * dp on subset
   * */
  public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int n : nums) {
      sum += n;
    }
    if (sum % 2 != 0) return false;
    return tryPartition(nums.length - 1, sum / 2, nums);
  }

  private boolean tryPartition(int idx, int target, int[] nums) {
    if (target == 0) return true;
    if (idx == 0) return nums[0] == target;
    boolean skip = tryPartition(idx - 1, target, nums);
    boolean include = false;
    if (nums[idx] <= target) include = tryPartition(idx - 1, target - nums[idx], nums);
    return skip || include;
  }

  public boolean partitionEqualSubsetSum(int[] nums) {
    int len = nums.length;
    if (len == 0) return false;
    int sum = 0;
    for (int n : nums) sum += n;
    if (sum % 2 != 0) return false;
    int t = sum / 2;
    boolean[][] dp = new boolean[len][t + 1];
    // when target is zero we set to true i.e.; first column is set to zero
    // for idx 0, we set dp[0][nums[0]] to true; [1,5,11,5]
    for (int i = 0; i < len; ++i) dp[i][0] = true;
    if (nums[0] <= t) dp[0][nums[0]] = true;

    for (int i = 1; i < len; ++i) {
      for (int j = 1; j <= t; ++j) {
        // we can skip or include a position
        boolean skip = dp[i - 1][j];
        boolean include = false;
        if (nums[i] <= j) include = dp[i - 1][j - nums[i]];
        dp[i][j] = skip || include;
      }
    }
    return dp[len - 1][t];
  }

  public int partitionCount(int[] nums, int k) {
    int[][] dp = new int[nums.length][k];
    for (int[] d : dp) Arrays.fill(d, -1);
    return partitionCount(nums.length - 1, k, nums, dp);
  }

  private int partitionCount(int idx, int target, int[] nums, int[][] dp) {
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
  public int findTargetSumWays(int[] nums, int target) {
    int sum = 0;
    for (int n : nums) sum += n;
    if (sum - target < 0 || (sum - target) % 2 != 0) return 0;
    int[][] dp = new int[nums.length][target + 1];
    for (int[] d : dp) Arrays.fill(d, -1);
    return findTargetSumWays(nums.length - 1, (sum - target) / 2, nums, dp);
  }

  // this is only for positive numbers
  public int minimumDifference(int[] nums) {
    // the last row of the dp table will tell you the answer
    // [3,2,7]
    // 0 1 2 3 4 5 6 7 8 9 10 11 12 <- last row
    // y n y y n y n y n y y  n  y
    // so the possible s1 is 0   2 3 5 7 9 10 12
    // corresponding s2 is.  12 10 9 7 5 3 2  0
    // find abs(s1-s2)
    int n = nums.length;
    int sum = 0;
    for (int num : nums) sum += num;
    boolean[][] dp = new boolean[n][sum + 1];
    // when target is zero we set to true i.e.; first column is set to zero
    // for idx 0, we set dp[0][nums[0]] to true; [1,5,11,5]
    for (int i = 0; i < n; ++i) dp[i][0] = true;
    if (nums[0] <= sum) dp[0][sum] = true;

    for (int i = 1; i < n; ++i) {
      for (int j = 1; j <= sum; ++j) {
        // we can skip or include a position
        boolean skip = dp[i - 1][j];
        boolean include = false;
        if (nums[i] <= j) include = dp[i - 1][j - nums[i]];
        dp[i][j] = skip || include;
      }
    }

    int min = (int) 1e9;
    for (int i = 0; i <= sum; ++i) {
      if (dp[n - 1][i]) {
        min = Math.min(min, Math.abs(i - (sum - i)));
      }
    }
    return min;
  }

  private int findTargetSumWays(int idx, int target, int[] nums, int[][] dp) {
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

  public int coinChange(int[] coins, int amount) {
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

  public int coinChangeOptimal(int[] coins, int amount) {
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

  /*
   * dp on strings
   * */
  public String longestCommonSubsequence(String text1, String text2) {
    if (text1.equals(text2)) return text1;
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int j = 0; j < n; ++j) dp[0][j] = 0;
    for (int i = 0; i < m; ++i) dp[i][0] = 0;

    // [[0, 0, 0, 0],
    // [0, 1, 1, 1],
    // [0, 1, 1, 1],
    // [0, 1, 2, 2],
    // [0, 1, 2, 2],
    // [0, 1, 2, 3]]

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    int i = m, j = n;
    StringBuilder result = new StringBuilder();
    while (i > 0 && j > 0) {
      if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
        result.append(text1.charAt(i - 1));
        i = i - 1;
        j = j - 1;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        i = i - 1;
      } else {
        j = j - 1;
      }
    }
    return result.reverse().toString();
  }

  public int longestCommonSubsequenceRecur(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    if (m == 0 || n == 0) return 0;
    int[][] dp = new int[m + 1][n + 1];
    for (int[] d : dp) Arrays.fill(d, -1);
    return longestCommonSubsequenceRecur(m, n, s1, s2, dp);
  }

  public int longestCommonSubsequenceRecur(int i, int j, String s1, String s2, int[][] dp) {
    if (i == 0 || j == 0) return 0;
    if (dp[i][j] != -1) return dp[i][j];
    // a | a
    // both matches so we move the index
    if (s1.charAt(i - 1) == s2.charAt(j - 1))
      return dp[i][j] = 1 + longestCommonSubsequenceRecur(i - 1, j - 1, s1, s2, dp);
      // ac | ce
      //  i    j
      // we have a chance to match c and c if we move j only
      // ec | ce
      //  i    j
      // we have a chance to match e with e if we move i only
      // so, we explore both possibilities and get the max
    else return dp[i][j] = Math.max(
      longestCommonSubsequenceRecur(i - 1, j, s1, s2, dp),
      longestCommonSubsequenceRecur(i, j - 1, s1, s2, dp)
    );
  }

  public String longestCommonSubstring(String text1, String text2) {
    if (text1.equals(text2)) return text1;
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int j = 0; j < n; ++j) dp[0][j] = 0;
    for (int i = 0; i < m; ++i) dp[i][0] = 0;

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = 0;
        }
      }
    }
    StringBuilder result = new StringBuilder();

    int len = -(int) 1e9;
    int maxI = 0, maxJ = 0;
    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (len < dp[i][j]) {
          len = dp[i][j];
          maxI = i;
          maxJ = j;
        }
      }
    }
    int i = maxI, j = maxJ;
    while (i > 0 && j > 0) {
      if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
        result.append(text1.charAt(i - 1));
        i = i - 1;
        j = j - 1;
      } else break;
    }
    return result.reverse().toString();
  }

  public String shortestCommonSupersequence(String str1, String str2) {
    int m = str1.length(), n = str2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int j = 0; j < n; ++j) dp[0][j] = 0;
    for (int i = 0; i < m; ++i) dp[i][0] = 0;

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    //        c  a  b
    //   [[0, 0, 0, 0],
    // a [0, 0, 1, 1],
    // b [0, 0, 1, 2],
    // a [0, 0, 1, 2],
    // c [0, 1, 1, 2]]


    int i = m, j = n;
    StringBuilder result = new StringBuilder();
    while (i > 0 && j > 0) {
      // common char taken once
      if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
        result.append(str1.charAt(i - 1));
        i = i - 1;
        j = j - 1;
      } else if (dp[i - 1][j] > dp[i][j - 1]) { // we are removing either of st1 or st2, and we need to take that char too
        result.append(str1.charAt(i - 1));
        i = i - 1;
      } else {
        result.append(str2.charAt(j - 1));
        j = j - 1;
      }
    }
    while (i > 0) {
      result.append(str1.charAt(i - 1));
      i--;
    }
    while (j > 0) {
      result.append(str2.charAt(j - 1));
      j--;
    }
    return result.reverse().toString();
  }

  public int longestPalindromeSubsequence(String s) {
    // s = bbbab, if we reverse s and get the lcs between s and s1, we can find the ans
    int n = s.length();
    if (n < 2) return n;
    StringBuilder sb = new StringBuilder(s);
    String s1 = sb.reverse().toString();
    int[][] dp = new int[n + 1][n + 1];

    // so, the base case is when i == 0 || j == 0 we return 0
    for (int i = 0; i < n; ++i) dp[0][i] = 0; // first row to zero
    for (int i = 0; i < n; ++i) dp[i][0] = 0; // first col to zero

    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (s.charAt(i - 1) == s1.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[n][n];
  }

  public int minimumInsertionToMakeStringPalindrome(String s) {
    // don't touch the longest palindrome
    // try to insert the rest by reversing and putting in between to make it work
    // s = abcaa, lps = aaa, and we have bc which we reverse and insert
    // so, n - lps will be the ans
    int n = s.length();
    int lps = longestPalindromeSubsequence(s);
    return n - lps;
  }

  public int deleteOperationForTwoString(String word1, String word2) {
    // word1 = abcd, word2 = anc
    // so to make word1 into word2, we can keep the lcs intact,
    // remove the rest of the word1 and make n-lcs insert to word2
    int lcs = longestCommonSubsequenceRecur(word1, word2);
    int n1 = word1.length(), n2 = word2.length();
    return n1 - lcs + n2 - lcs;
  }

  public int distinctSubsequence(String s, String t) {
    int m = s.length(), n = t.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int[] i : dp) Arrays.fill(i, -1);
    return distinctSubsequence(m, n, s, t, dp);
  }

  private int distinctSubsequence(int i, int j, String s, String t, int[][] dp) {
    // nb: we use 0-based instead of -1 like if (j<0)
    if (j == 0) return 1; // since t is exhausted I have matched all chars with s
    if (i == 0) return 0; // since s is exhausted, there is still some matching to be done in t
    if (dp[i][j] != -1) return dp[i][j];
    // s = babgbag, t = bag
    //           i        j
    // so if i and j match we can take it and move on
    // also, let's say we want t[j] i.e., g to match with the g in the left of s[i]
    // so, we need to move i
    if (s.charAt(i - 1) == t.charAt(j - 1)) {
      return dp[i][j] = distinctSubsequence(i - 1, j - 1, s, t, dp) + distinctSubsequence(i - 1, j, s, t, dp);
    } else {
      // if two char doesn't match, I have to exhaust s since it's the bigger string;
      // there is some chance I can match s[i] with t[j] in the left of s[i]
      return dp[i][j] = distinctSubsequence(i - 1, j, s, t, dp);
    }
  }

  public int distinctSubsequenceTabulation(String s, String t) {
    int m = s.length(), n = t.length();
    int[][] dp = new int[m + 1][n + 1];
    // j==0 means t is empty dp[i][j] <- here j is 0
    for (int i = 0; i <= m; ++i) dp[i][0] = 1;
    for (int j = 1; j <= n; ++j) dp[0][j] = 0;

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    return dp[m][n];
  }

  public int editDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    if (m == 0) return n;
    if (n == 0) return m;
    int[][] dp = new int[m + 1][n + 1];
    for (int[] d : dp) Arrays.fill(d, -1);
    editDistance(m, n, word1, word2, dp);
    return dp[m][n];
  }

  private int editDistance(int i, int j, String word1, String word2, int[][] dp) {
    // base case
    //  horse ros
    // i       j
    // to make empty string to 'ro' how many operations do we need? 2 inserts that's why j+1
    // if (i < 0) return j + 1;
    if (i == 0) return j; // we shift -1 to 0
    //  horse   ros
    //   i     j
    // min operations to convert ho to empty string?
    // if (j < 0) return i + 1;
    if (j == 0) return i;
    if (dp[i][j] != -1) return dp[i][j];
    // horse ros
    // if two char matches, we don't do anything
    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
      return dp[i][j] = editDistance(i - 1, j - 1, word1, word2, dp);
    } else {
      // we need to perform insert, update, delete
      // insert operation
      // horse ros
      //     i   j
      // if we insert at word1[i+1] then word2[j] matches
      // so, we matched by inserting now we reduce j
      int insert = 1 + editDistance(i, j - 1, word1, word2, dp);

      // update operation
      // horse ros
      //     i   j
      // if we update word1[i] with s then word1[i] match with word2[j]
      // so, we move reduce both i & j
      int update = 1 + editDistance(i - 1, j - 1, word1, word2, dp);

      // delete operation
      // rorse ros
      // if we delete r from the first string, we get rose
      // so we move i
      int delete = 1 + editDistance(i - 1, j, word1, word2, dp);
      return dp[i][j] = Math.min(insert, Math.min(update, delete));
    }
  }

  public int editDistanceTabulation(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    if (m == 0) return n;
    if (n == 0) return m;
    int[][] dp = new int[m + 1][n + 1];
    // base case i == 0 return j; j == 0 return i
    for (int i = 0; i <= m; ++i) dp[i][0] = i;
    for (int j = 0; j <= n; ++j) dp[0][j] = j;

    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j]));
        }
      }
    }
    return dp[m][n];
  }

  /*
   * dp on stocks
   * */
  // Best Time to Buy and Sell Stock III
  public int maxProfit(int[] prices) {
    int n = prices.length;
    int[][][] dp = new int[n + 1][2][3];
    for (int i = n - 1; i >= 0; --i) {
      for (int buy = 0; buy <= 1; ++buy) {
        for (int cap = 1; cap <= 2; ++cap) {
          if (buy == 1) {
            dp[i][buy][cap] = Math.max(-prices[i] + dp[i + 1][0][cap], dp[i + 1][buy][cap]);
          } else {
            dp[i][buy][cap] = Math.max(prices[i] + dp[i + 1][1][cap - 1], dp[i + 1][0][cap]);
          }
        }
      }
    }
    return dp[0][1][2];
  }

  public int bestTimeToBuyAndSellStockWithCooldown(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 2][2];
    // for recursion, we start from 0 to n,
    // so, in tabulation we do the opposite i.e., n-1 to 0
    for (int i = n - 1; i >= 0; --i) {
      for (int buy = 0; buy <= 1; ++buy) {
        if (buy == 1) {
          dp[i][buy] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][buy]);
        } else {
          dp[i][buy] = Math.max(prices[i] + dp[i + 2][1], dp[i + 1][buy]);
        }
      }
    }
    return dp[0][1];
  }

  public int bestTimeToBuyAndSellStockWithTransactionFee(int[] prices, int fee) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2];
    // for recursion, we start from 0 to n,
    // so, in tabulation we do the opposite i.e., n-1 to 0
    for (int i = n - 1; i >= 0; --i) {
      for (int buy = 0; buy <= 1; ++buy) {
        if (buy == 1) {
          dp[i][buy] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][buy]);
        } else {
          // so after a transaction is complete, we subtract the fee
          dp[i][buy] = Math.max(prices[i] - fee + dp[i + 1][1], dp[i + 1][buy]);
        }
      }
    }
    return dp[0][1];
  }

  int bestTimeToBuyAndSellStockWithCooldown(int i, int buy, int[] prices, int n) {
    if (i >= n) return 0;
    int profit = -(int) 1e9;
    if (buy == 1) {
      profit = Math.max(-prices[i] + bestTimeToBuyAndSellStockWithCooldown(i + 1, 0, prices, n),
        bestTimeToBuyAndSellStockWithCooldown(i + 1, buy, prices, n));
    } else {
      // after selling we need a cooldown period that is why i+2
      profit = Math.max(prices[i] + bestTimeToBuyAndSellStockWithCooldown(i + 2, 1, prices, n),
        bestTimeToBuyAndSellStockWithCooldown(i + 1, buy, prices, n));
    }
    return profit;
  }

  public int longestIncreasingSubsequence(int[] nums) {
    // [10,9,2,5,3,7,101,18]
    // we can either include an element in LIS or skip it
    // depending on a previous element, if a previous element is lesser, then we might take it
    int n = nums.length;
    return longestIncreasingSubsequence(0, -1, nums, n);
  }

  public int longestIncreasingSubsequence(int i, int p, int[] nums, int n) {
    if (i == n) return 0;
    // if we skip the current index, then we just move forward and previous stays the same
    int skip = longestIncreasingSubsequence(i + 1, p, nums, n);
    // if we include it then len increase by one,
    // and we move to next index with current index being the previous
    // p = -1 means the first index
    int include = 0;
    if (p == -1 || nums[p] < nums[i]) include = 1 + longestIncreasingSubsequence(i + 1, i, nums, n);
    return Math.max(skip, include);
  }

  public int longestIncreasingSubsequenceTabulation(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n + 1][n + 1];
    // reverse of recursion i.e., i = n-1 to 0 and p = i-1 to -1
    for (int i = n - 1; i >= 0; --i) {
      for (int p = i - 1; p >= -1; --p) {
        int skip = dp[i + 1][p + 1];
        int include = 0;
        if (p == -1 || nums[p] < nums[i]) include = 1 + dp[i + 1][i + 1];
        dp[i][p + 1] = Math.max(skip, include);
      }
    }
    return dp[0][0];
  }

  public int lengthOfLIS(int i, int p, int[] nums, int n, int[][] dp) {
    // move the index by 1 position for previous
    if (i == n) return 0;
    if (dp[i][p + 1] != -1) return dp[i][p + 1];
    int skip = lengthOfLIS(i + 1, p, nums, n, dp);
    int include = 0;
    if (p == -1 || nums[p] < nums[i]) include = 1 + lengthOfLIS(i + 1, i, nums, n, dp);
    return dp[i][p + 1] = Math.max(skip, include);
  }

  public int lis(int[] nums) {
    int n = nums.length;
    if (n == 0) return 0;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int ans = 1;
    // 5,4,11,1,16,8
    // dp[i] = lis that ends at index i
    // so, in each index we are storing the lis that ends at that index
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        if (nums[i] > nums[j]) { // we can merge both if too
          if (dp[i] < dp[j] + 1) {
            dp[i] = dp[j] + 1;
            ans = Math.max(ans, dp[i]);
          }
        }
      }
    }
    return ans;
  }

  public int[] lisChain(int[] nums) {
    int n = nums.length;
    if (n == 0) return new int[0];
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int[] idxArr = new int[n];
    for (int i = 0; i < n; ++i) {
      idxArr[i] = i;
    }
    int ans = 1;
    int startIdx = 0;
    // 5,4,11,1,16,8
    // dp[i] = lis that ends at index i
    // so, in each index we are storing the lis that ends at that index
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        if (nums[i] > nums[j] && dp[i] < dp[j] + 1) { // we can merge both if too
          dp[i] = dp[j] + 1;
          if (ans < dp[i]) {
            ans = dp[i];
            // we need to keep trace of the index that updated the lis ending at i
            startIdx = i; // end of a lis chain
            idxArr[i] = j;
          }
        }
      }
    }
    int[] result = new int[ans];
    while (startIdx != idxArr[startIdx]) { // 5 != 2
      result[--ans] = nums[startIdx]; // store value 18
      startIdx = idxArr[startIdx]; // startIdx = 2
    }
    result[--ans] = nums[startIdx];
    return result;
  }

  // O(n^2) | O(n)
  public List<Integer> largestDivisibleSubset(int[] nums) {
    // the longest divisible subsequence; in LIS numbers were increasing,
    // but here we need numbers which are divisible by the previous one
    // [1,4,7,8,16]
    // when we pick [1,4,8] since 8/4 that means 8/1 is also true
    // [10,9,2,5,3,7,101,18]
    int n = nums.length;
    Arrays.sort(nums);
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int[] hash = new int[n];
    int ans = 1, lastIdx = 0;
    for (int i = 0; i < n; ++i) {
      hash[i] = i;
      for (int j = 0; j < i; ++j) {
        if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1;
          hash[i] = j;
        }
      }
      if (dp[i] > ans) {
        ans = dp[i];
        lastIdx = i;
      }
    }
    List<Integer> temp = new ArrayList<>();
    temp.add(nums[lastIdx]);
    while (hash[lastIdx] != lastIdx) {
      lastIdx = hash[lastIdx];
      temp.add(nums[lastIdx]);
    }
    return temp.reversed();
  }

  // O(n^2 * L) | O(n)
  public int longestStringChain(String[] words) {
    // words = ["a","b","ba","bca","bda","bdca"]
    // if we have a difference of 1 in consecutive string, then it's okay
    Arrays.sort(words, (a, b) -> a.length() - b.length());
    int n = words.length;
    int[] dp = new int[n];
    int ans = 0;
    Arrays.fill(dp, 1);
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        if (isSubsequence(words[i], words[j]) && dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1;
        }
      }
      ans = Math.max(ans, dp[i]);
    }
    return ans;
  }

  private boolean isSubsequence(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    if (m != n + 1) return false;
    int i = 0, j = 0;
    // abce abe
    // i    j
    while (i < m && j < n) {
      if (s1.charAt(i) == s2.charAt(j)) {
        i++;
        j++;
      } else {
        // didn't match but still we can match since s1 > s1 just move i
        i++;
      }
    }
    return j == n; // bigger string exhausted; we need to check if we have gone through the smaller string
  }

  public int numberOfLis(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int n = nums.length;
    int[] dp = new int[n]; // Length of LIS ending at index i
    Arrays.fill(dp, 1);
    int[] count = new int[n]; // Number of LIS ending at index i
    Arrays.fill(count, 1);

    int maxLength = 1; // Length of the longest increasing subsequence

    // 1,3,5,4,7
    // 1,2,3,3,4
    // 1,1,1,1,2
    // dp[4] is being updated with dp[0],d[1] and so on
    // when dp[3] tries to update dp[4] it sees the value to be already 4
    // so we have encountered 4 again, now increase the lisCount[i]
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < i; ++j) {
        if (nums[i] > nums[j]) {
          if (dp[i] < dp[j] + 1) {
            // Found a longer subsequence
            dp[i] = dp[j] + 1;
            count[i] = count[j]; // Reset count to match the new longer subsequence
          } else if (dp[i] == dp[j] + 1) {
            // Found another way to form the same length subsequence
            count[i] += count[j];
          }
        }
      }
      maxLength = Math.max(maxLength, dp[i]);
    }

    // Count all subsequences with the maximum length
    int result = 0;
    for (int i = 0; i < n; ++i) {
      if (dp[i] == maxLength) {
        result += count[i];
      }
    }

    return result;
  }
}
