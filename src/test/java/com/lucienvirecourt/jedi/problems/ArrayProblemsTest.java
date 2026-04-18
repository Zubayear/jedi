package com.lucienvirecourt.jedi.problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ArrayProblemsTest {

  ArrayProblems ap;

  @BeforeEach
  void setup() {
    ap = new ArrayProblems();
  }

  @AfterEach
  void teardown() {
    ap = null;
  }

  @Test
  void secondMaxTest() {
    assertEquals(5, ap.secondMax(new int[]{1, 2, 4, 7, 7, 5}));
  }

  @Test
  void removeDuplicatesTest() {
    assertEquals(3, ap.removeDuplicates(new int[]{1, 1, 2, 2, 2, 3, 3}));
    assertEquals(5, ap.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
  }

  @Test
  void productExceptSelfTest() {
    int[] nums = {1, 2, 3, 4};
    assertArrayEquals(new int[]{24, 12, 8, 6}, ap.productExceptSelf(nums));
    assertArrayEquals(new int[]{0, 0, 9, 0, 0}, ap.productExceptSelf(new int[]{-1, 1, 0, -3, 3}));
  }

  @Test
  void firstDuplicateValueTest() {
    assertEquals(3, ap.firstDuplicateValue(new int[]{2, 1, 5, 3, 3, 2, 4}));
  }

  @Test
  void isValidSudokuTest() {
    assertTrue(ap.isValidSudoku(new char[][]{
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    }));
  }

  @Test
  void numRescueBoatsTest() {
    assertEquals(1, ap.numRescueBoats(new int[]{1, 2}, 3));
    assertEquals(3, ap.numRescueBoats(new int[]{3, 2, 2, 1}, 3));
    assertEquals(4, ap.numRescueBoats(new int[]{3, 5, 3, 4}, 5));
  }

  @Test
  void nextGreaterElementTest() {
    assertArrayEquals(new int[]{-1, 3, -1}, ap.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
    assertArrayEquals(new int[]{3, -1}, ap.nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
    assertArrayEquals(new int[]{9, 4, 10, 20}, ap.nextGreaterElement(new int[]{1, 2, 3, 9}, new int[]{2, 4, 3, 10, 1, 9, 20, 22}));
  }

  @Test
  void appendCharactersTest() {
    assertEquals(4, ap.appendCharacters("coaching", "coding"));
    assertEquals(0, ap.appendCharacters("abcde", "a"));
    assertEquals(5, ap.appendCharacters("z", "abcde"));
    assertEquals(0, ap.appendCharacters("z", "z"));
  }

  @Test
  void vowelStringsTest() {
    assertArrayEquals(new int[]{2, 3, 0}, ap.vowelStrings(new String[]{"aba", "bcb", "ece", "aa", "e"}, new int[][]{
      {0, 2},
      {1, 4},
      {1, 1}
    }));
  }

  @Test
  void replaceElements() {
    assertArrayEquals(new int[]{18, 6, 6, 6, 1, -1}, ap.replaceElements(new int[]{17, 18, 5, 4, 6, 1}));
    assertArrayEquals(new int[]{-1}, ap.replaceElements(new int[]{10}));
  }

  @Test
  void longestCommonPrefix() {
    assertEquals("fl", ap.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    assertEquals("", ap.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
  }

  @Test
  void groupAnagrams() {
    assertEquals(
      List.of("ate,eat,tea", "bat", "nat,tan"),
      normalizeGroups(ap.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}))
    );
  }

  @Test
  void wordPattern() {
    assertTrue(ap.wordPattern("abba", "dog cat cat dog"));
    assertFalse(ap.wordPattern("abba", "dog cat cat fish"));
  }

  @Test
  void topKFrequent() {
    int[] result = ap.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    Arrays.sort(result);
    assertArrayEquals(new int[]{1, 2}, result);
  }

  @Test
  void secondMax() {
    assertEquals(5, ap.secondMax(new int[]{1, 2, 4, 7, 7, 5}));
  }

  @Test
  void removeDuplicates() {
    int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    assertEquals(5, ap.removeDuplicates(nums));
    assertArrayEquals(new int[]{0, 1, 2, 3, 4}, Arrays.copyOf(nums, 5));
  }

  @Test
  void longestSubarrayWithSumK() {
    assertEquals(4, ap.longestSubarrayWithSumK(new int[]{10, 5, 2, 7, 1, 9}, 15));
    assertEquals(4, ap.longestSubarrayWithSumK(new int[]{1, -1, 5, -2, 3}, 3));
  }

  @Test
  void isValidSubsequence() {
    assertTrue(ap.isValidSubsequence(new int[]{5, 1, 22, 25, 6, 2, 8, 10}, new int[]{1, 6, 10}));
    assertFalse(ap.isValidSubsequence(new int[]{5, 1, 22, 25, 6, 2, 8, 10}, new int[]{1, 6, 7, 10}));
  }

  @Test
  void smallestDifference() {
    assertEquals(2, ap.smallestDifference(
      new int[]{-1, 5, 10, 20, 28, 3},
      new int[]{26, 134, 135, 15, 17}
    ));
  }

  @Test
  void isMonotonic() {
    assertTrue(ap.isMonotonic(new int[]{1, 2, 2, 3}));
    assertTrue(ap.isMonotonic(new int[]{5, 4, 4, 2}));
    assertFalse(ap.isMonotonic(new int[]{1, 3, 2}));
  }

  @Test
  void productExceptSelf() {
    int[] nums = {1, 2, 3, 4};
    assertArrayEquals(new int[]{24, 12, 8, 6}, ap.productExceptSelf(nums));
  }

  @Test
  void firstDuplicateValue() {
    assertEquals(3, ap.firstDuplicateValue(new int[]{2, 1, 5, 3, 3, 2, 4}));
    assertEquals(0, ap.firstDuplicateValue(new int[]{1, 2, 3, 4}));
  }

  @Test
  void isValidSudoku() {
    assertTrue(ap.isValidSudoku(new char[][]{
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    }));
  }

  @Test
  void numRescueBoats() {
    assertEquals(1, ap.numRescueBoats(new int[]{1, 2}, 3));
    assertEquals(3, ap.numRescueBoats(new int[]{3, 2, 2, 1}, 3));
  }

  @Test
  void maxDifference() {
    assertEquals(1, ap.maxDifference("aabbb"));
  }

  @Test
  void nextGreaterElement() {
    assertArrayEquals(new int[]{-1, 3, -1}, ap.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
  }

  @Test
  void appendCharacters() {
    assertEquals(4, ap.appendCharacters("coaching", "coding"));
  }

  @Test
  void vowelStrings() {
    assertArrayEquals(new int[]{2, 3, 0}, ap.vowelStrings(new String[]{"aba", "bcb", "ece", "aa", "e"}, new int[][]{
      {0, 2},
      {1, 4},
      {1, 1}
    }));
  }

  @Test
  void mergeAlternately() {
    assertEquals("apbqcr", ap.mergeAlternately("abc", "pqr"));
    assertEquals("apbqrs", ap.mergeAlternately("ab", "pqrs"));
  }

  @Test
  void firstPalindrome() {
    assertEquals("ada", ap.firstPalindrome(new String[]{"abc", "car", "ada", "racecar", "cool"}));
    assertEquals("", ap.firstPalindrome(new String[]{"abc", "def"}));
  }

  @Test
  void addSpaces() {
    assertEquals("Leetcode Helps Me Learn", ap.addSpaces("LeetcodeHelpsMeLearn", new int[]{8, 13, 15}));
    assertEquals("i code in py thon", ap.addSpaces("icodeinpython", new int[]{1, 5, 7, 9}));
    assertEquals(" s p a c i n g", ap.addSpaces("spacing", new int[]{0, 1, 2, 3, 4, 5, 6}));
  }

  @Test
  void sortArrayByParity() {
    int[] nums = ap.sortArrayByParity(new int[]{3, 1, 2, 4});
    assertParityPartition(nums);
  }

  @Test
  void isSubsequence() {
    assertTrue(ap.isSubsequence("abc", "ahbgdc"));
    assertFalse(ap.isSubsequence("axc", "ahbgdc"));
  }

  private static List<String> normalizeGroups(List<List<String>> groups) {
    return groups.stream()
      .map(group -> group.stream().sorted().collect(Collectors.joining(",")))
      .sorted()
      .toList();
  }

  private static void assertParityPartition(int[] nums) {
    int i = 0;
    while (i < nums.length && nums[i] % 2 == 0) {
      i++;
    }
    while (i < nums.length) {
      assertTrue(nums[i] % 2 != 0);
      i++;
    }
  }
}
