package com.zubayear.dsaj.dynamicprogramming;

import java.util.Arrays;

public class FibonacciProblems {
    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     * <p>
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        /*
         * start at n
         * */
        if (n == 0 || n == 1) {
            return 1;
        }
        int oneJump = climbStairs(n - 1);
        int twoJump = climbStairs(n - 2);
        return oneJump + twoJump;
    }

    /**
     * What is the changing parameter?
     * Since it's n we create a cache of size n
     */
    public int climbStairsMemo(int n) {

        int[] cache = new int[n];
        Arrays.fill(cache, -1);
        cache[0] = cache[1] = 1;
        return climbStairsMemoHelper(n, cache);
    }

    public int climbStairsMemoHelper(int n, int[] cache) {
        if (cache[n] != -1) {
            return cache[n];
        }
        return cache[n] = climbStairsMemoHelper(n - 1, cache) + climbStairsMemoHelper(n - 2, cache);
    }

    public int climbStairsTabulation(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = cache[1] = 1;
        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }

    /**
     * cache[i] = cache[i-1] + cache[i-2];
     * curr           prev2        prev1
     *
     * @param n
     * @return
     */
    public int climbStairsOpt(int n) {
        int prev1 = 1, prev2 = 1;
        for (int i = 2; i <= n; ++i) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    /*==================================================================================*/

    public int minCostClimbingStairs(int[] cost) {
        // return Math.min(minCostClimbingStairs(cost, cost.length-1), minCostClimbingStairs(cost, cost.length-2));
        // memo
        int[] cache = new int[cost.length];
        Arrays.fill(cache, -1);
        cache[0] = cost[0];
        cache[1] = cost[1];
        return Math.min(minCostClimbingStairs(cost, cost.length - 1, cache), minCostClimbingStairs(cost, cost.length - 2, cache));
    }

    private int minCostClimbingStairs(int[] cost, int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return cost[n];
        }
        return cost[n] + Math.min(minCostClimbingStairs(cost, n - 1), minCostClimbingStairs(cost, n - 2));
    }

    public int minCostClimbingStairs(int[] cost, int n, int[] cache) {
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return cache[n];
        }
        if (cache[n] != -1) {
            return cache[n];
        }
        return cache[n] = cost[n] + Math.min(minCostClimbingStairs(cost, n - 1, cache), minCostClimbingStairs(cost, n - 2, cache));
    }

    /*==================================================================================*/
    public int rob(int[] nums) {
        return rob(nums.length - 1, nums);
    }

    private int rob(int i, int[] nums) {
        // without memoization
        if (i == 0) { // didn't pick i = 1
            return nums[i];
        }
        if (i < 0) {
            return 0;
        }
        int x = nums[i] + rob(i - 2, nums); // can't take adjacent
        int y = 0 + rob(i - 1, nums);
        return Math.max(x, y);
    }

    private int robMemo(int i, int[] nums, int[] cache) {
        if (cache[i] != -1) {
            return cache[i];
        }
        int x = nums[i] + robMemo(i - 2, nums, cache); // can't take adjacent
        int y = robMemo(i - 1, nums, cache);
        return cache[i] = Math.max(x, y);
    }

    public int robTabulation(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] cache = new int[nums.length];
        cache[0] = nums[0];
        cache[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int x = nums[i] + cache[i-2];
            int y = cache[i-1];
            cache[i] = Math.max(x, y);
        }
        return cache[cache.length-1];
    }

    public int robOpt(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int prev2 = nums[0], prev1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int curr = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

}
