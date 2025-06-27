package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.SlidingWindowProblem.lengthOfLongestSubstringKDistinct;
import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowProblemTest {
  @Test
  void testLengthOfLongestSubstringKDistinct() {
    assertEquals(4, lengthOfLongestSubstringKDistinct("eceba", 3));
    assertEquals(4, lengthOfLongestSubstringKDistinct("WORLD", 4));
  }
}
