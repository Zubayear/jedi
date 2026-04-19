package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreedyProblemsTest {
  GreedyProblems greedyProblems;

  @BeforeEach
  void setup() {
    greedyProblems = new GreedyProblems();
  }

  @AfterEach
  void teardown() {
    greedyProblems = null;
  }

  @Test
  void testCanAttendMeetings() {
    assertFalse(greedyProblems.canAttendMeetings(new int[][]{
      {0, 30},
      {5, 10},
      {15, 20},
    }));

    assertTrue(greedyProblems.canAttendMeetings(new int[][]{
      {5, 8},
      {9, 15},
    }));

    assertFalse(greedyProblems.canAttendMeetings(new int[][]{
      {0, 5},
      {3, 4},
      {1, 2},
      {5, 9},
      {5, 7},
      {8, 9},
    }));

    assertTrue(greedyProblems.canAttendMeetings(new int[][]{
      {4, 8},
    }));
  }

  @Test
  void testMeetingCount() {
    assertEquals(4, greedyProblems.meetingCount(new int[][]{
      {1, 2},
      {3, 4},
      {0, 5},
      {5, 7},
      {8, 9},
      {5, 9},
    }));

    assertEquals(2, greedyProblems.meetingCount(new int[][]{
      {1, 2},
      {2, 3},
      {3, 4},
      {1, 3},
    }));

    assertEquals(1, greedyProblems.meetingCount(new int[][]{
      {2, 6},
    }));
  }

  @Test
  void testEraseOverlapIntervals() {
    assertEquals(1, greedyProblems.eraseOverlapIntervals(new int[][]{
      {1, 2},
      {2, 3},
      {3, 4},
      {1, 3},
    }));

    assertEquals(2, greedyProblems.eraseOverlapIntervals(new int[][]{
      {1, 4},
      {2, 5},
      {3, 6},
    }));
  }

  @Test
  void testFindContentChildren() {
    assertEquals(1, greedyProblems.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
    assertEquals(2, greedyProblems.findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));
    assertEquals(0, greedyProblems.findContentChildren(new int[]{}, new int[]{1, 2, 3}));
  }

  @Test
  void testLemonadeChange() {
    assertTrue(greedyProblems.lemonadeChange(new int[]{5, 5, 5, 10, 20}));
    assertFalse(greedyProblems.lemonadeChange(new int[]{10}));
    assertFalse(greedyProblems.lemonadeChange(new int[]{5, 5, 10, 10, 20}));
  }

  @Test
  void testMaxSubArray() {
    assertEquals(6, greedyProblems.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    assertEquals(-1, greedyProblems.maxSubArray(new int[]{-3, -2, -1, -4}));
    assertEquals(7, greedyProblems.maxSubArray(new int[]{7}));
  }

  @Test
  void testCanJump() {
    assertTrue(greedyProblems.canJump(new int[]{2, 3, 1, 1, 4}));
    assertFalse(greedyProblems.canJump(new int[]{3, 2, 1, 0, 4}));
    assertTrue(greedyProblems.canJump(new int[]{0}));
  }

  
  @Test
  void jump() {
    assertEquals(2, greedyProblems.jump(new int[]{2,3,1,1,4}));
    assertEquals(0, greedyProblems.jump(new int[]{0}));
    assertEquals(2, greedyProblems.jump(new int[]{2, 3, 0, 1, 4}));
  }

  @Test
  void testFindContentChildren_empty() {
    assertEquals(0, greedyProblems.findContentChildren(new int[]{1, 2}, new int[]{}));
  }

  @Test
  void testMaxSubArray_singleElement() {
    assertEquals(5, greedyProblems.maxSubArray(new int[]{5}));
    assertEquals(-5, greedyProblems.maxSubArray(new int[]{-5}));
  }

  @Test
  void testMaxSubArray_allPositive() {
    assertEquals(15, greedyProblems.maxSubArray(new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  void testCanJump_singleElement() {
    assertTrue(greedyProblems.canJump(new int[]{0}));
  }

  @Test
  void testJump_singleElement() {
    assertEquals(0, greedyProblems.jump(new int[]{0}));
  }
}
