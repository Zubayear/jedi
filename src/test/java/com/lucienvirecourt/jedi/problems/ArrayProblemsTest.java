package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayProblemsTest {

  ArrayProblems ap;

  @BeforeEach
  void setUp() {
    ap = new ArrayProblems();
  }

  @AfterEach
  void tearDown() {
    ap = null;
  }

  @Test
  void secondMax() {
    assertEquals(5, ap.secondMax(new int[]{1,2,4,7,7,5}));
  }

  @Test
  void removeDuplicatesTest() {
    assertEquals(3, ap.removeDuplicates(new int[]{1,1,2,2,2,3,3}));
    assertEquals(5, ap.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
  }
}
