package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingProblemsTest {

  DynamicProgrammingProblems dp;

  @BeforeEach
  void setup() {
    dp = new DynamicProgrammingProblems();
  }

  @AfterEach
  void teardown() {
    dp = null;
  }

  @Test
  void testClimbStairs() {
    assertEquals(1, dp.climbStairs(1));
    assertEquals(2, dp.climbStairs(2));
    assertEquals(3, dp.climbStairs(3));
    assertEquals(5, dp.climbStairs(4));
    assertEquals(13, dp.climbStairs(6));
    assertEquals(1836311903, dp.climbStairs(45));
  }

  @Test
  void testMinCostClimbingStairs() {
    assertEquals(1, dp.minCostClimbingStairs(new int[]{1, 2}));
    assertEquals(0, dp.minCostClimbingStairs(new int[]{0, 0}));
    assertEquals(15, dp.minCostClimbingStairs(new int[]{10, 15, 20}));
    assertEquals(6, dp.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    assertEquals(2, dp.minCostClimbingStairs(new int[]{1, 1, 1, 1}));
  }

  @Test
  void testMinimumPathSum() {
    assertEquals(7, dp.minimumPathSum(new int[][]{
      {1, 3, 1},
      {1, 5, 1},
      {4, 2, 1}
    }));
    assertEquals(12, dp.minimumPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
  }

  @Test
  void testMinimumFallingPathSum() {
    assertEquals(13, dp.minimumFallingPathSum(new int[][]{
      {2, 1, 3},
      {6, 5, 4},
      {7, 8, 9}
    }));
    assertEquals(-59, dp.minimumFallingPathSum(new int[][]{
      {-19, 57},
      {-40, -5},
    }));
  }

  @Test
  void testCanPartition() {
    assertTrue(dp.canPartition(new int[]{4, 3, 2, 3, 5, 2, 1}));
    assertFalse(dp.canPartition(new int[]{1, 2, 3, 5}));
  }

  @Test
  void testPartitionEqualSubsetSum() {
    assertFalse(dp.partitionEqualSubsetSum(new int[]{}), "Empty array cannot be partitioned");
    assertFalse(dp.partitionEqualSubsetSum(new int[]{1}), "Single element cannot be partitioned");
    assertTrue(dp.partitionEqualSubsetSum(new int[]{1, 1}), "[1,1] can be split into [1] and [1]");
    assertFalse(dp.partitionEqualSubsetSum(new int[]{1, 2, 3, 5}), "Total sum is odd → cannot partition");
    assertTrue(dp.partitionEqualSubsetSum(new int[]{1, 5, 11, 5}), "[1,5,5] and [11]");
    assertTrue(dp.partitionEqualSubsetSum(new int[]{0, 0, 0, 0}), "All zero values → can partition");
    assertTrue(dp.partitionEqualSubsetSum(new int[]{4, 4, 4, 4}), "[4,4] and [4,4]");
    assertFalse(dp.partitionEqualSubsetSum(new int[]{4, 4, 4}), "Odd count → cannot partition equally");
    assertTrue(dp.partitionEqualSubsetSum(new int[]{2, 2, 1, 5, 4, 1, 3}), "Multiple combinations possible");
    assertFalse(dp.partitionEqualSubsetSum(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100}), "Sum = 900 → odd");
    assertTrue(dp.partitionEqualSubsetSum(new int[]{100, 100, 100, 100, 100, 100, 100, 100}), "Sum = 800 → can partition");
    assertFalse(dp.partitionEqualSubsetSum(new int[]{1, 2, 5}), "Cannot form subset with sum = 4");
    assertTrue(dp.partitionEqualSubsetSum(new int[]{3, 1, 1, 2, 2, 1}), "Sum = 10 → can partition to 5");
    assertFalse(dp.partitionEqualSubsetSum(new int[]{3, 2, 7}), "Sum = 12 → cannot partition equally");
  }

  @Test
  void testCoinChange() {
    assertEquals(3, dp.coinChange(new int[]{1, 2, 5}, 11));
  }

  @Test
  void testFindTargetSumWays() {
    assertEquals(5, dp.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
  }

  @Test
  void testLongestCommonSubsequence() {
    assertEquals("ace", dp.longestCommonSubsequence("abcde", "ace"));
    assertEquals("abc", dp.longestCommonSubsequence("abc", "abc"));
    assertEquals("", dp.longestCommonSubsequence("abc", "def"));
    assertEquals("aaa", dp.longestCommonSubsequence("aaa", "aaa"));
    assertEquals("GTAB", dp.longestCommonSubsequence("AGGTAB", "GXTXAYB"));
    assertEquals("", dp.longestCommonSubsequence("", "abc"));
    assertEquals("", dp.longestCommonSubsequence("abc", ""));
    assertEquals("", dp.longestCommonSubsequence("", ""));
  }

  @Test
  void testLongestCommonSubstring() {
    assertEquals("a", dp.longestCommonSubstring("abcde", "ace"));
    assertEquals("abc", dp.longestCommonSubstring("abc", "abc"));
    assertEquals("bc", dp.longestCommonSubstring("abcr", "pbctr"));
    assertEquals("cry", dp.longestCommonSubstring("abcry", "pbcycry"));
    assertEquals("", dp.longestCommonSubstring("abc", "def"));
  }

  @Test
  void testShortestCommonSupersequence() {
    assertEquals("cabac", dp.shortestCommonSupersequence("abac", "cab"));
    assertEquals("aaaaaaaa", dp.shortestCommonSupersequence("aaaaaaaa", "aaaaaaaa"));
    assertEquals("bbcccabacabab", dp.shortestCommonSupersequence("bbabacaa", "cccababab"));
  }

  @Test
  void testBestTimeToBuyAndSellStockWithCooldown() {
    assertEquals(0, dp.bestTimeToBuyAndSellStockWithCooldown(new int[]{1}));
    assertEquals(1, dp.bestTimeToBuyAndSellStockWithCooldown(new int[]{1, 2}));
    assertEquals(0, dp.bestTimeToBuyAndSellStockWithCooldown(new int[]{5, 4, 3, 2, 1}));
    assertEquals(3, dp.bestTimeToBuyAndSellStockWithCooldown(new int[]{1, 2, 3, 0, 2}));
    assertEquals(6, dp.bestTimeToBuyAndSellStockWithCooldown(new int[]{1, 4, 2, 7}));
    assertEquals(7, dp.bestTimeToBuyAndSellStockWithCooldown(new int[]{1, 4, 2, 7, 6, 8}));
  }

  @Test
  void testNthTribonacciNumber() {
    assertEquals(0, dp.nthTribonacciNumber(0));
    assertEquals(1, dp.nthTribonacciNumber(1));
    assertEquals(1, dp.nthTribonacciNumber(2));
    assertEquals(2, dp.nthTribonacciNumber(3));
    assertEquals(4, dp.nthTribonacciNumber(4));
    assertEquals(1389537, dp.nthTribonacciNumber(25));
  }

  @Test
  void testHouseRobber() {
    assertEquals(1, dp.houseRobber(new int[]{1}));
    assertEquals(2, dp.houseRobber(new int[]{1, 2}));
    assertEquals(0, dp.houseRobber(new int[]{0, 0, 0}));
    assertEquals(4, dp.houseRobber(new int[]{1, 2, 3, 1}));
    assertEquals(12, dp.houseRobber(new int[]{2, 7, 9, 3, 1}));
    assertEquals(16, dp.houseRobber(new int[]{2, 1, 4, 1, 10, 1}));
  }

  @Test
  void testHouseRobberII() {
    assertEquals(3, dp.houseRobberII(new int[]{3}));
    assertEquals(3, dp.houseRobberII(new int[]{2, 3}));
    assertEquals(3, dp.houseRobberII(new int[]{2, 3, 2}));
    assertEquals(4, dp.houseRobberII(new int[]{1, 2, 3, 1}));
    assertEquals(13, dp.houseRobberII(new int[]{2, 3, 2, 3, 3, 7}));
  }

  @Test
  void testLargestDivisibleSubset() {
    assertIterableEquals(List.of(1), dp.largestDivisibleSubset(new int[]{1}));
    assertIterableEquals(List.of(1, 2, 4, 8), dp.largestDivisibleSubset(new int[]{1, 2, 4, 8}));
    assertIterableEquals(List.of(4, 8, 16), dp.largestDivisibleSubset(new int[]{3, 4, 16, 8}));
    assertIterableEquals(List.of(4, 8, 16), dp.largestDivisibleSubset(new int[]{4, 8, 16, 3}));
  }

  @Test
  void testLongestPalindromeSubsequence() {
    assertEquals(0, dp.longestPalindromeSubsequence(""), "Empty string should return 0");
    assertEquals(1, dp.longestPalindromeSubsequence("a"), "Single character should return 1");
    assertEquals(5, dp.longestPalindromeSubsequence("aaaaa"), "All same characters should return full length");
    assertEquals(1, dp.longestPalindromeSubsequence("abc"), "No matching characters means length 1");
    assertEquals(5, dp.longestPalindromeSubsequence("abcba"), "abcba is a palindrome of length 5");
    assertEquals(4, dp.longestPalindromeSubsequence("bbbab"), "Longest palindromic subsequence is bbbb");
    assertEquals(2, dp.longestPalindromeSubsequence("cbbd"), "Longest palindromic subsequence is bb");
    assertEquals(6, dp.longestPalindromeSubsequence("agbdbaabcd"), "Longest palindromic subsequence is abdba");
    assertEquals(4, dp.longestPalindromeSubsequence("abccb"), "Longest subsequence is bccb or abcc");
    String s = "abc" + "d".repeat(100) + "cba";
    assertEquals(106, dp.longestPalindromeSubsequence(s));
  }

  @Test
  void testMinimumInsertionToMakeStringPalindrome() {
    assertEquals(0, dp.minimumInsertionToMakeStringPalindrome(""), "Empty string needs 0 insertions");
    assertEquals(0, dp.minimumInsertionToMakeStringPalindrome("a"), "Single character is already a palindrome");
    assertEquals(0, dp.minimumInsertionToMakeStringPalindrome("aa"), "Already a palindrome");
    assertEquals(1, dp.minimumInsertionToMakeStringPalindrome("ab"), "Insert one character to make 'aba'");
    assertEquals(2, dp.minimumInsertionToMakeStringPalindrome("abcda"), "Insert two to make 'abdcdba'");
    assertEquals(3, dp.minimumInsertionToMakeStringPalindrome("abcd"), "Insert three to make it a palindrome");
    assertEquals(0, dp.minimumInsertionToMakeStringPalindrome("racecar"), "Already a palindrome");
    assertEquals(2, dp.minimumInsertionToMakeStringPalindrome("aebcbda"), "Can insert 'd' and 'a' to make 'adbcbea'");
    assertEquals(5, dp.minimumInsertionToMakeStringPalindrome("abcdef"), "Needs multiple insertions");
    assertEquals(0, dp.minimumInsertionToMakeStringPalindrome("aaaaa"), "Already a palindrome");
    assertEquals(1, dp.minimumInsertionToMakeStringPalindrome("aaab"), "Insert 'a' at end to make 'baaab'");
    assertEquals(2, dp.minimumInsertionToMakeStringPalindrome("abccda"), "Insert two character to make it palindrome");
  }

  @Test
  void testDeleteOperationForTwoString() {
    assertEquals(0, dp.deleteOperationForTwoString("", ""), "Both strings empty => 0 operations");
    assertEquals(3, dp.deleteOperationForTwoString("abc", ""), "Delete all characters from 'abc'");
    assertEquals(4, dp.deleteOperationForTwoString("", "abcd"), "Insert all characters of 'abcd'");
    assertEquals(2, dp.deleteOperationForTwoString("sea", "eat"), "LCS = 'ea' → delete 's', 't'");
    assertEquals(4, dp.deleteOperationForTwoString("leetcode", "etco"), "LCS = 'etco' → delete 'l', 'd', insert 'o'");
    assertEquals(1, dp.deleteOperationForTwoString("abc", "ac"), "LCS = 'ac' → delete 'b'");
    assertEquals(3, dp.deleteOperationForTwoString("abcd", "anc"), "LCS = 'ac' → delete 'b', 'd'; delete 'n' from word2");
    assertEquals(0, dp.deleteOperationForTwoString("abc", "abc"), "Same strings → 0 operations");
    assertEquals(6, dp.deleteOperationForTwoString("abc", "def"), "No LCS → delete all 3 from each → 3 + 3 = 6");
  }

  @Test
  void testDistinctSubsequence() {
    assertEquals(3, dp.distinctSubsequence("rabbbit", "rabbit"));  // 3 ways
    assertEquals(5, dp.distinctSubsequence("babgbag", "bag"));     // 5 ways
    assertEquals(0, dp.distinctSubsequence("abc", "d"));           // 'd' not in 'abc'
    assertEquals(1, dp.distinctSubsequence("abc", "abc"));         // Only one way, all characters used
    assertEquals(1, dp.distinctSubsequence("abcdef", "ace"));      // Only one path: take a->c->e
    assertEquals(1, dp.distinctSubsequence("abc", ""));
    assertEquals(0, dp.distinctSubsequence("", "a"));
    assertEquals(1, dp.distinctSubsequence("", ""));
    assertEquals(0, dp.distinctSubsequence("ab", "abc"));
    assertEquals(0, dp.distinctSubsequence("aab", "aaab"));
    assertEquals(6, dp.distinctSubsequence("aaaa", "aa"));
    assertEquals(10, dp.distinctSubsequence("aaaaa", "aaa")); // C(5,3) = 10
    assertEquals(5, dp.distinctSubsequence("aaaaa", "a")); // Can pick any of the 5 'a's
    assertEquals(0, dp.distinctSubsequence("xyzxyzxyz", "abcd")); // t not present in s

    String s = "a".repeat(1000);
    String t = "a".repeat(500);
    int result = dp.distinctSubsequence(s, t);
    assertTrue(result > 0);
  }

  @Test
  void testDistinctSubsequenceTabulation() {
    assertEquals(3, dp.distinctSubsequenceTabulation("rabbbit", "rabbit"));  // 3 ways
    assertEquals(5, dp.distinctSubsequenceTabulation("babgbag", "bag"));     // 5 ways
    assertEquals(0, dp.distinctSubsequenceTabulation("abc", "d"));           // 'd' not in 'abc'
    assertEquals(1, dp.distinctSubsequenceTabulation("abc", "abc"));         // Only one way, all characters used
    assertEquals(1, dp.distinctSubsequenceTabulation("abcdef", "ace"));      // Only one path: take a->c->e
    assertEquals(1, dp.distinctSubsequenceTabulation("abc", ""));
    assertEquals(0, dp.distinctSubsequenceTabulation("", "a"));
    assertEquals(1, dp.distinctSubsequenceTabulation("", ""));
    assertEquals(0, dp.distinctSubsequenceTabulation("ab", "abc"));
    assertEquals(0, dp.distinctSubsequenceTabulation("aab", "aaab"));
    assertEquals(6, dp.distinctSubsequenceTabulation("aaaa", "aa"));
    assertEquals(10, dp.distinctSubsequenceTabulation("aaaaa", "aaa")); // C(5,3) = 10
    assertEquals(5, dp.distinctSubsequenceTabulation("aaaaa", "a")); // Can pick any of the 5 'a's
    assertEquals(0, dp.distinctSubsequenceTabulation("xyzxyzxyz", "abcd")); // t not present in s

    String s = "a".repeat(1000);
    String t = "a".repeat(500);
    int result = dp.distinctSubsequenceTabulation(s, t);
    assertTrue(result > 0);
  }


  @Test
  public void testEditDistance() {
    assertEquals(3, dp.editDistance("horse", "ros"), "Test Case 1: horse to ros");
    assertEquals(5, dp.editDistance("intention", "execution"), "Test Case 2: intention to execution");
    assertEquals(3, dp.editDistance("", "abc"), "Test Case 3: Empty word1");
    assertEquals(3, dp.editDistance("abc", ""), "Test Case 4: Empty word2");
    assertEquals(0, dp.editDistance("", ""), "Test Case 5: Both strings empty");
    assertEquals(0, dp.editDistance("abc", "abc"), "Test Case 6: Identical strings");
    assertEquals(1, dp.editDistance("abc", "axc"), "Test Case 7: One char difference (replace)");
    assertEquals(1, dp.editDistance("abc", "abdc"), "Test Case 8: One char difference (insert)");
    assertEquals(1, dp.editDistance("abdc", "abc"), "Test Case 9: One char difference (delete)");
    assertEquals(3, dp.editDistance("abcdef", "azced"), "Test Case 10: Longer strings, multiple operations");
    assertEquals(1, dp.editDistance("apple", "aple"), "Test Case 11: Common prefix/suffix, different middle");
    assertEquals(3, dp.editDistance("kitten", "sitting"), "Test Case 12: Classic kitten to sitting");
    assertEquals(35, dp.editDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "pseudopseudohypoparathyroidism"), "Test Case 13: Very long strings with many changes");
  }

  @Test
  public void testEditDistanceTabulation() {
    assertEquals(2, dp.editDistanceTabulation("abc", "axbyc"), "Test Case 14: Insert multiple characters");
    assertEquals(2, dp.editDistanceTabulation("axbyc", "abc"), "Test Case 15: Delete multiple characters");
    assertEquals(3, dp.editDistanceTabulation("abc", "xyz"), "Test Case 16: All characters different (replace all)");
    assertEquals(6, dp.editDistanceTabulation("algorithm", "altruistic"), "Test Case 17: Medium length, mixed operations");
    assertEquals(1, dp.editDistanceTabulation("a", "b"), "Test Case 18: Single character replace");
    assertEquals(1, dp.editDistanceTabulation("a", ""), "Test Case 19: Single character to empty");
    assertEquals(1, dp.editDistanceTabulation("", "a"), "Test Case 20: Empty to single character");
    assertEquals(1, dp.editDistanceTabulation("abc", "ab"), "Test Case 21: Word1 is longer prefix of word2");
    assertEquals(1, dp.editDistanceTabulation("ab", "abc"), "Test Case 22: Word2 is longer prefix of word1");
    assertEquals(3, dp.editDistanceTabulation("testing", "test"), "Test Case 23: Word2 is a prefix of word1");
    assertEquals(3, dp.editDistanceTabulation("test", "testing"), "Test Case 24: Word1 is a prefix of word2");
    assertEquals(1, dp.editDistanceTabulation("banana", "banan"), "Test Case 25: Delete from end");
    assertEquals(1, dp.editDistanceTabulation("banan", "banana"), "Test Case 26: Insert at end");
    assertEquals(3, dp.editDistanceTabulation("saturday", "sunday"), "Test Case 27: Common suffix, different prefix");
  }

  @Test
  public void testMinimumDifference() {
    assertEquals(2, dp.minimumDifference(new int[]{3, 9, 7, 3}));
    assertEquals(1, dp.minimumDifference(new int[]{1, 2}));
    assertEquals(0, dp.minimumDifference(new int[]{1, 2, 3, 4}));
    assertEquals(0, dp.minimumDifference(new int[]{5, 5, 5, 5, 5, 5}));
    assertEquals(5, dp.minimumDifference(new int[]{1, 1, 1, 1, 1, 10}));
    assertEquals(0, dp.minimumDifference(new int[]{1000, 1000, 1000, 1000}));
    assertEquals(1, dp.minimumDifference(new int[]{1, 2, 4, 8, 16, 32}));
    assertEquals(0, dp.minimumDifference(new int[]{1, 3, 5, 7, 9, 11}));
    // Test case 9: Long uniform array
    int[] nums9 = new int[20];
    for (int i = 0; i < 20; i++) nums9[i] = 10;
    int expected9 = 0; // 10*10 = 100 vs 100
    assertEquals(expected9, dp.minimumDifference(nums9));

    // Test case 10: Prime numbers
    int[] nums10 = {2, 3, 5, 7, 11, 13};
    int expected10 = 1; // The best partition may not be perfect
    assertEquals(expected10, dp.minimumDifference(nums10));
  }

  @Test
  void testLis() {
    assertEquals(3, dp.lis(new int[]{5, 4, 11, 1, 6, 18}));
    assertEquals(3, dp.lis(new int[]{5, 4, 11, 1, 16, 8}));
  }

  @Test
  public void testRecursiveLIS() {
    assertEquals(4, dp.longestIncreasingSubsequence(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    assertEquals(1, dp.longestIncreasingSubsequence(new int[]{5}));
    assertEquals(0, dp.longestIncreasingSubsequence(new int[]{}));
    assertEquals(1, dp.longestIncreasingSubsequence(new int[]{5, 5, 5}));
    assertEquals(6, dp.longestIncreasingSubsequence(new int[]{1, 2, 3, 4, 5, 6}));
  }

  @Test
  public void testMemoizedLIS() {
    int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
    int[][] cache = new int[arr.length][arr.length + 1];
    for (int[] row : cache) Arrays.fill(row, -1);
    assertEquals(4, dp.lengthOfLIS(0, -1, arr, arr.length, cache));
  }

  @Test
  public void testTabulationLIS() {
    assertEquals(4, dp.longestIncreasingSubsequenceTabulation(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    assertEquals(1, dp.longestIncreasingSubsequenceTabulation(new int[]{5}));
    assertEquals(0, dp.longestIncreasingSubsequenceTabulation(new int[]{}));
    assertEquals(1, dp.longestIncreasingSubsequenceTabulation(new int[]{5, 5, 5}));
    assertEquals(6, dp.longestIncreasingSubsequenceTabulation(new int[]{1, 2, 3, 4, 5, 6}));
  }

  @Test
  public void testDPBottomUpLIS() {
    assertEquals(4, dp.lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    assertEquals(1, dp.lis(new int[]{5}));
    assertEquals(0, dp.lis(new int[]{}));
    assertEquals(1, dp.lis(new int[]{5, 5, 5}));
    assertEquals(6, dp.lis(new int[]{1, 2, 3, 4, 5, 6}));
    assertEquals(3, dp.lis(new int[]{5, 4, 11, 1, 16, 8}));
  }

  @Test
  public void testLISChain() {
    int[] result = dp.lisChain(new int[]{5, 4, 11, 1, 16, 8});
    assertTrue(isIncreasing(result));
    assertEquals(3, result.length);

    int[] singleElement = dp.lisChain(new int[]{5});
    assertArrayEquals(new int[]{5}, singleElement);

    int[] emptyArray = dp.lisChain(new int[]{});
    assertEquals(0, emptyArray.length);
  }

  private boolean isIncreasing(int[] arr) {
    for (int i = 1; i < arr.length; ++i) {
      if (arr[i] <= arr[i - 1]) return false;
    }
    return true;
  }

  @Test
  void testHouseRobberRecur() {
    assertEquals(4, dp.houseRobber(3, new int[]{1, 2, 3, 1}));
    assertEquals(12, dp.houseRobber(4, new int[]{2, 7, 9, 3, 1}));
    assertEquals(4, dp.houseRobber(3, new int[]{2, 1, 1, 2}));

    assertEquals(5, dp.houseRobber(0, new int[]{5}));
    assertEquals(9, dp.houseRobber(1, new int[]{9, 1}));
    assertEquals(7, dp.houseRobber(1, new int[]{3, 7}));
    assertEquals(0, dp.houseRobber(3, new int[]{0, 0, 0, 0}));
    assertEquals(9, dp.houseRobber(4, new int[]{3, 3, 3, 3, 3}));
    assertEquals(41, dp.houseRobber(6, new int[]{6, 7, 1, 30, 8, 2, 4}));
    assertEquals(132, dp.houseRobber(6, new int[]{20, 30, 50, 10, 2, 40, 60}));
  }

  @Test
  public void testBuySellWithTransactionFee() {
    assertEquals(8, dp.bestTimeToBuyAndSellStockWithTransactionFee(new int[]{1, 3, 2, 8, 4, 9}, 2));
    assertEquals(0, dp.bestTimeToBuyAndSellStockWithTransactionFee(new int[]{1}, 2));
    assertEquals(0, dp.bestTimeToBuyAndSellStockWithTransactionFee(new int[]{5, 4, 3, 2, 1}, 1));
    assertEquals(3, dp.bestTimeToBuyAndSellStockWithTransactionFee(new int[]{1, 5}, 1));
    assertEquals(0, dp.bestTimeToBuyAndSellStockWithTransactionFee(new int[]{1, 2}, 2));
    assertEquals(7, dp.bestTimeToBuyAndSellStockWithTransactionFee(new int[]{2, 1, 4, 5, 2, 9, 7}, 2));
  }

  private int bestTimeToBuyAndSellStockWithCooldownWrapper(int[] prices) {
    return dp.bestTimeToBuyAndSellStockWithCooldown(0, 1, prices, prices.length);
  }

  @Test
  public void testBuySellWithCooldown() {
    assertEquals(3, bestTimeToBuyAndSellStockWithCooldownWrapper(new int[]{1, 2, 3, 0, 2}));
    assertEquals(0, bestTimeToBuyAndSellStockWithCooldownWrapper(new int[]{1}));
    assertEquals(0, bestTimeToBuyAndSellStockWithCooldownWrapper(new int[]{5, 4, 3}));
    assertEquals(1, bestTimeToBuyAndSellStockWithCooldownWrapper(new int[]{1, 2}));
    assertEquals(2, bestTimeToBuyAndSellStockWithCooldownWrapper(new int[]{1, 2, 3}));
    assertEquals(5, bestTimeToBuyAndSellStockWithCooldownWrapper(new int[]{1, 2, 3, 0, 2, 1, 4}));
  }

  @Test
  void testLongestStringChain() {
    String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
    assertEquals(4, dp.longestStringChain(words));
    words = new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
    assertEquals(5, dp.longestStringChain(words));
    words = new String[]{"abcd", "dbqca"};
    assertEquals(1, dp.longestStringChain(words));
    words = new String[]{"abc"};
    assertEquals(1, dp.longestStringChain(words));
    words = new String[]{"abc", "def", "ghi"};
    assertEquals(1, dp.longestStringChain(words));
    words = new String[]{};
    assertEquals(0, dp.longestStringChain(words));
    words = new String[]{"a", "a", "ab", "abc", "abcd", "abcd"};
    assertEquals(4, dp.longestStringChain(words));
    words = new String[]{"a", "ab", "abc", "abcd", "abcde", "abcdef", "abcdefg", "abcdefgh",
      "abcdefghi", "abcdefghij", "abcdefghijk", "abcdefghijkl",
      "abcdefghijklm", "abcdefghijklmn", "abcdefghijklmno", "abcdefghijklmnop"};
    assertEquals(16, dp.longestStringChain(words));
    words = new String[]{"abc", "xyz", "uvw"};
    assertEquals(1, dp.longestStringChain(words));
    words = new String[]{"bdca", "bda", "ba", "a"};
    assertEquals(4, dp.longestStringChain(words));
    words = new String[]{"a", "ab", "abc", "x", "xy", "xyz"};
    assertEquals(3, dp.longestStringChain(words)); // Two chains: a→ab→abc and x→xy→xyz
  }

  @Test
  void testNumberOfLis() {
    assertEquals(2, dp.numberOfLis(new int[]{1, 3, 5, 4, 7}));
    assertEquals(5, dp.numberOfLis(new int[]{2, 2, 2, 2, 2}));
    assertEquals(2, dp.numberOfLis(new int[]{1, 3, 5, 4, 7}));
    assertEquals(3, dp.numberOfLis(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));
    assertEquals(0, dp.numberOfLis(null));
    assertEquals(0, dp.numberOfLis(new int[]{}));
    assertEquals(1, dp.numberOfLis(new int[]{5}));
    assertEquals(5, dp.numberOfLis(new int[]{5, 5, 5, 5, 5}));
    assertEquals(5, dp.numberOfLis(new int[]{5, 4, 3, 2, 1}));
    assertEquals(4, dp.numberOfLis(new int[]{1, 2, 3, 1, 2, 3}));
    assertEquals(1, dp.numberOfLis(new int[]{1, 2, 3, 4, 5, 6}));
    assertEquals(27, dp.numberOfLis(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}));
  }

//  @Test
//  void testPartitionCount() {
//    // Basic test cases
//    assertEquals(1, partitionCount(new int[]{1, 2, 3}, 3)); // Only [3] sums to 3
//    assertEquals(2, partitionCount(new int[]{1, 2, 3}, 4)); // [1,3] and [4] sum to 4
//    assertEquals(3, partitionCount(new int[]{1, 1, 2, 3}, 3)); // [1,2], [3], [1,1,1] sum to 3
//
//    // Edge cases
//    assertEquals(0, partitionCount(new int[]{}, 1)); // Empty array
//    assertEquals(0, partitionCount(new int[]{5, 6, 7}, 4)); // No subset sums to 4
//    assertEquals(1, partitionCount(new int[]{0}, 0)); // Single element 0 sums to 0
//
//    // Special cases with zeros
//    assertEquals(2, partitionCount(new int[]{0, 0, 0}, 0)); // Multiple ways to get sum 0
//    assertEquals(4, partitionCount(new int[]{0, 0, 1}, 1)); // [1], [0,1], [0,0,1], [0,0,1] sum to 1
//
//    // Larger test cases
//    assertEquals(4, partitionCount(new int[]{1, 2, 3, 4, 5}, 10)); // Multiple combinations sum to 10
//    assertEquals(6, partitionCount(new int[]{2, 3, 5, 6, 8, 10}, 10)); // Multiple combinations sum to 10
//  }

  @Test
  void testCoinChangeOptimal() {
    // Basic test cases
    assertEquals(3, dp.coinChangeOptimal(new int[]{1, 2, 5}, 11)); // 5 + 5 + 1 = 11
    assertEquals(2, dp.coinChangeOptimal(new int[]{1, 3, 4, 5}, 7)); // 3 + 4 = 7
    assertEquals(20, dp.coinChangeOptimal(new int[]{1, 2, 5}, 100)); // Minimum 20 coins needed

    // Edge cases
    assertEquals(0, dp.coinChangeOptimal(new int[]{1}, 0)); // Amount is 0
    assertEquals(-1, dp.coinChangeOptimal(new int[]{2}, 3)); // Cannot make amount 3 with coin 2
    assertEquals(1, dp.coinChangeOptimal(new int[]{1}, 1)); // Single coin equals amount

    // Special cases
    assertEquals(-1, dp.coinChangeOptimal(new int[]{2, 5, 10}, 3)); // Cannot make amount 3
    assertEquals(1, dp.coinChangeOptimal(new int[]{1, 2, 5}, 5)); // Single coin of value 5

    // Larger denominations
    assertEquals(5, dp.coinChangeOptimal(new int[]{2, 5, 10, 20, 50}, 94)); // 50 + 20 + 20 + 4*1 = 94
    assertEquals(2, dp.coinChangeOptimal(new int[]{25, 10, 5}, 30)); // 25 + 5 = 30
  }

  @Test
  void testLongestCommonSubsequenceRecur() {
    // Basic test cases
    assertEquals(3, dp.longestCommonSubsequenceRecur("abcde", "ace")); // LCS is "ace"
    assertEquals(3, dp.longestCommonSubsequenceRecur("abc", "abc")); // LCS is "abc"
    assertEquals(0, dp.longestCommonSubsequenceRecur("abc", "def")); // No common subsequence

    // Edge cases
    assertEquals(0, dp.longestCommonSubsequenceRecur("", "abc")); // Empty first string
    assertEquals(0, dp.longestCommonSubsequenceRecur("abc", "")); // Empty second string
    assertEquals(0, dp.longestCommonSubsequenceRecur("", "")); // Both strings empty

    // Special cases
    assertEquals(1, dp.longestCommonSubsequenceRecur("a", "a")); // Single character match
    assertEquals(4, dp.longestCommonSubsequenceRecur("AGGTAB", "GXTXAYB")); // LCS is "GTAB"
    assertEquals(3, dp.longestCommonSubsequenceRecur("abcdef", "acf")); // LCS is "acf"

    // Longer strings
    assertEquals(4, dp.longestCommonSubsequenceRecur("abcdefghij", "ecdgi")); // LCS is "cdgi"
    assertEquals(6, dp.longestCommonSubsequenceRecur("programming", "gaming")); // LCS is "gamg"
  }

  @Test
  void testMaxProfit() {
    // Create an instance to test the non-static method
    DynamicProgrammingProblems dp = new DynamicProgrammingProblems();

    // Basic test cases
    assertEquals(6, dp.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); // Buy at 0, sell at 3, buy at 1, sell at 4
    assertEquals(4, dp.maxProfit(new int[]{1, 2, 3, 4, 5})); // Buy at 1, sell at 5
    assertEquals(0, dp.maxProfit(new int[]{7, 6, 4, 3, 1})); // No profit possible

    // Edge cases
    assertEquals(0, dp.maxProfit(new int[]{1})); // Single price, no transactions possible
    assertEquals(0, dp.maxProfit(new int[]{})); // Empty array
    assertEquals(0, dp.maxProfit(new int[]{5, 5, 5, 5})); // No price change

    // Special cases
    assertEquals(7, dp.maxProfit(new int[]{3, 2, 6, 5, 0, 3})); // Buy at 2, sell at 6, buy at 0, sell at 3
    assertEquals(13, dp.maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9})); // Multiple transactions

    // Test with at most 2 transactions
    assertEquals(6, dp.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    assertEquals(0, dp.maxProfit(new int[]{1, 1, 1, 1, 1}));
  }

  @Test
  void wordBreakTest() {
    assertTrue(dp.wordBreak("leetcode", List.of("leet", "code")));
    assertTrue(dp.wordBreak("applepenapple", List.of("apple", "pen")));
    assertFalse(dp.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
    assertFalse(dp.wordBreak("leetcode", List.of("leet", "dog", "sand", "and", "cat")));
    assertTrue(dp.wordBreak("leetcode", List.of("leetco", "ode", "ed", "d", "e")));
    assertTrue(dp.wordBreak("aaaaaaa", List.of("aaaa", "aaa")));
  }
}
