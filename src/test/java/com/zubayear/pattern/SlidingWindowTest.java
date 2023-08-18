package com.zubayear.pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowTest {

    SlidingWindow sw;

    @BeforeEach
    void init() {
        sw = new SlidingWindow();
    }

    @Test
    void maxSlidingWindow() {
        int[] result = sw.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(Arrays.toString(result));
    }

    @Test
    void firstNeg() {
        int[] result = sw.firstNeg(new int[]{12, -1, -7, 8, -15, 30, 16, 28}, 3);
        System.out.println(Arrays.toString(result));
    }
}