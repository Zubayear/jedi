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
}
