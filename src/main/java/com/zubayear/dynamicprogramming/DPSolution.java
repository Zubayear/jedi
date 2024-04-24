package com.zubayear.dynamicprogramming;

import java.util.*;

public class DPSolution {
    public long uniquePaths(int m, int n) {
        /*
        recursion
        if (m == 0 || n == 0) return 0;
        if (m == 1 && n == 1) return 1;
        // down
        return uniquePaths(m-1, n) + uniquePaths(m, n-1);
        */
//      return uniquePathsHelper(m, n, new HashMap<>());
        /*
        memoization
        long[][] dp = new long[m+1][n+1];
        for (long[] row : dp) Arrays.fill(row, -1);
        return uniquePathsHelper(m, n, dp);
        */
        /*tabulation*/
        long[][] dp = new long[m][n];
//        for (long[] row : dp) Arrays.fill(row, -1);
        return uniquePathsTabulation(m, n, dp);
    }

    private long uniquePathsHelper(int m, int n, long[][] dp) {
        if (m == 0 || n == 0) return 0;
        if (m == 1 && n == 1) return 1;
        if (dp[m][n] != -1) return dp[m][n];
        dp[m][n] = uniquePathsHelper(m - 1, n, dp) + uniquePathsHelper(m, n - 1, dp);
        return dp[m][n];
    }

    private long uniquePathsTabulation(int m, int n, long[][] dp) {
        /*dp[1][1] = 1;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i + 1 <= m) dp[i + 1][j] += dp[i][j];
                if (j + 1 <= n) dp[i][j + 1] += dp[i][j];
            }
        }
        return dp[m][n];*/
        int[] prev = new int[n]; // col
        for (int i = 0; i < m; i++) {
            int[] tmp = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    tmp[j] = 1;
                    continue;
                }
                int up = 0, left = 0;
                if (i > 0) up = prev[j];
                if (j > 0) left = tmp[j - 1];
                tmp[j] = up + left;
            }
            prev = tmp;
        }
        return prev[n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return uniquePathsWithObstaclesHelper(obstacleGrid, m - 1, n - 1, dp);
    }

    private int uniquePathsWithObstaclesHelper(int[][] obstacleGrid, int m, int n, int[][] dp) {
        if (m >= 0 && n >= 0 && obstacleGrid[m][n] == 1) return 0; // there is an obstacle
        // we can start at bottom right and move either up or left
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;
        if (dp[m][n] != -1) return dp[m][n];
        int up = uniquePathsWithObstaclesHelper(obstacleGrid, m - 1, n, dp);
        int left = uniquePathsWithObstaclesHelper(obstacleGrid, m, n - 1, dp);
        dp[m][n] = up + left;
        return dp[m][n];
    }

    int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
