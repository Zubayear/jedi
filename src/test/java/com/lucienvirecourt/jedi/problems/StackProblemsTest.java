package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StackProblemsTest {

  StackProblems sp;

  @BeforeEach
  void setup() {
    sp = new StackProblems();
  }

  @AfterEach
  void teardown() {
    sp = null;
  }

  @Test
  void removingStarsFromAStringTest() {
    assertEquals("lecoe", sp.removingStarsFromAString("leet**cod*e"));
    assertEquals("", sp.removingStarsFromAString("erase*****"));
    assertEquals("abc", sp.removingStarsFromAString("abc"));
  }

  @Test
  void asteroidCollisionTest() {
    assertArrayEquals(new Integer[]{5, 10}, sp.asteroidCollision(new int[]{5, 10, -5}));
    assertArrayEquals(new Integer[]{}, sp.asteroidCollision(new int[]{8, -8}));
    assertArrayEquals(new Integer[]{10}, sp.asteroidCollision(new int[]{10, 2, -5}));
    assertArrayEquals(new Integer[]{-2, -1, 1, 2}, sp.asteroidCollision(new int[]{-2, -1, 1, 2}));
    assertArrayEquals(new Integer[]{-10}, sp.asteroidCollision(new int[]{1, 2, 3, -10}));
  }

  @Test
  void dailyTemperaturesTest() {
    assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, sp.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    assertArrayEquals(new int[]{0}, sp.dailyTemperatures(new int[]{30}));
    assertArrayEquals(new int[]{0, 0, 0}, sp.dailyTemperatures(new int[]{80, 79, 78}));
  }

  @Test
  void decodeStringTest() {
    assertEquals("accaccacc", sp.decodeString("3[a2[c]]"));
    assertEquals("aaabcbc", sp.decodeString("3[a]2[bc]"));
    assertEquals("abcabccdcdcdef", sp.decodeString("2[abc]3[cd]ef"));
    assertEquals("aaaaaaaaaa", sp.decodeString("10[a]"));
    assertEquals("abbabb", sp.decodeString("2[a2[b]]"));
  }

  @Test
  void removingStarsFromAString_emptyString() {
    assertEquals("", sp.removingStarsFromAString(""));
  }

  @Test
  void removingStarsFromAString_onlyStars() {
    assertEquals("", sp.removingStarsFromAString("***"));
  }

  @Test
  void asteroidCollision_allPositive() {
    assertArrayEquals(new Integer[]{1, 2, 3}, sp.asteroidCollision(new int[]{1, 2, 3}));
  }

  @Test
  void asteroidCollision_allNegative() {
    assertArrayEquals(new Integer[]{-1, -2, -3}, sp.asteroidCollision(new int[]{-1, -2, -3}));
  }

  @Test
  void asteroidCollision_equalSize() {
    assertArrayEquals(new Integer[]{}, sp.asteroidCollision(new int[]{5, -5}));
  }

  @Test
  void dailyTemperatures_empty() {
    assertArrayEquals(new int[]{}, sp.dailyTemperatures(new int[]{}));
  }
}
