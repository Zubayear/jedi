package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.SlidingWindowProblem.*;
import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowProblemTest {

  SlidingWindowProblem slidingWindowProblem;

  @BeforeEach
  void setup() {
    slidingWindowProblem = new SlidingWindowProblem();
  }

  @AfterEach
  void teardown() {
    slidingWindowProblem = null;
  }

  @Test
  void testLengthOfLongestSubstringKDistinct() {
    assertEquals(4, slidingWindowProblem.lengthOfLongestSubstringKDistinct("eceba", 3));
    assertEquals(4, slidingWindowProblem.lengthOfLongestSubstringKDistinct("WORLD", 4));
  }

  @Test
  @DisplayName("Number of Substrings Containing All Three Characters")
  void testNumberOfStrings() {
    assertEquals(10, slidingWindowProblem.numberOfStrings("abcabc"));
    assertEquals(3, slidingWindowProblem.numberOfStrings("aaacb"));
    assertEquals(1, slidingWindowProblem.numberOfStrings("abc"));
  }

  @Test
  @DisplayName("Longest Repeating Character Replacement")
  void testCharacterReplacement() {
    assertEquals(4, slidingWindowProblem.characterReplacement("ABAB", 2));
    assertEquals(4, slidingWindowProblem.characterReplacement("AABABBA", 1));
  }

  @Test
  @DisplayName("Binary Subarrays With Sum")
  void testNumSubarraysWithSum() {
    assertEquals(4, slidingWindowProblem.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
    assertEquals(15, slidingWindowProblem.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
  }
}
