package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.GreedyProblems.*;
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
  }

  @Test
  void testEraseOverlapIntervals() {
    assertEquals(1, greedyProblems.eraseOverlapIntervals(new int[][]{
      {1, 2},
      {2, 3},
      {3, 4},
      {1, 3},
    }));
  }
}
