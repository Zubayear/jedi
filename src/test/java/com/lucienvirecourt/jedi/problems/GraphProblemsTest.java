package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.lucienvirecourt.jedi.problems.GraphProblems.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class GraphProblemsTest {

  @Test
  void bfsTest() {
    List<List<Integer>> graph = List.of(
      Arrays.asList(1, 2),
      Arrays.asList(0, 2, 3),
      Arrays.asList(0, 4),
      Arrays.asList(1, 4),
      Arrays.asList(2, 3)
    );
    assertIterableEquals(List.of(0, 1, 2, 3, 4), bfs(graph, 0));
  }

  @Test
  void dfsTest() {
    List<List<Integer>> graph = List.of(
      Arrays.asList(1, 2),
      Arrays.asList(0, 2, 3),
      Arrays.asList(0, 4),
      Arrays.asList(1, 4),
      Arrays.asList(2, 3)
    );
    assertIterableEquals(List.of(0, 1, 2, 4, 3), dfs(graph, 0));
  }

  @Test
  void numberOfDistinctIslandsTest() {
    assertEquals(2, numberOfDistinctIslands(new int[][]{
      {1, 1, 0, 1, 1},
      {1, 0, 0, 0, 0},
      {0, 0, 0, 1, 1},
      {1, 1, 0, 1, 0},
    }));
    assertEquals(1, numberOfDistinctIslands(new int[][]{
      {1, 1, 0, 0, 0},
      {1, 1, 0, 0, 0},
      {0, 0, 0, 1, 1},
      {0, 0, 0, 1, 1},
    }));
  }

  @Test
  void cycleExistsTest() {
    int[][] graph = {
      {1, 2, 3},
      {0, 2},
      {1, 0},
      {0, 4},
      {3}
    };
    assertTrue(cycleExists(graph));
  }

  @Test
  void cycleExitsInDirectedGraphTest() {
    int[][] graph = {
      {},
      {2},
      {3},
      {4, 7},
      {5},
      {6},
      {},
      {5},
      {2, 9},
      {10},
      {10}
    };
    assertTrue(cycleExitsInDirectedGraph(graph));
  }

  @Test
  void eventualSafeNodesTest() {
    assertThat("Assert eventual safe nodes", List.of(2, 4, 5, 6), is(eventualSafeNodes(new int[][]{
      {1, 2},
      {2, 3},
      {5},
      {0},
      {5},
      {},
      {}
    })));

    assertThat("Assert eventual safe nodes 2", List.of(4), is(eventualSafeNodes(new int[][]{
      {1, 2, 3, 4},
      {1, 2},
      {3, 4},
      {0, 4},
      {},
    })));
  }

  @Test
  void topologicalSortTest() {
    int[][] graph = {
      {},
      {},
      {3},
      {1},
      {0, 1},
      {0, 2}
    };
    // although we can have different ordering too
    assertThat("Topological sort", List.of(5, 4, 2, 3, 1, 0), is(topologicalSort(graph)));
  }
}
