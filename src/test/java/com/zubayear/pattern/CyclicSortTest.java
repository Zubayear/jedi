package com.zubayear.pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CyclicSortTest {

    CyclicSort cs;

    @BeforeEach
    void setUp() {
        cs = new CyclicSort();
    }

    @AfterEach
    void tearDown() {
        cs = null;
    }

    @Test
    void sort() {
        int[] nums = {5, 4, 1, 3, 2};
        cs.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    void missingNumber() {
        int[] nums = {3,0,1};
        System.out.println(cs.missingNumber(nums));
    }
}