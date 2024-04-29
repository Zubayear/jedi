package com.zubayear.dsaj.customds.graph;

import java.util.*;

public class Graph<T> {
    /**
     * 1 -> [2, 3]
     * 2 -> [1]
     * 3 -> []
     * 4 -> [1]
     */
    protected Map<T, List<T>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    public void addEdge(T node1, T node2) {
        if ((int) node2 == 0) {
            graph.put(node1, new ArrayList<>());
            return;
        }
        graph.computeIfAbsent(node1, k -> new ArrayList<>()).add(node2);
//        graph.computeIfAbsent(node2, k -> new ArrayList<>()).add(node1);
    }

    public void printGraph() {
        System.out.println(graph);
    }

    public List<T> bfs(T start) {
        List<T> result = new ArrayList<>();
        Deque<T> queue = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        queue.offerLast(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.pollFirst();
            result.add(current);
            List<T> neighbors = graph.get(current);
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.offerLast(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return result;
    }

    public List<T> dfs(T start) {
        List<T> result = new ArrayList<>();
        Deque<T> stack = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        stack.offerFirst(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            T current = stack.pollFirst();
            result.add(current);
            List<T> neighbors = graph.get(current);
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.offerFirst(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return result;
    }

    public List<T> dfsRecur(T node) {
        List<T> result = new ArrayList<>();
        if (node == null) return result;
        dfsRecur(node, new HashSet<>(), result);
        return result;
    }

    private void dfsRecur(T node, Set<T> visited, List<T> result) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        result.add(node);
        List<T> neighbors = graph.get(node);
        for (T n : neighbors) {
            dfsRecur(n, visited, result);
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Deque<int[]> coordinateQ = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    coordinateQ.offer(new int[]{i, j});
                    numIslands(grid, coordinateQ);
                    result++;
                }
            }
        }
        return result;
    }

    private void numIslands(char[][] grid, Deque<int[]> coordinateQ) {
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int m = grid.length, n = grid[0].length;
        while (!coordinateQ.isEmpty()) {
            int[] coordinates = coordinateQ.poll();
            for (int[] dir : dirs) {
                int a = coordinates[0] + dir[0], b = coordinates[1] + dir[1];
                if (a < 0 || a >= m || b < 0 || b >= n || grid[a][b] == '0') continue;
                coordinateQ.offer(new int[]{a, b});
                grid[a][b] = '0';
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        List<List<Integer>> adjList = new ArrayList<>();
        boolean[] visited = new boolean[m];
        for (int i = 0; i < m; ++i) adjList.add(new ArrayList<>());
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }
        System.out.println(adjList);
        int result = 0;
        for (int i = 0; i < m; ++i) {
            if (!visited[i]) {
                result++;
                findCircleNum(adjList, i, visited);
            }
        }
        return result;
    }

    private void findCircleNum(List<List<Integer>> graph, int start, boolean[] visited) {
        visited[start] = true;
        List<Integer> neighbors = graph.get(start);
        for (int n : neighbors) {
            if (!visited[n]) {
                findCircleNum(graph, n, visited);
            }
        }
    }

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] result = new int[m][n];
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 0) {
                    q.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] val = q.poll();
            int x = val[0], y = val[1], count = val[2];
            result[x][y] = count;
            for (int[] d : dirs) {
                int xc = x + d[0], yc = y + d[1];
                if (xc < 0 || yc < 0 || xc >= m || yc >= n || visited[xc][yc]) continue;
                q.offer(new int[]{xc, yc, count + 1});
                visited[xc][yc] = true;
            }
        }
        return result;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        // n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
        int result = 0;
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int[] road : roads) {
            adj.computeIfAbsent(road[0], x -> new HashSet<>()).add(road[1]);
            adj.computeIfAbsent(road[1], x -> new HashSet<>()).add(road[0]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // we'll take the pair i.e. (0,1), (0,2), (0,3)
                // calculating the set size for node pair i.e. for 0 -> size 2 and 1 -> size 3
                int currentRank = adj.getOrDefault(i, Collections.emptySet()).size() +
                        adj.getOrDefault(j, Collections.emptySet()).size();
                // {0=[1, 3], 1=[0, 2, 3], 2=[1], 3=[0, 1]}
                // calculating 0-1 connection twice so need to subtract one
                // so we're getting the set of 0 -> [1,3] and checking if 1 exists or not if it does we're subtracting 1
                if (adj.getOrDefault(i, Collections.emptySet()).contains(j)) currentRank--;
                result = Math.max(result, currentRank);
            }
        }
        return result;
    }

    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;
        // run dfs on the corner 'O'
        // first row
        for (int i = 0; i < n; ++i) if (board[0][i] == 'O') surroundedRegions(board, 0, i);
        // last row
        for (int i = 0; i < n; ++i) if (board[m - 1][i] == 'O') surroundedRegions(board, m - 1, i);
        // first col
        for (int i = 0; i < m; ++i) if (board[i][0] == 'O') surroundedRegions(board, i, 0);
        // last col
        for (int i = 0; i < m; ++i) if (board[i][n - 1] == 'O') surroundedRegions(board, i, n - 1);

        // we've made 'O' to 'D' in all the corners
        // now make the rest of the 'O' in the middle to 'X'
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }

        // change 'D' to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'D') board[i][j] = 'O';
            }
        }
    }

    private void surroundedRegions(char[][] board, int xr, int yc) {
        if (xr < 0 || yc < 0 || xr >= board.length || yc >= board[0].length || board[xr][yc] == 'X') return;
        board[xr][yc] = 'D';
        surroundedRegions(board, xr - 1, yc);
        surroundedRegions(board, xr + 1, yc);
        surroundedRegions(board, xr, yc - 1);
        surroundedRegions(board, xr, yc + 1);
    }

    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        color[0] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int n : graph[cur]) {
                if (color[n] == -1) {
                    // if the adj node not colored yet
                    color[n] = color[cur] == 0 ? 1 : 0;
                    q.offer(n);
                } else if (color[n] == color[cur]) { // if adj node have the same color
                    return false;
                }
            }
        }
        return true;
    }

    public int minCostConnectPoints(int[][] points) {
        // pre-processing
        // [[0,0],[2,2],[3,10],[5,2],[7,0]]
        int N = points.length;
        // 0 -> [cost, node]
        // 1 -> []
        // ...
        List<int[]>[] adj = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int x1 = points[i][0], y1 = points[i][1]; // 0, 0
            for (int j = i + 1; j < N; j++) {
                int x2 = points[j][0], y2 = points[j][1]; // 2, 2
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                adj[i].add(new int[]{dist, j});
                adj[j].add(new int[]{dist, i});
            }
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // {wt,n}
        Set<Integer> visited = new HashSet<>();
        int sum = 0;
        minHeap.add(new int[]{0, 0});

        while (visited.size() < N) {
            int[] val = minHeap.poll();
            int wt = val[0], node = val[1];

            if (visited.contains(node)) continue;
            sum += wt;
            visited.add(node);

            for (int[] n : adj[node]) {
                if (!visited.contains(n[1])) {
                    minHeap.add(new int[]{n[0], n[1]});
                }
            }
        }
        return sum;

    }

}
