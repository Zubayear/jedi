package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class HeapProblemsTest {

  HeapProblems hp;

  @BeforeEach
  void setup() {
    hp = new HeapProblems();
  }

  @AfterEach
  void teardown() {
    hp = null;
  }

  @Test
  void testFindKthLargest() {
    assertEquals(5, hp.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    assertEquals(4, hp.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    assertEquals(7, hp.findKthLargest(new int[]{7}, 1));
  }

  @Test
  void testFrequencySort() {
    assertEquals("bbAa", hp.frequencySort("Aabb"));
    assertEquals("aaaBB1", hp.frequencySort("aaaBB1"));
  }

  @Test
  void getOrderTest() {
    assertThat(hp.getOrder(new int[][]{
      {1, 2},
      {2, 4},
      {3, 2},
      {4, 1}
    }), is(new int[]{0, 2, 3, 1}));
    assertThat(hp.getOrder(new int[][]{
      {7, 10},
      {7, 12},
      {7, 5},
      {7, 4},
      {7, 2}
    }), is(new int[]{4, 3, 2, 0, 1}));
    assertThat(hp.getOrder(new int[][]{
      {5, 2},
      {7, 2},
      {9, 4},
      {6, 3},
      {5, 10},
      {1, 1},
    }), is(new int[]{5, 0, 1, 3, 2, 4}));
    assertThat(hp.getOrder(new int[][]{
      {5, 3},
      {5, 2},
      {6, 1}
    }), is(new int[]{1, 2, 0}));
  }

  @Test
  void kClosestTest() {
    assertPointsEqual(new int[][]{{-2, 2}}, hp.kClosest(new int[][]{
      {1, 3},
      {-2, 2}
    }, 1));

    assertPointsEqual(new int[][]{{0, 1}, {1, 1}}, hp.kClosest(new int[][]{
      {1, 3},
      {-2, 2},
      {5, 8},
      {0, 1},
      {1, 1}
    }, 2));
  }

  @Test
  void leastIntervalTest() {
    assertEquals(8, hp.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    assertEquals(6, hp.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
    assertEquals(1, hp.leastInterval(new char[]{'A'}, 3));
  }

  private static void assertPointsEqual(int[][] expected, int[][] actual) {
    Arrays.sort(expected, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
    Arrays.sort(actual, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
    assertTrue(Arrays.deepEquals(expected, actual));
  }
}
