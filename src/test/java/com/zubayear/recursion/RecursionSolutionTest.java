package com.zubayear.recursion;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecursionSolutionTest {

    RecursionSolution rs;

    @BeforeEach
    void setUp() {
        rs = new RecursionSolution();
    }

    @AfterEach
    void tearDown() {
        rs = null;
    }

    @Test
    void printName() {
        rs.printName(0, 5);
    }

    @Test
    void oneToN() {
        rs.oneToN(1, 5);
    }

    @Test
    void nToOne() {
        rs.nToOne(1, 5);
    }

    @Test
    void sum() {
        int sum = rs.sum(new int[]{1, 2, 3, 4});
        System.out.println(sum);
    }

    @Test
    void reverse() {
        int[] numbers = new int[]{1,2,3,4,5,6,7};
        rs.reverse(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    @Test
    void palindrome() {
        Assertions.assertTrue(rs.palindrome("madam"));
        Assertions.assertFalse(rs.palindrome("king"));
        Assertions.assertTrue(rs.palindrome("121"));
        Assertions.assertFalse(rs.palindrome("madsm"));
    }

    @Test
    void reverseStr() {
        String str = "king";
        char[] chars = str.toCharArray();
        rs.reverseStr(chars);
        System.out.println(chars);
    }

    @Test
    void subsequence() {
        rs.subsequence(new int[]{3,1,2});
    }

    @Test
    void mergeSort() {
        int[] numbers = {8, 7, 1, 9, 4, 5, 3};
        rs.mergeSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    @Test
    void combinationSum() {
        int[] candidates = new int[]{1,2,3};
        int target = 4;
        List<List<Integer>> result = rs.combinationSum(candidates, target);
        System.out.println(result);
    }

    @Test
    void subsets() {
        List<List<Integer>> subsets = rs.subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }

    @Test
    void subsetsWithDup() {
        List<List<Integer>> subsets = rs.subsetsWithDup(new int[]{1, 2, 2});
        System.out.println(subsets);
    }

    @Test
    void permute() {
        List<List<Integer>> permute = rs.permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }
}