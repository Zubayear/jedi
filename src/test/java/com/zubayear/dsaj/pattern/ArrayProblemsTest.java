package com.zubayear.dsaj.pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayProblemsTest {

    ArrayProblems arrayProblems;

    @BeforeEach
    void setUp() {
        arrayProblems = new ArrayProblems();
    }

    @AfterEach
    void tearDown() {
        arrayProblems = null;
    }

    @Test
    void replaceElements() {
//        int[] result = arrayProblems.replaceElements(new int[]{17, 18, 5, 4, 6, 1});
//        System.out.println(Arrays.toString(result));
        arrayProblems.longestCommonPrefix(new String[]{"flower","faow","flight"});
    }
}