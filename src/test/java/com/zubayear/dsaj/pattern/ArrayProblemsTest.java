package com.zubayear.dsaj.pattern;

import com.zubayear.dsaj.problems.ArrayProblems;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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