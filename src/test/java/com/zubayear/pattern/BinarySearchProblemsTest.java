package com.zubayear.pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchProblemsTest {

    BinarySearchProblems bs;

    @BeforeEach
    void setUp() {
        bs = new BinarySearchProblems();
    }

    @AfterEach
    void tearDown() {
        bs = null;
    }

    @Test
    void rotationTime() {
//        int[] nums = {3,4,5,1,2};
        int[] nums = {11,13,15,17};
        int i = bs.rotationTime(nums);
        System.out.println(i);
    }

    @Test
    void searchInRotatedSorted() {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int i = bs.searchInRotatedSorted(nums, target);
        System.out.println(i);
    }
}