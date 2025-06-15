package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.BinarySearchProblems.*;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchProblemsTest {
  @Test
  void lowerBoundTest() {
    assertEquals(1, lowerBound(new int[]{1, 2, 2, 3}, 2));
    assertEquals(1, lowerBound(new int[]{3, 5, 8, 15, 19}, 5));
    assertEquals(3, lowerBound(new int[]{3, 5, 8, 15, 19}, 9));
  }

  @Test
  void upperBoundTest() {
    assertEquals(3, upperBound(new int[]{2, 3, 6, 7, 8, 8, 11, 11, 11, 12}, 6));
  }

  @Test
  void floorTest() {
    assertEquals(22, floor(new int[]{10, 20, 22, 30, 40, 50}, 25));
  }

  @Test
  void ceilTest() {
    assertEquals(30, ceil(new int[]{10, 20, 30, 40, 50}, 25));
  }

  @Test
  void firstAndLastOccurrenceTest() {
    assertArrayEquals(new int[]{3, 4}, firstAndLastOccurrence(new int[]{2, 4, 6, 8, 8, 11, 13}, 8));
  }

  @Test
  void searchInRotatedSortedArrayTest() {
    assertEquals(4, searchInRotatedSortedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    assertEquals(-1, searchInRotatedSortedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    assertEquals(-1, searchInRotatedSortedArray(new int[]{1}, 0));
  }

  @Test
  void searchInRotatedSortedArrayWithDuplicatesTest() {
    assertTrue(searchInRotatedSortedArrayWithDuplicates(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
    assertFalse(searchInRotatedSortedArrayWithDuplicates(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
  }

  @Test
  void findMinInRotatedSortedArrayTest() {
    assertEquals(1, findMinInRotatedSortedArray(new int[]{3, 4, 5, 1, 2}));
    assertEquals(0, findMinInRotatedSortedArray(new int[]{4, 5, 6, 7, 0, 1, 2}));
    assertEquals(11, findMinInRotatedSortedArray(new int[]{11, 13, 15, 17}));
  }

  @Test
  void findRotationTimeTest() {
    assertEquals(3, findRotationTime(new int[]{3, 4, 5, 1, 2}));
    assertEquals(4, findRotationTime(new int[]{4, 5, 6, 7, 0, 1, 2}));
    assertEquals(0, findRotationTime(new int[]{11, 13, 15, 17}));
  }

  @Test
  void searchMatrixTest() {
    assertTrue(searchMatrix(new int[][]{
      {1,3,5,7},
      {10,11,16,20},
      {23,30,34,60}
    }, 3));
  }
}
