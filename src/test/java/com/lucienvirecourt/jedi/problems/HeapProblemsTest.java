package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.HeapProblems.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class HeapProblemsTest {

  @Test
  void testFindKthLargest() {
    assertEquals(5, findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    assertEquals(4, findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
  }

  @Test
  void testFrequencySort() {
    assertEquals("bbAa", frequencySort("Aabb"));
  }

  @Test
  void getOrderTest() {
    assertThat(getOrder(new int[][]{
      {1, 2},
      {2, 4},
      {3, 2},
      {4, 1}
    }), is(new int[]{0, 2, 3, 1}));
    assertThat(getOrder(new int[][]{
      {7, 10},
      {7, 12},
      {7, 5},
      {7, 4},
      {7, 2}
    }), is(new int[]{4, 3, 2, 0, 1}));
    assertThat(getOrder(new int[][]{
      {5, 2},
      {7, 2},
      {9, 4},
      {6, 3},
      {5, 10},
      {1, 1},
    }), is(new int[]{5, 0, 1, 3, 2, 4}));
  }
}
