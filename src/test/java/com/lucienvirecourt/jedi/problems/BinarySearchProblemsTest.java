package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.BinarySearchProblems.*;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchProblemsTest {

  BinarySearchProblems bsp;

  @BeforeEach
  void setup() {
    bsp = new BinarySearchProblems();
  }

  @Test
  void lowerBoundTest() {
    assertEquals(1, bsp.lowerBound(new int[]{1, 2, 2, 3}, 2));
    assertEquals(1, bsp.lowerBound(new int[]{3, 5, 8, 15, 19}, 5));
    assertEquals(3, bsp.lowerBound(new int[]{3, 5, 8, 15, 19}, 9));
  }

  @Test
  void upperBoundTest() {
    assertEquals(3, bsp.upperBound(new int[]{2, 3, 6, 7, 8, 8, 11, 11, 11, 12}, 6));
  }

  @Test
  void floorTest() {
    assertEquals(22, bsp.floor(new int[]{10, 20, 22, 30, 40, 50}, 25));
  }

  @Test
  void ceilTest() {
    assertEquals(30, bsp.ceil(new int[]{10, 20, 30, 40, 50}, 25));
  }

  @Test
  void firstAndLastOccurrenceTest() {
    assertArrayEquals(new int[]{3, 4}, bsp.firstAndLastOccurrence(new int[]{2, 4, 6, 8, 8, 11, 13}, 8));
  }

  @Test
  void searchInRotatedSortedArrayTest() {
    assertEquals(4, bsp.searchInRotatedSortedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    assertEquals(-1, bsp.searchInRotatedSortedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    assertEquals(-1, bsp.searchInRotatedSortedArray(new int[]{1}, 0));
  }

  @Test
  void searchInRotatedSortedArrayWithDuplicatesTest() {
    assertTrue(bsp.searchInRotatedSortedArrayWithDuplicates(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
    assertFalse(bsp.searchInRotatedSortedArrayWithDuplicates(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
  }

  @Test
  void findMinInRotatedSortedArrayTest() {
    assertEquals(1, bsp.findMinInRotatedSortedArray(new int[]{3, 4, 5, 1, 2}));
    assertEquals(0, bsp.findMinInRotatedSortedArray(new int[]{4, 5, 6, 7, 0, 1, 2}));
    assertEquals(11, bsp.findMinInRotatedSortedArray(new int[]{11, 13, 15, 17}));
  }

  @Test
  void findRotationTimeTest() {
    assertEquals(3, bsp.findRotationTime(new int[]{3, 4, 5, 1, 2}));
    assertEquals(4, bsp.findRotationTime(new int[]{4, 5, 6, 7, 0, 1, 2}));
    assertEquals(0, bsp.findRotationTime(new int[]{11, 13, 15, 17}));
  }

  @Test
  void searchMatrixTest() {
    assertTrue(bsp.searchMatrix(new int[][]{
      {1, 3, 5, 7},
      {10, 11, 16, 20},
      {23, 30, 34, 60}
    }, 3));
  }

  @Test
  void minEatingSpeedTest() {
    assertEquals(4, bsp.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    assertEquals(30, bsp.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
    assertEquals(23, bsp.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
  }
}
