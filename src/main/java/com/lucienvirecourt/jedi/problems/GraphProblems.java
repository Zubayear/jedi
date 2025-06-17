package com.lucienvirecourt.jedi.problems;

import java.util.*;

public class GraphProblems {
  public static List<Integer> bfs(List<List<Integer>> graph, int start) {
    Deque<Integer> queue = new ArrayDeque<>();
    List<Integer> result = new ArrayList<>();
    int v = graph.size();
    boolean[] visited = new boolean[v];
    queue.offer(start);
    visited[start] = true;
    while (!queue.isEmpty()) {
      int current = queue.poll();
      result.add(current);
      graph.get(current).forEach(i -> {
        if (!visited[i]) {
          queue.offer(i);
          visited[i] = true;
        }
      });
    }
    return result;
  }

  public static List<Integer> dfs(List<List<Integer>> graph, int start) {
    var result = new ArrayList<Integer>();
    int v = graph.size();
    boolean[] visited = new boolean[v];
    dfs(graph, start, visited, result);
    return result;
  }

  private static void dfs(List<List<Integer>> graph, int start, boolean[] visited, List<Integer> result) {
    if (visited[start]) return;
    visited[start] = true;
    result.add(start);
    graph.get(start).forEach(i -> {
      if (!visited[i]) {
        dfs(graph, i, visited, result);
      }
    });
  }

  public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    boolean[] visited = new boolean[n];
    int result = 0;
    for (int i = 0; i < n; ++i) {
      if (!visited[i]) {
        result++;
        findCircleNum(isConnected, visited, i);
      }
    }
    return result;
  }

  private void findCircleNum(int[][] isConnected, boolean[] visited, int node) {
    if (visited[node]) return;
    visited[node] = true;
    for (int i = 0; i < isConnected.length; ++i) {
      if (isConnected[node][i] == 1) {
        findCircleNum(isConnected, visited, i);
      }
    }
  }

  public void surroundedRegion(char[][] board) {
    int m = board.length, n = board[0].length;
    // we remove the corner values out of the equation
    // then work towards solving the rest
    for (int j = 0; j < n; ++j) {
      if (board[0][j] == 'O') surroundedRegion(board, 0, j, m, n);
    }

    for (int j = 0; j < n; ++j) {
      if (board[m - 1][j] == 'O') surroundedRegion(board, m - 1, j, m, n);
    }

    for (int i = 0; i < m; ++i) {
      if (board[i][0] == 'O') surroundedRegion(board, i, 0, m, n);
    }

    for (int i = 0; i < m; ++i) {
      if (board[i][n - 1] == 'O') surroundedRegion(board, i, n - 1, m, n);
    }

    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (board[i][j] == 'O') board[i][j] = 'X';
      }
    }

    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (board[i][j] == 'D') board[i][j] = 'O';
      }
    }

  }

  private void surroundedRegion(char[][] board, int i, int j, int m, int n) {
    if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O') return;
    board[i][j] = 'D';
    surroundedRegion(board, i - 1, j, m, n);
    surroundedRegion(board, i, j - 1, m, n);
    surroundedRegion(board, i + 1, j, m, n);
    surroundedRegion(board, i, j + 1, m, n);
  }

  public int numEnclaves(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for (int j = 0; j < n; ++j) {
      if (grid[0][j] == 1) numEnclaves(grid, 0, j, m, n);
    }

    for (int j = 0; j < n; ++j) {
      if (grid[m - 1][j] == 1) numEnclaves(grid, m - 1, j, m, n);
    }

    for (int i = 0; i < m; ++i) {
      if (grid[i][0] == 1) numEnclaves(grid, i, 0, m, n);
    }

    for (int i = 0; i < m; ++i) {
      if (grid[i][n - 1] == 1) numEnclaves(grid, i, n - 1, m, n);
    }

    int ans = 0;
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (grid[i][j] == 1) ans++;
      }
    }
    return ans;

  }

  private void numEnclaves(int[][] grid, int i, int j, int m, int n) {
    if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1) return;
    grid[i][j] = 0;
    numEnclaves(grid, i - 1, j, m, n);
    numEnclaves(grid, i, j - 1, m, n);
    numEnclaves(grid, i + 1, j, m, n);
    numEnclaves(grid, i, j + 1, m, n);
  }
}
