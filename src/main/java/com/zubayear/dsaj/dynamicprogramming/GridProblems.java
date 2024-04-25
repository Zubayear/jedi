package com.zubayear.dsaj.dynamicprogramming;

public class GridProblems {
    
    public int uniquePaths(int m, int n) {
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    cache[i][j] = 1;
                } else {
                    int up = i > 0 ? cache[i-1][j] : 0;
                    int left = j > 0 ? cache[i][j-1] : 0;
                    cache[i][j] = up + left;
                }
            }
        }
        return cache[m-1][n-1];
    }

    public int uniquePathsOpt(int m, int n) {
        int[] prev = new int[n];
        for (int i = 0; i < m; i++) {
            int[] temp = new int[m];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    temp[j] = 1;
                } else {
                    int up = i > 0 ? prev[j] : 0;
                    int left = j > 0 ? temp[j-1] : 0;
                    temp[j] = up + left;
                }
            }
            prev = temp;
        }
        return prev[n-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    cache[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    cache[i][j] = 1;
                } else {
                    int up = i > 0 ? obstacleGrid[i-1][j] : 0;
                    int left = j > 0 ? obstacleGrid[i][j-1] : 0;
                    cache[i][j] = up + left;
                }
            }
        }
        return cache[m-1][n-1];
    }


}
