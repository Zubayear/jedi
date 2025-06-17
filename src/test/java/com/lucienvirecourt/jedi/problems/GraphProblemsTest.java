package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.lucienvirecourt.jedi.problems.GraphProblems.*;
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
    assertIterableEquals(List.of(0,1,2,3,4), bfs(graph, 0));
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
    assertIterableEquals(List.of(0,1,2,4,3), dfs(graph, 0));
  }

  @Test
  void numberOfDistinctIslandsTest() {
    assertEquals(2, numberOfDistinctIslands(new int[][]{
      {1,1,0,1,1},
      {1,0,0,0,0},
      {0,0,0,1,1},
      {1,1,0,1,0},
    }));
    assertEquals(1, numberOfDistinctIslands(new int[][]{
      {1,1,0,0,0},
      {1,1,0,0,0},
      {0,0,0,1,1},
      {0,0,0,1,1},
    }));
  }
}
