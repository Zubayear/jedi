package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.ArrayProblems.*;
import static org.junit.jupiter.api.Assertions.*;

class ArrayProblemsTest {

  ArrayProblems ap;

  @BeforeEach
  void setup() {
    ap = new ArrayProblems();
  }

  @AfterEach
  void teardown() {
    ap = null;
  }

  @Test
  void secondMaxTest() {
    assertEquals(5, ap.secondMax(new int[]{1, 2, 4, 7, 7, 5}));
  }

  @Test
  void removeDuplicatesTest() {
    assertEquals(3, ap.removeDuplicates(new int[]{1, 1, 2, 2, 2, 3, 3}));
    assertEquals(5, ap.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
  }

  @Test
  void productExceptSelfTest() {
    int[] nums = {1, 2, 3, 4};
    assertArrayEquals(new int[]{24, 12, 8, 6}, ap.productExceptSelf(nums));
    assertArrayEquals(new int[]{0, 0, 9, 0, 0}, ap.productExceptSelf(new int[]{-1, 1, 0, -3, 3}));
  }

  @Test
  void firstDuplicateValueTest() {
    assertEquals(3, ap.firstDuplicateValue(new int[]{2, 1, 5, 3, 3, 2, 4}));
  }

  @Test
  void isValidSudokuTest() {
    assertTrue(ap.isValidSudoku(new char[][]{
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    }));
  }

  @Test
  void numRescueBoatsTest() {
    assertEquals(1, ap.numRescueBoats(new int[]{1, 2}, 3));
    assertEquals(3, ap.numRescueBoats(new int[]{3, 2, 2, 1}, 3));
    assertEquals(4, ap.numRescueBoats(new int[]{3, 5, 3, 4}, 5));
  }

  @Test
  void nextGreaterElementTest() {
    assertArrayEquals(new int[]{-1,3,-1}, ap.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
    assertArrayEquals(new int[]{3, -1}, ap.nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
    assertArrayEquals(new int[]{9, 4, 10, 20}, ap.nextGreaterElement(new int[]{1, 2, 3, 9}, new int[]{2, 4, 3, 10, 1, 9, 20, 22}));
  }
}
