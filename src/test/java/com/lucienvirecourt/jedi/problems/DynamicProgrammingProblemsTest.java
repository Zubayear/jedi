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

  @Test
  void testPartitionEqualSubsetSum() {
    assertFalse(partitionEqualSubsetSum(new int[]{}), "Empty array cannot be partitioned");
    assertFalse(partitionEqualSubsetSum(new int[]{1}), "Single element cannot be partitioned");
    assertTrue(partitionEqualSubsetSum(new int[]{1, 1}), "[1,1] can be split into [1] and [1]");
    assertFalse(partitionEqualSubsetSum(new int[]{1, 2, 3, 5}), "Total sum is odd → cannot partition");
    assertTrue(partitionEqualSubsetSum(new int[]{1, 5, 11, 5}), "[1,5,5] and [11]");
    assertTrue(partitionEqualSubsetSum(new int[]{0, 0, 0, 0}), "All zero values → can partition");
    assertTrue(partitionEqualSubsetSum(new int[]{4, 4, 4, 4}), "[4,4] and [4,4]");
    assertFalse(partitionEqualSubsetSum(new int[]{4, 4, 4}), "Odd count → cannot partition equally");
    assertTrue(partitionEqualSubsetSum(new int[]{2, 2, 1, 5, 4, 1, 3}), "Multiple combinations possible");
    assertFalse(partitionEqualSubsetSum(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100}), "Sum = 900 → odd");
    assertTrue(partitionEqualSubsetSum(new int[]{100, 100, 100, 100, 100, 100, 100, 100}), "Sum = 800 → can partition");
    assertFalse(partitionEqualSubsetSum(new int[]{1, 2, 5}), "Cannot form subset with sum = 4");
    assertTrue(partitionEqualSubsetSum(new int[]{3, 1, 1, 2, 2, 1}), "Sum = 10 → can partition to 5");
    assertFalse(partitionEqualSubsetSum(new int[]{3, 2, 7}), "Sum = 12 → cannot partition equally");
  }


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
    assertEquals(0, minimumInsertionToMakeStringPalindrome(""), "Empty string needs 0 insertions");
    assertEquals(0, minimumInsertionToMakeStringPalindrome("a"), "Single character is already a palindrome");
    assertEquals(0, minimumInsertionToMakeStringPalindrome("aa"), "Already a palindrome");
    assertEquals(1, minimumInsertionToMakeStringPalindrome("ab"), "Insert one character to make 'aba'");
    assertEquals(2, minimumInsertionToMakeStringPalindrome("abcda"), "Insert two to make 'abdcdba'");
    assertEquals(3, minimumInsertionToMakeStringPalindrome("abcd"), "Insert three to make it a palindrome");
    assertEquals(0, minimumInsertionToMakeStringPalindrome("racecar"), "Already a palindrome");
    assertEquals(2, minimumInsertionToMakeStringPalindrome("aebcbda"), "Can insert 'd' and 'a' to make 'adbcbea'");
    assertEquals(5, minimumInsertionToMakeStringPalindrome("abcdef"), "Needs multiple insertions");
    assertEquals(0, minimumInsertionToMakeStringPalindrome("aaaaa"), "Already a palindrome");
    assertEquals(1, minimumInsertionToMakeStringPalindrome("aaab"), "Insert 'a' at end to make 'baaab'");
    assertEquals(2, minimumInsertionToMakeStringPalindrome("abccda"), "Insert two character to make it palindrome");
  }

  @Test
  void testDeleteOperationForTwoString() {
    assertEquals(0, deleteOperationForTwoString("", ""), "Both strings empty => 0 operations");
    assertEquals(3, deleteOperationForTwoString("abc", ""), "Delete all characters from 'abc'");
    assertEquals(4, deleteOperationForTwoString("", "abcd"), "Insert all characters of 'abcd'");
    assertEquals(2, deleteOperationForTwoString("sea", "eat"), "LCS = 'ea' → delete 's', 't'");
    assertEquals(4, deleteOperationForTwoString("leetcode", "etco"), "LCS = 'etco' → delete 'l', 'd', insert 'o'");
    assertEquals(1, deleteOperationForTwoString("abc", "ac"), "LCS = 'ac' → delete 'b'");
    assertEquals(3, deleteOperationForTwoString("abcd", "anc"), "LCS = 'ac' → delete 'b', 'd'; delete 'n' from word2");
    assertEquals(0, deleteOperationForTwoString("abc", "abc"), "Same strings → 0 operations");
    assertEquals(6, deleteOperationForTwoString("abc", "def"), "No LCS → delete all 3 from each → 3 + 3 = 6");
  }

  @Test
  void testDistinctSubsequence() {
    assertEquals(3, distinctSubsequence("rabbbit", "rabbit"));  // 3 ways
    assertEquals(5, distinctSubsequence("babgbag", "bag"));     // 5 ways
    assertEquals(0, distinctSubsequence("abc", "d"));           // 'd' not in 'abc'
    assertEquals(1, distinctSubsequence("abc", "abc"));         // Only one way, all characters used
    assertEquals(1, distinctSubsequence("abcdef", "ace"));      // Only one path: take a->c->e
    assertEquals(1, distinctSubsequence("abc", ""));
    assertEquals(0, distinctSubsequence("", "a"));
    assertEquals(1, distinctSubsequence("", ""));
    assertEquals(0, distinctSubsequence("ab", "abc"));
    assertEquals(0, distinctSubsequence("aab", "aaab"));
    assertEquals(6, distinctSubsequence("aaaa", "aa"));
    assertEquals(10, distinctSubsequence("aaaaa", "aaa")); // C(5,3) = 10
    assertEquals(5, distinctSubsequence("aaaaa", "a")); // Can pick any of the 5 'a's
    assertEquals(0, distinctSubsequence("xyzxyzxyz", "abcd")); // t not present in s

    String s = "a".repeat(1000);
    String t = "a".repeat(500);
    int result = distinctSubsequence(s, t);
    assertTrue(result > 0);
  }

  @Test
  void testDistinctSubsequenceTabulation() {
    assertEquals(3, distinctSubsequenceTabulation("rabbbit", "rabbit"));  // 3 ways
    assertEquals(5, distinctSubsequenceTabulation("babgbag", "bag"));     // 5 ways
    assertEquals(0, distinctSubsequenceTabulation("abc", "d"));           // 'd' not in 'abc'
    assertEquals(1, distinctSubsequenceTabulation("abc", "abc"));         // Only one way, all characters used
    assertEquals(1, distinctSubsequenceTabulation("abcdef", "ace"));      // Only one path: take a->c->e
    assertEquals(1, distinctSubsequenceTabulation("abc", ""));
    assertEquals(0, distinctSubsequenceTabulation("", "a"));
    assertEquals(1, distinctSubsequenceTabulation("", ""));
    assertEquals(0, distinctSubsequenceTabulation("ab", "abc"));
    assertEquals(0, distinctSubsequenceTabulation("aab", "aaab"));
    assertEquals(6, distinctSubsequenceTabulation("aaaa", "aa"));
    assertEquals(10, distinctSubsequenceTabulation("aaaaa", "aaa")); // C(5,3) = 10
    assertEquals(5, distinctSubsequenceTabulation("aaaaa", "a")); // Can pick any of the 5 'a's
    assertEquals(0, distinctSubsequenceTabulation("xyzxyzxyz", "abcd")); // t not present in s

    String s = "a".repeat(1000);
    String t = "a".repeat(500);
    int result = distinctSubsequenceTabulation(s, t);
    assertTrue(result > 0);
  }


  @Test
  public void testEditDistance() {
    assertEquals(3, editDistance("horse", "ros"), "Test Case 1: horse to ros");
    assertEquals(5, editDistance("intention", "execution"), "Test Case 2: intention to execution");
    assertEquals(3, editDistance("", "abc"), "Test Case 3: Empty word1");
    assertEquals(3, editDistance("abc", ""), "Test Case 4: Empty word2");
    assertEquals(0, editDistance("", ""), "Test Case 5: Both strings empty");
    assertEquals(0, editDistance("abc", "abc"), "Test Case 6: Identical strings");
    assertEquals(1, editDistance("abc", "axc"), "Test Case 7: One char difference (replace)");
    assertEquals(1, editDistance("abc", "abdc"), "Test Case 8: One char difference (insert)");
    assertEquals(1, editDistance("abdc", "abc"), "Test Case 9: One char difference (delete)");
    assertEquals(3, editDistance("abcdef", "azced"), "Test Case 10: Longer strings, multiple operations");
    assertEquals(1, editDistance("apple", "aple"), "Test Case 11: Common prefix/suffix, different middle");
    assertEquals(3, editDistance("kitten", "sitting"), "Test Case 12: Classic kitten to sitting");
    assertEquals(35, editDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "pseudopseudohypoparathyroidism"), "Test Case 13: Very long strings with many changes");
  }

  @Test
  public void testEditDistanceTabulation() {
    assertEquals(2, editDistanceTabulation("abc", "axbyc"), "Test Case 14: Insert multiple characters");
    assertEquals(2, editDistanceTabulation("axbyc", "abc"), "Test Case 15: Delete multiple characters");
    assertEquals(3, editDistanceTabulation("abc", "xyz"), "Test Case 16: All characters different (replace all)");
    assertEquals(6, editDistanceTabulation("algorithm", "altruistic"), "Test Case 17: Medium length, mixed operations");
    assertEquals(1, editDistanceTabulation("a", "b"), "Test Case 18: Single character replace");
    assertEquals(1, editDistanceTabulation("a", ""), "Test Case 19: Single character to empty");
    assertEquals(1, editDistanceTabulation("", "a"), "Test Case 20: Empty to single character");
    assertEquals(1, editDistanceTabulation("abc", "ab"), "Test Case 21: Word1 is longer prefix of word2");
    assertEquals(1, editDistanceTabulation("ab", "abc"), "Test Case 22: Word2 is longer prefix of word1");
    assertEquals(3, editDistanceTabulation("testing", "test"), "Test Case 23: Word2 is a prefix of word1");
    assertEquals(3, editDistanceTabulation("test", "testing"), "Test Case 24: Word1 is a prefix of word2");
    assertEquals(1, editDistanceTabulation("banana", "banan"), "Test Case 25: Delete from end");
    assertEquals(1, editDistanceTabulation("banan", "banana"), "Test Case 26: Insert at end");
    assertEquals(3, editDistanceTabulation("saturday", "sunday"), "Test Case 27: Common suffix, different prefix");
  }

  @Test
  public void testMinimumDifference() {
    assertEquals(2, minimumDifference(new int[]{3, 9, 7, 3}));
    assertEquals(1, minimumDifference(new int[]{1, 2}));
    assertEquals(0, minimumDifference(new int[]{1, 2, 3, 4}));
    assertEquals(0, minimumDifference(new int[]{5, 5, 5, 5, 5, 5}));
    assertEquals(5, minimumDifference(new int[]{1, 1, 1, 1, 1, 10}));
    assertEquals(0, minimumDifference(new int[]{1000, 1000, 1000, 1000}));
    assertEquals(1, minimumDifference(new int[]{1, 2, 4, 8, 16, 32}));
    assertEquals(0, minimumDifference(new int[]{1, 3, 5, 7, 9, 11}));
    // Test case 9: Long uniform array
    int[] nums9 = new int[20];
    for (int i = 0; i < 20; i++) nums9[i] = 10;
    int expected9 = 0; // 10*10 = 100 vs 100
    assertEquals(expected9, minimumDifference(nums9));

    // Test case 10: Prime numbers
    int[] nums10 = {2, 3, 5, 7, 11, 13};
    int expected10 = 1; // The best partition may not be perfect
    assertEquals(expected10, minimumDifference(nums10));
  }

}
