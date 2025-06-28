package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.SlidingWindowProblem.*;
import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowProblemTest {
  @Test
  void testLengthOfLongestSubstringKDistinct() {
    assertEquals(4, lengthOfLongestSubstringKDistinct("eceba", 3));
    assertEquals(4, lengthOfLongestSubstringKDistinct("WORLD", 4));
  }

  @Test
  @DisplayName("Number of Substrings Containing All Three Characters")
  void testNumberOfStrings() {
    assertEquals(10, numberOfStrings("abcabc"));
    assertEquals(3, numberOfStrings("aaacb"));
    assertEquals(1, numberOfStrings("abc"));
  }

  @Test
  @DisplayName("Longest Repeating Character Replacement")
  void testCharacterReplacement() {
    assertEquals(4, characterReplacement("ABAB", 2));
    assertEquals(4, characterReplacement("AABABBA", 1));
  }

  @Test
  @DisplayName("Binary Subarrays With Sum")
  void testNumSubarraysWithSum() {
    assertEquals(4, numSubarraysWithSum(new int[]{1,0,1,0,1}, 2));
    assertEquals(15, numSubarraysWithSum(new int[]{0,0,0,0,0}, 0));
  }
}
