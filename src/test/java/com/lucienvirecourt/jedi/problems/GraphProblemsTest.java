package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import java.util.*;

import static com.lucienvirecourt.jedi.problems.GraphProblems.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
    assertThat("Topological sort", List.of(5, 4, 2, 3, 1, 0), is(topologicalSort(graph)));
  }

  @Test
  public void testTopologicalSortBfs() {
    int[][] graph1 = {
      {1},
      {2},
      {}
    };
    assertThat(topologicalSortBfs(graph1), is(new int[]{0, 1, 2}));

    int[][] graph2 = {
      {1},     // 0 -> 1
      {},      // 1
      {1}      // 2 -> 1
    };
    int[] result2 = topologicalSortBfs(graph2);
    assertThat(result2.length, is(3));
    Integer[] boxedResult = Arrays.stream(result2).boxed().toArray(Integer[]::new);
    assertThat(boxedResult, arrayContainingInAnyOrder(0, 1, 2));

    assertThat(indexOf(result2, 1), greaterThan(indexOf(result2, 0)));
    assertThat(indexOf(result2, 1), greaterThan(indexOf(result2, 2)));

    int[][] graph3 = {
      {1},
      {2},
      {0}
    };
    assertThat(topologicalSortBfs(graph3), is(new int[]{}));

    int[][] graph4 = {
      {}, {}, {3}, {}
    };
    int[] result4 = topologicalSortBfs(graph4);
    assertThat(result4.length, is(4));
    assertArrayEquals(new int[]{0, 1, 2, 3}, result4);
    assertThat(indexOf(result4, 2), lessThan(indexOf(result4, 3)));

    int[][] graph5 = {{}};
    assertThat(topologicalSortBfs(graph5), is(new int[]{0}));

    int[][] graph6 = {};
    assertThat(topologicalSortBfs(graph6), is(new int[]{}));
  }

  private int indexOf(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == target) return i;
    }
    return -1;
  }

  @Test
  void cycleExistsWithTopologicalSortTest() {
    assertTrue(cycleExistsWithTopologicalSort(
      new int[][]{
        {},
        {},
        {3},
        {1},
        {0, 1},
        {0, 2}
      }
    ));

    assertFalse(cycleExistsWithTopologicalSort(
      new int[][]{
        {},
        {2},
        {3},
        {4, 5},
        {2},
        {}
      }
    ));
  }

  @Test
  void courseScheduleTest() {
    assertTrue(courseSchedule(2, new int[][]{{1, 0}}));
    assertFalse(courseSchedule(2, new int[][]{{1, 0}, {0, 1}}));
    assertTrue(courseSchedule(2, new int[][]{{0, 1}}));
  }

  @Test
  void courseScheduleFindOrderTest() {
    assertValidTopologicalOrder(
      4,
      new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}},
      "Basic valid graph"
    );

    assertValidTopologicalOrder(
      3,
      new int[][]{},
      "No prerequisites"
    );

    assertValidTopologicalOrder(
      1,
      new int[][]{},
      "Single course"
    );

    assertValidTopologicalOrder(
      4,
      new int[][]{{1, 0}, {3, 2}},
      "Disconnected components"
    );

    assertInvalidTopologicalOrder(
      2,
      new int[][]{{0, 1}, {1, 0}},
      "Simple cycle"
    );

    assertInvalidTopologicalOrder(
      4,
      new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}},
      "Larger cycle"
    );

    assertValidTopologicalOrder(
      5,
      new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}},
      "Linear chain dependency"
    );

    assertValidTopologicalOrder(
      3,
      new int[][]{{1, 0}, {2, 0}},
      "Multiple valid topological orders"
    );
  }

  private void assertValidTopologicalOrder(int numCourses, int[][] prerequisites, String label) {
    int[] order = courseScheduleFindOrder(numCourses, prerequisites);

    assertEquals(numCourses, order.length, label + ": Missing or extra courses");

    Set<Integer> seen = new HashSet<>();
    for (int course : order) {
      assertTrue(seen.add(course), label + ": Duplicate course in order");
    }

    Map<Integer, Integer> position = new HashMap<>();
    for (int i = 0; i < order.length; i++) {
      position.put(order[i], i);
    }

    for (int[] prereq : prerequisites) {
      int course = prereq[0];
      int dependency = prereq[1];
      assertTrue(position.get(dependency) < position.get(course),
        label + ": Course " + dependency + " should come before " + course);
    }
  }

  private void assertInvalidTopologicalOrder(int numCourses, int[][] prerequisites, String label) {
    int[] order = courseScheduleFindOrder(numCourses, prerequisites);
    assertEquals(0, order.length, label + ": Should return empty array due to cycle");
  }

  @Test
  void findCircleNumTest() {
    assertEquals(1, findCircleNum(new int[][]{
      {1, 1, 1},
      {1, 1, 1},
      {1, 1, 1}
    }), "All cities connected → 1 province");

    assertEquals(3, findCircleNum(new int[][]{
      {1, 0, 0},
      {0, 1, 0},
      {0, 0, 1}
    }), "No cities connected → 3 provinces");

    assertEquals(2, findCircleNum(new int[][]{
      {1, 1, 0},
      {1, 1, 0},
      {0, 0, 1}
    }), "Two connected components → 2 provinces");

    assertEquals(2, findCircleNum(new int[][]{
      {1, 0, 0, 1},
      {0, 1, 1, 0},
      {0, 1, 1, 0},
      {1, 0, 0, 1}
    }), "Two separate groups → 2 provinces");

    assertEquals(1, findCircleNum(new int[][]{
      {1}
    }), "Single city → 1 province");

    assertEquals(0, findCircleNum(new int[][]{}), "Empty graph → 0 provinces");
  }

  @Test
  void surroundRegionTest() {
    char[][] board1 = {
      {'X', 'X', 'X', 'X'},
      {'X', 'O', 'O', 'X'},
      {'X', 'X', 'O', 'X'},
      {'X', 'O', 'X', 'X'}
    };
    char[][] expected1 = {
      {'X', 'X', 'X', 'X'},
      {'X', 'X', 'X', 'X'},
      {'X', 'X', 'X', 'X'},
      {'X', 'O', 'X', 'X'}
    };
    surroundedRegion(board1);
    assertArrayEquals(expected1, board1, "Enclosed 'O' should be flipped to 'X'");

    char[][] board2 = {
      {'O', 'O'},
      {'O', 'O'}
    };
    char[][] expected2 = {
      {'O', 'O'},
      {'O', 'O'}
    };
    surroundedRegion(board2);
    assertArrayEquals(expected2, board2, "All 'O' on border — should remain unchanged");

    char[][] board3 = {
      {'O', 'X', 'X', 'O', 'X'},
      {'X', 'O', 'O', 'X', 'O'},
      {'X', 'O', 'X', 'O', 'X'},
      {'O', 'X', 'O', 'O', 'O'},
      {'X', 'X', 'O', 'X', 'O'}
    };
    char[][] expected3 = {
      {'O', 'X', 'X', 'O', 'X'},
      {'X', 'X', 'X', 'X', 'O'},
      {'X', 'X', 'X', 'O', 'X'},
      {'O', 'X', 'O', 'O', 'O'},
      {'X', 'X', 'O', 'X', 'O'}
    };
    surroundedRegion(board3);
    assertArrayEquals(expected3, board3, "Only surrounded 'O' should be flipped");

    char[][] board5 = {
      {'O'}
    };
    char[][] expected5 = {
      {'O'}
    };
    surroundedRegion(board5);
    assertArrayEquals(expected5, board5, "Single cell border 'O' should remain unchanged");

    char[][] board6 = {
      {'X', 'X'},
      {'X', 'X'}
    };
    char[][] expected6 = {
      {'X', 'X'},
      {'X', 'X'}
    };
    surroundedRegion(board6);
    assertArrayEquals(expected6, board6, "All Xs should remain unchanged");
  }

  @Test
  void numEnclavesTest() {
    // Case 1: Fully enclosed land
    int[][] grid1 = {
      {0, 0, 0, 0},
      {0, 1, 1, 0},
      {0, 1, 1, 0},
      {0, 0, 0, 0}
    };
    assertEquals(4, numEnclaves(grid1), "Fully enclosed 2x2 land should return 4");

    // Case 2: Land touches border → not counted
    int[][] grid2 = {
      {0, 1, 0, 0},
      {0, 1, 1, 0},
      {0, 0, 0, 0}
    };
    assertEquals(0, numEnclaves(grid2), "Land connected to border should return 0");

    // Case 3: All land
    int[][] grid3 = {
      {1, 1},
      {1, 1}
    };
    assertEquals(0, numEnclaves(grid3), "All land touches boundary → 0");

    // Case 4: No land
    int[][] grid4 = {
      {0, 0},
      {0, 0}
    };
    assertEquals(0, numEnclaves(grid4), "No land cells → 0");

    // Case 5: One small island in center
    int[][] grid5 = {
      {0, 0, 0},
      {0, 1, 0},
      {0, 0, 0}
    };
    assertEquals(1, numEnclaves(grid5), "Single 1 fully enclosed → 1");

    // Case 6: One row
    int[][] grid6 = {
      {1, 1, 1, 1}
    };
    assertEquals(0, numEnclaves(grid6), "All land touches boundary → 0");

    // Case 7: One column
    int[][] grid7 = {
      {1},
      {1},
      {1}
    };
    assertEquals(0, numEnclaves(grid7), "Single column of land touches boundary");

    // Case 8: Zigzag land that touches border
    int[][] grid8 = {
      {1, 0, 1},
      {1, 1, 0},
      {0, 1, 1}
    };
    assertEquals(0, numEnclaves(grid8), "Zigzag land all connected to edge → 0");

    // Case 9: Large enclosed area
    int[][] grid9 = {
      {0, 0, 0, 0, 0},
      {0, 1, 1, 1, 0},
      {0, 1, 1, 1, 0},
      {0, 1, 1, 1, 0},
      {0, 0, 0, 0, 0}
    };
    assertEquals(9, numEnclaves(grid9), "3x3 block of land in center → 9");

    // Case 10: Minimum size
    int[][] grid10 = {
      {1}
    };
    assertEquals(0, numEnclaves(grid10), "1x1 land on border → 0");
  }
}
