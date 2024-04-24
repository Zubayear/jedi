package com.zubayear.customds.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph<Integer> graph;

    @BeforeEach
    void init() {
        graph = new Graph<>();
//        int[][] isConnected = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
//        // 1 -> [2]
//        // 2 -> [1]
//        // 3 -> []
//        int m = isConnected.length, n = isConnected[0].length;
//        for (int i = 1; i <= m; ++i) {
//            for (int j = 1; j <= n; ++j) {
//                if (i == j) graph.addEdge(i, 0);
//                if (isConnected[i-1][j-1] == 1) graph.addEdge(i, j);
//            }
//        }
//        graph.printGraph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 4);
        graph.addEdge(3, 7);
        graph.addEdge(4, 8);
        graph.addEdge(7, 8);
    }

    @Test
    void dfsRecur() {
        List<Integer> result = graph.dfs(3);
        System.out.println(result);
    }

    @Test
    void numIslands() {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int i = graph.numIslands(grid);
        System.out.println(i);
    }

    @Test
    void findCircleNum() {
        int[][] isConnected = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        int circleNum = graph.findCircleNum(isConnected);
        System.out.println(circleNum);
    }

    @Test
    void maximalNetworkRank() {
        int n = 4;
        int[][] roads = {{0,1},{0,3},{1,2},{1,3}};
        int result = graph.maximalNetworkRank(n, roads);
        System.out.println(result);
    }

    @Test
    void surroundedRegions() {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        char[][] x = {{'X','O','X'},{'O','X','O'},{'X','O','X'}};
        graph.surroundedRegions(x);
        System.out.println(Arrays.deepToString(x));
    }

    @Test
    void isBipartite() {
        int[][] g = {{1,2,3},{0,2},{0,1,3},{0,2}};
        boolean bipartite = graph.isBipartite(g);
        System.out.println(bipartite);
    }

    @Test
    void minCostConnectPoints() {
        int[][] x = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        int i = graph.minCostConnectPoints(x);
        System.out.println(i);
    }
}