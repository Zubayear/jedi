package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.StackProblems.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StackProblemsTest {

  @Test
  void removingStarsFromAStringTest() {
    assertEquals("lecoe", removingStarsFromAString("leet**cod*e"));
    assertEquals("", removingStarsFromAString("erase*****"));
  }

  @Test
  void asteroidCollisionTest() {
    assertArrayEquals(new Integer[]{5, 10}, asteroidCollision(new int[]{5, 10, -5}));
    assertArrayEquals(new Integer[]{}, asteroidCollision(new int[]{8, -8}));
    assertArrayEquals(new Integer[]{10}, asteroidCollision(new int[]{10, 2, -5}));
  }

  @Test
  void dailyTemperaturesTest() {
    assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
  }

  @Test
  void decodeStringTest() {
    assertEquals("accaccacc", decodeString("3[a2[c]]"));
    assertEquals("aaabcbc", decodeString("3[a]2[bc]"));
    assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"));
  }
}
