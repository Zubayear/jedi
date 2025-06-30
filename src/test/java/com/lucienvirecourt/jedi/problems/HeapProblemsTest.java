package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.HeapProblems.findKthLargest;
import static org.junit.jupiter.api.Assertions.*;

class HeapProblemsTest {

  @Test
  void testFindKthLargest() {
    assertEquals(5, findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    assertEquals(4, findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
  }
}
