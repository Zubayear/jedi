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

  public static int findCircleNum(int[][] isConnected) {
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

  private static void findCircleNum(int[][] isConnected, boolean[] visited, int node) {
    if (visited[node]) return;
    visited[node] = true;
    for (int i = 0; i < isConnected.length; ++i) {
      if (isConnected[node][i] == 1) {
        findCircleNum(isConnected, visited, i);
      }
    }
  }

  public static void surroundedRegion(char[][] board) {
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

  private static void surroundedRegion(char[][] board, int i, int j, int m, int n) {
    if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'O') return;
    board[i][j] = 'D';
    surroundedRegion(board, i - 1, j, m, n);
    surroundedRegion(board, i, j - 1, m, n);
    surroundedRegion(board, i + 1, j, m, n);
    surroundedRegion(board, i, j + 1, m, n);
  }

  public static int numEnclaves(int[][] grid) {
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

  private static void numEnclaves(int[][] grid, int i, int j, int m, int n) {
    if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1) return;
    grid[i][j] = 0;
    numEnclaves(grid, i - 1, j, m, n);
    numEnclaves(grid, i, j - 1, m, n);
    numEnclaves(grid, i + 1, j, m, n);
    numEnclaves(grid, i, j + 1, m, n);
  }

  public static int numberOfDistinctIslands(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    List<String> list = new ArrayList<>();
    Set<List<String>> set = new HashSet<>();
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (grid[i][j] == 1) {
          numberOfDistinctIslands(grid, i, j, i, j, list, m, n);
          set.add(new ArrayList<>(list));
          list.clear();
        }
      }
    }

    return set.size();
  }

  private static String toString(int a, int b) {
    return a + " " + b;
  }

  private static void numberOfDistinctIslands(int[][] grid, int i, int j, int a, int b, List<String> aux, int m, int n) {
    if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) return;
    grid[i][j] = 0;
    aux.add(toString(i - a, j - b));
    numberOfDistinctIslands(grid, i - 1, j, a, b, aux, m, n);
    numberOfDistinctIslands(grid, i, j - 1, a, b, aux, m, n);
    numberOfDistinctIslands(grid, i + 1, j, a, b, aux, m, n);
    numberOfDistinctIslands(grid, i, j + 1, a, b, aux, m, n);
  }

  public static boolean isBipartite(int[][] grid) {
    return false;
  }

  /*
   * Cycle detection in undirected graph
   * */
  public static boolean cycleExists(int[][] graph) {
    int n = graph.length;
    boolean[] visited = new boolean[n];
    return cycleExists(0, -1, graph, visited);
  }

  private static boolean cycleExists(int current, int parent, int[][] graph, boolean[] visited) {
    visited[current] = true;
    int[] neighbors = graph[current];
    for (int n : neighbors) {
      if (!visited[n]) {
        if (cycleExists(n, current, graph, visited)) return true;
      } else {
        if (n != parent) return true;
      }
    }
    return false;
  }

  public static boolean cycleExitsInDirectedGraph(int[][] graph) {
    int n = graph.length;
    boolean[] visited = new boolean[n];
    boolean[] visitedPath = new boolean[n];
    for (int i = 0; i < n; ++i) {
      if (visited[i]) continue;
      if (cycleExitsInDirectedGraph(i, graph, visited, visitedPath)) return true;
    }
    return false;
  }

  private static boolean cycleExitsInDirectedGraph(int current, int[][] graph, boolean[] visited, boolean[] visitedPath) {
    visited[current] = true;
    visitedPath[current] = true;
    int[] neighbors = graph[current];
    for (int neighbor : neighbors) {
      if (!visited[neighbor]) {
        if (cycleExitsInDirectedGraph(neighbor, graph, visited, visitedPath)) return true;
      } else {
        // node has been visited
        // also if it's on the same path
        if (visitedPath[neighbor]) return true;
      }
    }
    visitedPath[current] = false;
    return false;
  }

  public static List<Integer> eventualSafeNodes(int[][] graph) {
    // we use cycle detection in the directed graph
    // if a node is part of the cycle, then that isn't a safe node
    int n = graph.length;
    boolean[] visited = new boolean[n];
    boolean[] pathVisited = new boolean[n];
    for (int i = 0; i < n; ++i) {
      if (visited[i]) continue;
      eventualSafeNodes(i, graph, visited, pathVisited);
    }
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      if (!pathVisited[i]) {
        result.add(i);
      }
    }
    return result;
  }

  private static boolean eventualSafeNodes(int current, int[][] graph, boolean[] visited, boolean[] pathVisited) {
    visited[current] = true;
    pathVisited[current] = true;
    for (int neighbor : graph[current]) {
      if (!visited[neighbor]) {
        if (eventualSafeNodes(neighbor, graph, visited, pathVisited)) return true;
      } else if (pathVisited[neighbor]) {
        return true;
      }
    }
    pathVisited[current] = false;
    return false;
  }

  public static List<Integer> topologicalSort(int[][] graph) {
    // use dfs, but before backtracking put the value in the stack,
    // this will return one of the possible orderings btw
    List<Integer> result = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();
    int n = graph.length;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; ++i) {
      if (visited[i]) continue;
      topologicalSort(i, graph, visited, stack);
    }
    while (!stack.isEmpty()) {
      result.add(stack.pollFirst());
    }
    return result;
  }

  private static void topologicalSort(int current, int[][] graph, boolean[] visited, Deque<Integer> stack) {
    visited[current] = true;
    for (int neighbor : graph[current]) {
      if (!visited[neighbor]) {
        topologicalSort(neighbor, graph, visited, stack);
      }
    }
    stack.offerFirst(current);
  }

  public static int[] topologicalSortBfs(int[][] graph) {
    // create indegrees
    // push all node having 0 indegrees
    // try to make indegree to zero for those having indegree > 0
    int n = graph.length;
    int[] indegree = new int[n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < graph[i].length; ++j) {
        indegree[graph[i][j]]++;
      }
    }
    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < indegree.length; ++i) {
      if (indegree[i] == 0) queue.offer(i);
    }
    int[] result = new int[n];
    int ind = 0;
    while (!queue.isEmpty()) {
      int current = queue.poll();
      result[ind++] = current;
      for (int i : graph[current]) {
        if (indegree[i] > 0) indegree[i]--;
        if (indegree[i] == 0) queue.offer(i);
      }
    }
    return ind == n ? result : new int[0];
  }

  public static boolean cycleExistsWithTopologicalSort(int[][] graph) {
    // so, if we can have a valid topological sort, then there is no cycle
    int n = graph.length;
    int[] indegree = new int[n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < graph[i].length; ++j) {
        indegree[graph[i][j]]++;
      }
    }
    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < indegree.length; ++i) {
      if (indegree[i] == 0) queue.offer(i);
    }
    int count = 0;
    while (!queue.isEmpty()) {
      int current = queue.poll();
      count++;
      for (int i : graph[current]) {
        indegree[i]--;
        if (indegree[i] == 0) queue.offer(i);
      }
    }
    return count == n;
  }

  // O(V+E) | O(V+E)
  public static boolean courseSchedule(int numCourses, int[][] prerequisites) {
    // first we create a directed graph,
    // then run topological sort, if there is cycle we return false
    // [[1,0]] = 0 -> 1,
    // so the result will be [[1],[]]
    List<List<Integer>> graph = new ArrayList<>(numCourses);
    for (int i = 0; i < numCourses; ++i) graph.add(new ArrayList<>());
    int[] indegrees = new int[numCourses];

    for (int[] edge : prerequisites) {
      int course = edge[0], prereq = edge[1];
      graph.get(prereq).add(course);
      indegrees[course]++;
    }

    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < numCourses; ++i) {
      if (indegrees[i] == 0) queue.offer(i);
    }
    int count = 0;
    while (!queue.isEmpty()) {
      int current = queue.poll();
      count++;
      // reduce indegree of neighbors
      for (int neighbor : graph.get(current)) {
        if (indegrees[neighbor] > 0) indegrees[neighbor]--;
        if (indegrees[neighbor] == 0) queue.offer(neighbor);
      }
    }
    return count == numCourses;
  }

  // O(V+E) | O(V+E)
  public static int[] courseScheduleFindOrder(int numCourses, int[][] prerequisites) {
    // first we create a directed graph,
    // then run topological sort, if there is cycle we return false
    // [[1,0]] = 0 -> 1,
    // so the result will be [[1],[]]
    List<List<Integer>> graph = new ArrayList<>(numCourses);
    int[] indegrees = new int[numCourses];
    for (int i = 0; i < numCourses; ++i) graph.add(new ArrayList<>());

    // build graph prereq -> course
    // also increment course indegree
    for (int[] edge : prerequisites) {
      int course = edge[0];
      int prereq = edge[1];
      graph.get(prereq).add(course);
      indegrees[course]++;
    }

    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < numCourses; ++i) {
      if (indegrees[i] == 0) queue.offer(i);
    }
    int[] result = new int[numCourses];
    int ind = 0;
    while (!queue.isEmpty()) {
      int current = queue.poll();
      result[ind++] = current;
      // reduce indegree of neighbors
      for (int neighbor : graph.get(current)) {
        if (indegrees[neighbor] > 0) indegrees[neighbor]--;
        if (indegrees[neighbor] == 0) queue.offer(neighbor);
      }
    }
    return ind == numCourses ? result : new int[0];
  }
}
