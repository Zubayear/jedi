package com.zubayear.dsaj.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

public class GridProblems {

    public int uniquePaths(int m, int n) {
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    cache[i][j] = 1;
                } else {
                    int up = i > 0 ? cache[i - 1][j] : 0;
                    int left = j > 0 ? cache[i][j - 1] : 0;
                    cache[i][j] = up + left;
                }
            }
        }
        return cache[m - 1][n - 1];
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
                    int left = j > 0 ? temp[j - 1] : 0;
                    temp[j] = up + left;
                }
            }
            prev = temp;
        }
        return prev[n - 1];
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
                    int up = i > 0 ? obstacleGrid[i - 1][j] : 0;
                    int left = j > 0 ? obstacleGrid[i][j - 1] : 0;
                    cache[i][j] = up + left;
                }
            }
        }
        return cache[m - 1][n - 1];
    }

    // l64
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] cache = new int[m][n];
        for (int[] c : cache) {
            Arrays.fill(c, -1);
        }
        return minPathSumMemoization(grid, m - 1, n - 1, cache);
    }

    // O(M*N)
    public int minPathSumMemoization(int[][] grid, int i, int j, int[][] cache) {
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (i < 0 || j < 0) {
            return (int) 1e9;
        }
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        int left = grid[i][j] + minPathSumMemoization(grid, i, j - 1, cache);
        int up = grid[i][j] + minPathSumMemoization(grid, i - 1, j, cache);
        return cache[i][j] = Math.min(left, up);
    }

    public int minPathSumTabulation(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    cache[i][j] = grid[0][0];
                } else {
                    int up = 0;
                    if (i > 0) {
                        up = grid[i][j] + cache[i - 1][j];
                    } else {
                        up = (int) 1e9;
                    }
                    int left = 0;
                    if (j > 0) {
                        left = grid[i][j] + cache[i][j - 1];
                    } else {
                        left = (int) 1e9;
                    }
                    cache[i][j] = Math.min(up, left);
                }
            }
        }
        return cache[m - 1][n - 1];
    }

    /*=======================================*/
    // l120
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size(), n = triangle.get(triangle.size() - 1).size();
        int[][] cache = new int[m][n];
        for (int[] c : cache) {
            Arrays.fill(c, -1);
        }
        return minimumTotalMemoization(triangle, 0, 0, cache);
    }

    public int minimumTotalMemoization(List<List<Integer>> triangle, int i, int j, int[][] cache) {
        if (i == triangle.size() - 1) {
            return triangle.get(triangle.size() - 1).get(j);
        }
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        int down = triangle.get(i).get(j) + minimumTotalMemoization(triangle, i + 1, j, cache);
        int diagonal = triangle.get(i).get(j) + minimumTotalMemoization(triangle, i + 1, j + 1, cache);
        return cache[i][j] = Math.min(down, diagonal);
    }

    public int minimumTotalTabulation(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] cache = new int[n][n];
        // last row same
        for (int i = 0; i < n; i++) {
            cache[n - 1][i] = triangle.get(n - 1).get(i);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j >= 0; --j) {
                int down = triangle.get(i).get(j) + cache[i + 1][j];
                int diagonal = triangle.get(i).get(j) + cache[i + 1][j + 1];
                cache[i][j] = Math.min(down, diagonal);
            }
        }
        return cache[0][0];
    }

    /*=======================================*/
    // l931
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        for (int[] c : cache) {
            Arrays.fill(c, -1);
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int ans = minFallingPathSumMemoization(matrix, m - 1, n - 1, cache);
            result = Math.min(result, ans);
        }
        return result;
    }

    public int minFallingPathSumMemoization(int[][] matrix, int i, int j, int[][] cache) {
        if (j < 0 || j >= matrix[0].length) {
            return (int) -1e9;
        }
        if (i == 0) {
            return matrix[0][j];
        }
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        int up = matrix[i][j] + minFallingPathSumMemoization(matrix, i - 1, j, cache);
        int leftDiagonal = matrix[i][j] + minFallingPathSumMemoization(matrix, i - 1, j - 1, cache);
        int rightDiagonal = matrix[i][j] + minFallingPathSumMemoization(matrix, i - 1, j + 1, cache);
        return cache[i][j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
    }

    public int minFallingPathSumTabulation(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        for (int i = 0; i < n; i++) {
            cache[0][i] = matrix[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int up = matrix[i][j] + cache[i - 1][j];
                int leftDiagonal = matrix[i][j];
                if (j - 1 >= 0) {
                    leftDiagonal += cache[i - 1][j - 1];
                } else {
                    leftDiagonal += (int) -1e9;
                }
                int rightDiagonal = matrix[i][j];
                if (j + 1 < n) {
                    rightDiagonal += cache[i - 1][j + 1];
                } else {
                    rightDiagonal += (int) -1e9;
                }
                cache[i][j] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }
        }

        // min values will be stored in last row
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, cache[m - 1][i]);
        }
        return min;
    }
}
