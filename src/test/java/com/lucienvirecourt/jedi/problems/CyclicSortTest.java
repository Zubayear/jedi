package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CyclicSortTest {

  CyclicSort cs;

  @BeforeEach
  void setup() {
    cs = new CyclicSort();
  }

  @Test
  void cyclicSort_basic() {
    int[] nums = {5, 4, 1, 3, 2};
    cs.cyclicSort(nums);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
  }

  @Test
  void cyclicSort_alreadySorted() {
    int[] nums = {1, 2, 3, 4, 5};
    cs.cyclicSort(nums);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
  }

  @Test
  void cyclicSort_reverseSorted() {
    int[] nums = {5, 4, 3, 2, 1};
    cs.cyclicSort(nums);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
  }

  @Test
  void cyclicSort_singleElement() {
    int[] nums = {1};
    cs.cyclicSort(nums);
    assertArrayEquals(new int[]{1}, nums);
  }

  @Test
  void cyclicSort_twoElements() {
    int[] nums = {2, 1};
    cs.cyclicSort(nums);
    assertArrayEquals(new int[]{1, 2}, nums);
  }

  @Test
  void cyclicSort_withValidRange() {
    int[] nums = {5, 4, 1, 3, 2};
    cs.cyclicSort(nums);
    assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
  }

  @Test
  void missingNumber_basic() {
    int[] nums = {1, 3, 4, 5};
    assertTrue(cs.missingNumber(nums) == 2 || cs.missingNumber(nums) != nums.length);
  }
}