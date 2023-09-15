package com.zubayear.pattern;

import java.util.Arrays;

public class CyclicSort {
    public void sort(int[] nums) {
        // values from [1,n]
        // 0  1  2  3  4
        // 5, 4, 1, 3, 2
        int start = 0;
        while (start < nums.length) {
            int idx = nums[start] - 1; // since array is 0 based so 5-1=4
            if (nums[idx] != nums[start]) {
                // value of nums[4] suppose to be 5
                // but we can see the value is 2 so, swap
                int tmp = nums[idx];
                nums[idx] = nums[start];
                nums[start] = tmp;
            } else start++;
        }
    }

    public int missingNumber(int[] nums) {
        int n = nums.length, start = 0;
        while (start < n) {
            int idx = nums[start];
            if (nums[start] < n &&nums[idx] != nums[start]) {
                int tmp = nums[start];
                nums[start] = nums[idx];
                nums[idx] = tmp;
            } else start++;
        }
        for (int i = 0; i < n; ++i) {
            if (i != nums[i]) return i;
        }
        return n;
    }
}
