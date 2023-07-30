package com.zubayear.dynamicprogramming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DPSolutionTest {

    DPSolution dp;

    @BeforeEach
    public void init() {
        dp = new DPSolution();
    }

    @Test
    void uniquePath() {
        assertEquals(dp.uniquePaths(18, 18), 2333606220l);
        assertEquals(dp.uniquePaths(3, 7), 28);
    }

    @Test
    void uniquePathsWithObstacles() {
        int[][] obstacleGrid = {
                {0, 0, 0}, {0, 1, 0}, {0, 0, 0}
        };
        assertEquals(dp.uniquePathsWithObstaclesTabulation(obstacleGrid), 2);
        int[][] obstacleGrid2 = {{0, 1}, {0, 0}};
        assertEquals(dp.uniquePathsWithObstaclesTabulation(obstacleGrid2), 1);
    }

    @Test
    void minPathSum() {
        long[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        assertEquals(dp.minPathSum(grid), 7);
    }

    @Test
    void minimumTotal() {
        int[][] array = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };
        List<List<Integer>> triangle = convertTo2DList(array);
        assertEquals(dp.minimumTotal(triangle), 11);
//        int[][] array2 = {
//                {-10}
//        };
//        List<List<Integer>> triangle = convertTo2DList(array2);
//        assertEquals(dp.minimumTotal(triangle), -10);
    }

    List<List<Integer>> convertTo2DList(int[][] array) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : array) {
            result.add(Arrays.asList(Arrays.stream(row).boxed().toArray(Integer[]::new)));
        }
        return result;
    }

    @Test
    void coinChange() {
        assertEquals(dp.coinChange(new int[]{1, 2, 5}, 11), 3);
        assertEquals(dp.coinChange(new int[]{2}, 3), -1);
        assertEquals(dp.coinChange(new int[]{1, 2, 5, 10, 20, 50, 100, 200}, 2456), 0);
    }

    @Test
    void x() {
        dp.x();
    }

    @Test
    void longestCommonSubsequence() {
        assertEquals(dp.longestCommonSubsequence("abcde", "ace"), 3);
        assertEquals(dp.longestCommonSubsequence("abc", "abc"), 3);
        assertEquals(dp.longestCommonSubsequence("abc", "def"), 0);
    }

    @Test
    void uniquePaths() {
    }

    @Test
    void uniquePathsWithObstaclesTabulation() {
    }

    @Test
    void findMinimumNotes() {
        int[] notes = { 1, 2, 5 };
        int amount = 11;

        Map<Integer, Integer> notesUsed = new HashMap<>();
        int minNotes = dp.findMinimumNotes(notes, amount, notesUsed);
        System.out.println(minNotes);
        System.out.println(notesUsed);
    }
}