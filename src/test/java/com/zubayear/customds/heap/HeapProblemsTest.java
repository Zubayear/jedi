package com.zubayear.customds.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapProblemsTest {

    HeapProblems hp;

    @BeforeEach
    void init() {
        hp = new HeapProblems();
    }

    @Test
    void findKthLargest() {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        Assertions.assertEquals(hp.findKthLargest(nums, k), 5);
    }
}