package com.zubayear.dsaj.pattern;

import com.zubayear.dsaj.problems.SlidingWindow;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SlidingWindowTest {

    SlidingWindow slidingWindow;

    @BeforeEach
    void setUp() {
        slidingWindow = new SlidingWindow();
    }

    @AfterEach
    void tearDown() {
        slidingWindow = null;
    }

    @Test
    void firstNegative() {
        int[] nums = new int[]{12, -1, -7, 8, -15, 30, 16, 28};
        int[] ints = slidingWindow.firstNegative(nums, 3);
        System.out.println(Arrays.toString(ints));

    }

    @Test
    void longestSubstringKDistinct() {
        int result = slidingWindow.longestSubstringKDistinct("aaaa", 2);
        System.out.println(result);
    }
}