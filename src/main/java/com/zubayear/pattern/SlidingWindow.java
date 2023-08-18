package com.zubayear.pattern;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int ws = 0, we = 0, size = nums.length;
        if (k > size) return new int[]{Arrays.stream(nums).max().getAsInt()};
        int[] result = new int[size - k + 1];
        Deque<Integer> q = new LinkedList<>();
        while (we < size) {
            // calculation
            // before insert remove value < nums[we]
            // elements < nums[we] won't be of any use so we remove them before pushing nums[we]
            while (!q.isEmpty() && q.peekLast() < nums[we]) q.pollLast();
            q.add(nums[we]);
            if (we - ws + 1 < k) we++;

            else if (we - ws + 1 == k) {
                // ans from calculation
                Integer peek = q.peek();
                result[ws] = peek;
                // slide the window
                // we have to remove the calculation
                if (peek == nums[ws]) q.pollFirst();
                ws++;
                we++;
            }
        }
        return result;
    }

    public int[] firstNeg(int[] nums, int k) {
        int ws = 0, we = 0, size = nums.length;
        int[] result = new int[size - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        // -8 2 3 -6 10
        while (we < size) {
            // calculation
            if (nums[we] < 0) q.offer(nums[we]); // -8, 2
            if (we - ws + 1 < k) we++;
            else if (we - ws + 1 == k) {
                // ans <- calculation
                // slide
                if (q.isEmpty()) result[ws] = 0;
                else {
                    int val = q.peek();
                    result[ws] = val;
                    if (val == nums[ws]) q.poll();
                }
                ws++;
                we++;
            }
        }
        return result;
    }

}

