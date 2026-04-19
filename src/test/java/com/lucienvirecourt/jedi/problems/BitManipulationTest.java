package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitManipulationTest {

  BitManipulation bm;

  @BeforeEach
  void setup() {
    bm = new BitManipulation();
  }

  @AfterEach
  void tearDown() {
    bm = null;
  }

  @Test
  void minBitFlipsTest() {
    assertEquals(3, bm.minBitFlips(10, 7));
  }

  @Test
  void missingNumbersTest() {
    assertEquals(2, bm.missingNumbers(new int[]{3, 0, 1}));
    assertEquals(8, bm.missingNumbers(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
  }

  @Test
  void minBitFlips_sameNumbers() {
    assertEquals(0, bm.minBitFlips(5, 5));
  }

  @Test
  void minBitFlips_singleBit() {
    assertEquals(1, bm.minBitFlips(0, 1));
    assertEquals(1, bm.minBitFlips(1, 0));
  }

  @Test
  void minBitFlips_maxValues() {
    assertEquals(0, bm.minBitFlips(1023, 1023));
  }

  @Test
  void missingNumbers_singleElement() {
    assertEquals(1, bm.missingNumbers(new int[]{0}));
    assertEquals(0, bm.missingNumbers(new int[]{1}));
  }

  @Test
  void missingNumbers_twoElements() {
    assertEquals(2, bm.missingNumbers(new int[]{0, 1}));
    assertEquals(1, bm.missingNumbers(new int[]{0, 2}));
  }
}
