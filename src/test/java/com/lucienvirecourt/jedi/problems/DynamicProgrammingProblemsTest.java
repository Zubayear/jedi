package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.lucienvirecourt.jedi.problems.DynamicProgrammingProblems.*;
import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingProblemsTest {

  @Test
  void testClimbStairs() {
    assertEquals(1, climbStairs(1));
    assertEquals(2, climbStairs(2));
    assertEquals(3, climbStairs(3));
    assertEquals(5, climbStairs(4));
    assertEquals(13, climbStairs(6));
    assertEquals(1836311903, climbStairs(45));
  }

  @Test
  void testMinCostClimbingStairs() {
    assertEquals(1, minCostClimbingStairs(new int[]{1, 2}));
    assertEquals(0, minCostClimbingStairs(new int[]{0, 0}));
    assertEquals(15, minCostClimbingStairs(new int[]{10, 15, 20}));
    assertEquals(6, minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    assertEquals(2, minCostClimbingStairs(new int[]{1, 1, 1, 1}));
  }

  @Test
  void testMinimumPathSum() {
    assertEquals(7, minimumPathSum(new int[][]{
      {1, 3, 1},
      {1, 5, 1},
      {4, 2, 1}
    }));
    assertEquals(12, minimumPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
  }

  @Test
  void testMinimumFallingPathSum() {
    assertEquals(13, minimumFallingPathSum(new int[][]{
      {2, 1, 3},
      {6, 5, 4},
      {7, 8, 9}
    }));
    assertEquals(-59, minimumFallingPathSum(new int[][]{
      {-19, 57},
      {-40, -5},
    }));
  }

  @Test
  void testCanPartition() {
    assertTrue(canPartition(new int[]{4, 3, 2, 3, 5, 2, 1}));
    assertFalse(canPartition(new int[]{1, 2, 3, 5}));
  }

//  @Test
//  void testCanPartitionOptimal() {
//    assertEquals(1, canPartitionOptimal(new int[]{2, 7, 4, 1, 8, 1}));
//    assertEquals(5, canPartitionOptimal(new int[]{31, 26, 33, 21, 40}));
//  }

//  @Test
//  void testPartitionCount() {
//    assertEquals(1, partitionCount(new int[]{2, 2, 2, 2, 3, 4, 5}, 9));
//  }

  @Test
  void testCoinChange() {
    assertEquals(3, coinChange(new int[]{1, 2, 5}, 11));
  }

  @Test
  void testFindTargetSumWays() {
    assertEquals(5, findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
  }

  @Test
  void testLongestCommonSubsequence() {
    assertEquals("ace", longestCommonSubsequence("abcde", "ace"));
    assertEquals("abc", longestCommonSubsequence("abc", "abc"));
    assertEquals("", longestCommonSubsequence("abc", "def"));
    assertEquals("aaa", longestCommonSubsequence("aaa", "aaa"));
    assertEquals("GTAB", longestCommonSubsequence("AGGTAB", "GXTXAYB"));
    assertEquals("", longestCommonSubsequence("", "abc"));
    assertEquals("", longestCommonSubsequence("abc", ""));
    assertEquals("", longestCommonSubsequence("", ""));
  }

  @Test
  void testLongestCommonSubstring() {
    assertEquals("a", longestCommonSubstring("abcde", "ace"));
    assertEquals("abc", longestCommonSubstring("abc", "abc"));
    assertEquals("bc", longestCommonSubstring("abcr", "pbctr"));
    assertEquals("cry", longestCommonSubstring("abcry", "pbcycry"));
    assertEquals("", longestCommonSubstring("abc", "def"));
  }

  @Test
  void testShortestCommonSupersequence() {
    assertEquals("cabac", shortestCommonSupersequence("abac", "cab"));
    assertEquals("aaaaaaaa", shortestCommonSupersequence("aaaaaaaa", "aaaaaaaa"));
    assertEquals("bbcccabacabab", shortestCommonSupersequence("bbabacaa", "cccababab"));
  }

  @Test
  void testBestTimeToBuyAndSellStockWithCooldown() {
    assertEquals(0, bestTimeToBuyAndSellStockWithCooldown(new int[]{1}));
    assertEquals(1, bestTimeToBuyAndSellStockWithCooldown(new int[]{1, 2}));
    assertEquals(0, bestTimeToBuyAndSellStockWithCooldown(new int[]{5, 4, 3, 2, 1}));
    assertEquals(3, bestTimeToBuyAndSellStockWithCooldown(new int[]{1, 2, 3, 0, 2}));
    assertEquals(6, bestTimeToBuyAndSellStockWithCooldown(new int[]{1, 4, 2, 7}));
    assertEquals(7, bestTimeToBuyAndSellStockWithCooldown(new int[]{1, 4, 2, 7, 6, 8}));
  }

  @Test
  void testNthTribonacciNumber() {
    assertEquals(0, nthTribonacciNumber(0));
    assertEquals(1, nthTribonacciNumber(1));
    assertEquals(1, nthTribonacciNumber(2));
    assertEquals(2, nthTribonacciNumber(3));
    assertEquals(4, nthTribonacciNumber(4));
    assertEquals(1389537, nthTribonacciNumber(25));
  }

  @Test
  void testHouseRobber() {
    assertEquals(1, houseRobber(new int[]{1}));
    assertEquals(2, houseRobber(new int[]{1, 2}));
    assertEquals(0, houseRobber(new int[]{0, 0, 0}));
    assertEquals(4, houseRobber(new int[]{1, 2, 3, 1}));
    assertEquals(12, houseRobber(new int[]{2, 7, 9, 3, 1}));
    assertEquals(16, houseRobber(new int[]{2, 1, 4, 1, 10, 1}));
  }

  @Test
  void testHouseRobberII() {
    assertEquals(3, houseRobberII(new int[]{3}));
    assertEquals(3, houseRobberII(new int[]{2, 3}));
    assertEquals(3, houseRobberII(new int[]{2, 3, 2}));
    assertEquals(4, houseRobberII(new int[]{1, 2, 3, 1}));
    assertEquals(13, houseRobberII(new int[]{2, 3, 2, 3, 3, 7}));
  }

  @Test
  void testLargestDivisibleSubset() {
    assertIterableEquals(List.of(1), largestDivisibleSubset(new int[]{1}));
    assertIterableEquals(List.of(1, 2, 4, 8), largestDivisibleSubset(new int[]{1, 2, 4, 8}));
    assertIterableEquals(List.of(4, 8, 16), largestDivisibleSubset(new int[]{3, 4, 16, 8}));
    assertIterableEquals(List.of(4, 8, 16), largestDivisibleSubset(new int[]{4, 8, 16, 3}));
  }

  @Test
  void testLongestPalindromeSubsequence() {
    assertEquals(0, longestPalindromeSubsequence(""), "Empty string should return 0");
    assertEquals(1, longestPalindromeSubsequence("a"), "Single character should return 1");
    assertEquals(5, longestPalindromeSubsequence("aaaaa"), "All same characters should return full length");
    assertEquals(1, longestPalindromeSubsequence("abc"), "No matching characters means length 1");
    assertEquals(5, longestPalindromeSubsequence("abcba"), "abcba is a palindrome of length 5");
    assertEquals(4, longestPalindromeSubsequence("bbbab"), "Longest palindromic subsequence is bbbb");
    assertEquals(2, longestPalindromeSubsequence("cbbd"), "Longest palindromic subsequence is bb");
    assertEquals(6, longestPalindromeSubsequence("agbdbaabcd"), "Longest palindromic subsequence is abdba");
    assertEquals(4, longestPalindromeSubsequence("abccb"), "Longest subsequence is bccb or abcc");
    String s = "abc" + "d".repeat(100) + "cba";
    assertEquals(106, longestPalindromeSubsequence(s));
  }

  @Test
  void testMinimumInsertionToMakeStringPalindrome() {
    // Edge cases
    assertEquals(0, minimumInsertionToMakeStringPalindrome(""), "Empty string needs 0 insertions");
    assertEquals(0, minimumInsertionToMakeStringPalindrome("a"), "Single character is already a palindrome");
    assertEquals(0, minimumInsertionToMakeStringPalindrome("aa"), "Already a palindrome");

    // Simple cases
    assertEquals(1, minimumInsertionToMakeStringPalindrome("ab"), "Insert one character to make 'aba'");
    assertEquals(2, minimumInsertionToMakeStringPalindrome("abcda"), "Insert two to make 'abdcdba'");
    assertEquals(3, minimumInsertionToMakeStringPalindrome("abcd"), "Insert three to make it a palindrome");

    // Complex cases
    assertEquals(0, minimumInsertionToMakeStringPalindrome("racecar"), "Already a palindrome");
    assertEquals(2, minimumInsertionToMakeStringPalindrome("aebcbda"), "Can insert 'd' and 'a' to make 'adbcbea'");
    assertEquals(5, minimumInsertionToMakeStringPalindrome("abcdef"), "Needs multiple insertions");

    // Repeating characters
    assertEquals(0, minimumInsertionToMakeStringPalindrome("aaaaa"), "Already a palindrome");
    assertEquals(1, minimumInsertionToMakeStringPalindrome("aaab"), "Insert 'a' at end to make 'baaab'");

    // Long palindrome with 1 mismatch
    assertEquals(2, minimumInsertionToMakeStringPalindrome("abccda"), "Insert two character to make it palindrome");
  }

}