//        for (int[] row : dp) Arrays.fill(row, -1);
//        dp[0][0] = 1;
        int up = 0, left = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else if (i == 0 && j == 0) dp[i][j] = 1;
                else {
                    if (i > 0) up = dp[i - 1][j];
                    if (j > 0) left = dp[i][j - 1];
                    dp[i][j] = up + left;
                }
            }
        }
        for (int[] i : dp)
            System.out.println(Arrays.toString(i));
        return dp[m - 1][n - 1];
    }

    long minPathSum(long[][] grid) {
        int i = grid.length, j = grid[0].length;
        long[][] dp = new long[i][j];
        for (long[] row : dp) Arrays.fill(row, -1);
        return minPathSumHelper(grid, i - 1, j - 1, dp);
    }

    private long minPathSumHelper(long[][] grid, int i, int j, long[][] dp) {
        if (i == 0 && j == 0) return grid[0][0]; // we are at the destination
        if (i < 0 || j < 0) return Integer.MAX_VALUE;
        if (dp[i][j] != -1) return dp[i][j];
        long up = grid[i][j] + minPathSumHelper(grid, i - 1, j, dp); // we take grid[i][j] and ask the function to find the rest
        long left = grid[i][j] + minPathSumHelper(grid, i, j - 1, dp);
        dp[i][j] = Math.min(left, up);
        return dp[i][j];
    }

    // L120 Triangle
    int minimumTotal(List<List<Integer>> triangle) {
        // [[2],[3,4],[6,5,7],[4,1,8,3]]
        int m = triangle.size();
        int n = triangle.get(triangle.size() - 1).size();
        System.out.printf("m = %d, n = %d\n", m, n);
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return minimumTotalHelper(triangle, 0, 0, dp);
    }

    private int minimumTotalHelper(List<List<Integer>> triangle, int i, int j, int[][] dp) {
        // last row
        if (i == triangle.get(triangle.size() - 1).size() - 1) return triangle.get(triangle.size() - 1).get(j);
        if (dp[i][j] != -1) return dp[i][j];
        int down = triangle.get(i).get(j) + minimumTotalHelper(triangle, i + 1, j, dp);
        int diag = triangle.get(i).get(j) + minimumTotalHelper(triangle, i + 1, j + 1, dp);
        dp[i][j] = Math.min(down, diag);
        return dp[i][j];
    }

    int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int result = coinChangeHelper(coins, coins.length - 1, amount, dp);
        return result == 1e9 ? -1 : result;
    }

    private int coinChangeHelper(int[] coins, int i, int amount, int[][] dp) {
        if (i == 0) {
            if (amount % coins[i] == 0) return amount / coins[i];
            else return (int) 1e9;
        }
        // we'll just check before calling the function
        if (dp[i][amount] != -1) return dp[i][amount];
        int notTake = coinChangeHelper(coins, i - 1, amount, dp);
        int take = Integer.MAX_VALUE;
        // if we can make up the amount with coins
        if (coins[i] <= amount) take = 1 + coinChangeHelper(coins, i, amount - coins[i], dp);
        dp[i][amount] = Math.min(take, notTake);
        return dp[i][amount];
    }

    public int coinChangeTab(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i < amount + 1; ++i) {
            if (i % coins[0] == 0) dp[0][i] = i / coins[0];
            else dp[0][i] = (int) 1e9;
        }
        for (int i = 1; i < coins.length; ++i) {
            for (int j = 0; j <= amount; ++j) {
                // not taking
                int nt = dp[i - 1][j];

                // taking
                int t = Integer.MAX_VALUE;
                if (coins[i] <= j) t = 1 + dp[i][j - coins[i]];
                dp[i][j] = Math.min(t, nt);
            }
        }
        int res = dp[coins.length - 1][amount];
        if (res == 1e9) return -1;
        return res;
    }

    public void x() {
//        int[] notes = new int[]{2000, 500, 200, 100, 50, 20, 10, 5, 1};
        int[] notes = new int[]{1, 2, 5, 10, 20, 50, 100, 200};
        int amount = 2456;
        int[] noteCounter = new int[notes.length];
        for (int i = notes.length - 1; i >= 0; i--) {
            if (amount >= notes[i]) {
                noteCounter[i] = amount / notes[i];
                amount = amount % notes[i];
            }
        }

        for (int i = 0; i < noteCounter.length; i++) {
            if (noteCounter[i] != 0) {
                System.out.printf("Note = %d, Count = %d\n", notes[i], noteCounter[i]);
            }
        }
    }

    public int combinationSum4(int[] nums, int target) {
        // this is coin change ii
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        for (int i = 0; i <= target; ++i) {
            if (i % nums[0] == 0) dp[0][i] = 1;
        }
        for (int j = 1; j <= target; ++j) {
            for (int i = 0; i < n; ++i) {
                int nt = dp[j - 1][i];
                int t = 0;
                if (nums[i] <= j) t = dp[j][i - nums[j]];
                dp[j][i] = nt + t;
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[n - 1][target];
    }

    int longestCommonSubsequence(String text1, String text2) {
        if (text1.equals(text2)) return text1.length();
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        // acd ced
        return longestCommonSubsequenceHelper(text1, text2, text1.length() - 1, text2.length() - 1, dp);
    }

    private int longestCommonSubsequenceHelper(String text1, String text2, int i, int j, int[][] dp) {
        if (i < 0 || j < 0) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        // char matched so we take it and reduce by 1
        if (text1.charAt(i) == text2.charAt(j))
            return dp[i][j] = 1 + longestCommonSubsequenceHelper(text1, text2, i - 1, j - 1, dp);
            // we go to both branch
        else
            return dp[i][j] = Math.max(longestCommonSubsequenceHelper(text1, text2, i, j - 1, dp), longestCommonSubsequenceHelper(text1, text2, i - 1, j, dp));
    }

    private int lscTabulation(String s1, String s2) {
        int n = s1.length(); // i
        int m = s2.length(); // j
        // if i == 0 || j == 0
        int[][] dp = new int[n][m];
        for (int i = 0; i <= m; ++i) dp[0][i] = 0;
        for (int i = 0; i <= n; ++i) dp[i][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1]; // diag
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n][m];
    }

    public int findMinimumNotes(int[] notes, int amount, Map<Integer, Integer> notesUsed) {
        int[] dp = new int[amount + 1];
        int[] lastNote = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int note : notes) {
            for (int target = 1; target <= amount; ++target) {
                if (note <= target) {
                    dp[target] = Math.min(dp[target], 1 + dp[target - note]);
                    lastNote[target] = note;
                }
            }
        }
        System.out.println(Arrays.toString(lastNote));

        int remainingAmount = amount;
        while (remainingAmount > 0) {
            int note = lastNote[remainingAmount];
            notesUsed.put(note, notesUsed.getOrDefault(note, 0) + 1);
            remainingAmount -= note;
        }
        if (dp[amount] != Integer.MAX_VALUE) return dp[amount];
        else return -1;
    }

    public int ninjaTraining(int[][] points) {
        int m = points.length;
        // 1 2 3
        // 4 5 6
        // 7 8 9
        int[][] dp = new int[m + 1][4];
        for (int[] row : dp) Arrays.fill(row, -1);
        return ninjaTraining(points, m - 1, m, dp);
    }

    private int ninjaTraining(int[][] points, int day, int last, int[][] dp) {
        if (dp[day][last] != -1) return dp[day][last];

        if (day == 0) {
            // run a loop in 1, 2, 3
            int max = 0;
            for (int i = 0; i < points[0].length; ++i) {
                if (i != last) max = Math.max(max, points[0][i]);
            }
            return dp[day][last] = max;
        }

        int max = -1;
        for (int i = 0; i < points[0].length; ++i) {
            if (i != last) {
                int point = points[day][i] + ninjaTraining(points, day - 1, i, dp);
                max = Math.max(point, max);
            }
        }
        return dp[day][last] = max;
    }

    private int ninjaTrainingTab(int[][] points) {
        int[][] dp = new int[points.length][4];
        // (day, last)
        // (0,0) (0,1) (0,2) (0,3)
        // (0,0) -> we take max(points[0][1], points[0][2]
        // (0,1) -> max(points[0][0], points[0][2])
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(Math.max(points[0][0], points[0][1]), points[0][2]); // if one row

        for (int day = 1; day < points.length; ++day) { // first row filled
            for (int last = 0; last < 4; ++last) {
                dp[day][last] = 0;
                for (int task = 0; task < 2; ++task) {
                    if (task != last) dp[day][last] = Math.max(points[day][task] + dp[day - 1][task], dp[day][last]);
                }
            }
        }
        return dp[points.length - 1][3];
    }

    public int lengthOfLIS(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        list.add(nums[0]);
        int ans = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > list.get(list.size() - 1)) {
                // if the next value is greater than the one we pushed previously to list
                list.add(nums[i]);
                ans++;
            } else {
                // need to find the right place to insert
                int idx = Collections.binarySearch(list, nums[i]);
                if (idx < 0) idx = -(idx + 1);
                list.set(idx, nums[i]);
            }
        }
        return ans;
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int N = words.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 0; i < N; ++i) {
//            dp[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (compare(words[j], words[i]) && 1 + dp[j] > dp[i]) dp[i] = 1 + dp[j];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    private boolean compare(String s1, String s2) {
        if (s1.length() + 1 != s2.length()) return false;
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s1.charAt(j)) i++;
            j++;
        }
        return i == s1.length();
    }
}
