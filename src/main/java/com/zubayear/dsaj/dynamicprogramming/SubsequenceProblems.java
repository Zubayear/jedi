package com.zubayear.dsaj.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

public class SubsequenceProblems {
    // l416
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int m = nums.length, n = target + 1;
        boolean[][] cache = new boolean[m][n];
        // fill first column to 0 i.e. target = 0
        for (int i = 0; i < n; i++) {
            cache[i][0] = true;
        }
        if (nums[0] <= target) {
            cache[0][nums[0]] = true;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= target; j++) {
                boolean skip = cache[i-1][j];
                boolean include = false;
                if (nums[i] <= j) {
                    include = cache[i-1][j - nums[i]];
                }
                cache[i][j] = skip || include;
            }
        }
        return cache[m-1][target];
    }



}
