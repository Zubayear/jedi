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
}
