package com.lucienvirecourt.jedi.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionProblems {
  void nameList(int i, int n, List<String> result, String name) {
    if (i == n) return;
    result.add(name);
    nameList(i + 1, n, result, name);
  }

  void oneToN(int n, List<Integer> result) {
    oneToN(1, n, result);
  }

  private void oneToN(int i, int n, List<Integer> result) {
    if (i == n + 1) return;
    result.add(i);
    oneToN(i + 1, n, result);
  }

  void nToOne(int n, List<Integer> result) {
    // one way
    /*if (n == 0) return;
    result.add(n);
    nToOne(n - 1, result);*/

    nToOne(1, n, result);
  }

  private void nToOne(int i, int n, List<Integer> result) {
    if (i == n + 1) return;
    nToOne(i + 1, n, result);
    result.add(i);
  }

  void reverse(int[] nums) {
    reverse(0, nums.length, nums);
  }

  private void reverse(int i, int n, int[] nums) {
    if (i == n / 2) return;
    int t = nums[i];
    nums[i] = nums[n - i - 1];
    nums[n - i - 1] = t;
    reverse(i + 1, n, nums);
  }

  int sum(int[] arr) {
    return sum(arr, 0);
  }

  private int sum(int[] arr, int idx) {
    if (idx == arr.length) return 0;
    return arr[idx] + sum(arr, idx + 1);
  }

  boolean palindrome(String str) {
    if (str.isEmpty() || str.length() == 1) return true;
    return palindrome(0, str.length(), str.toCharArray());
  }

  private boolean palindrome(int i, int n, char[] chars) {
    if (i == n / 2) return true;
    char first = chars[i];
    char last = chars[n - 1 - i];
    if (first != last) return false;
    return palindrome(i + 1, n, chars);
  }

  String reverseStr(String str) {
    if (str.length() == 1) return str;
    char[] chars = str.toCharArray();
    reverseStr(0, str.length(), chars);
    return new String(chars);
  }

  private void reverseStr(int i, int n, char[] chars) {
    if (i == n / 2) return;
    char tmp = chars[i];
    chars[i] = chars[n - 1 - i];
    chars[n - 1 - i] = tmp;
    reverseStr(i + 1, n, chars);
  }

  void mergeSort(int[] arr) {
    mergeSort(arr, 0, arr.length - 1);
  }

  private void mergeSort(int[] arr, int low, int high) {
    if (low == high) return;
    int mid = (low + high) / 2;
    mergeSort(arr, low, mid); // divide
    mergeSort(arr, mid + 1, high); // divide
    merge(arr, low, mid, high);
  }

  private void merge(int[] arr, int low, int mid, int high) {
    List<Integer> tmp = new ArrayList<>();
    // [1,   3, 6]      [2,    4,   5]
    // low     mid     mid+1     high
    int left = low, right = mid + 1;
    while (left <= mid && right <= high) {
      if (arr[left] <= arr[right]) {
        tmp.add(arr[left]);
        left++;
      } else {
        tmp.add(arr[right]);
        right++;
      }
    }
    // remaining in left subarray
    while (left <= mid) {
      tmp.add(arr[left]);
      left++;
    }
    // remaining int right subarray
    while (right <= high) {
      tmp.add(arr[right]);
      right++;
    }
    for (int i = low; i <= high; ++i) arr[i] = tmp.get(i - low);
  }

  List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    combinationSum(0, candidates, target, new ArrayList<>(), result);
    return result;
  }

  private void combinationSum(int idx, int[] candidates, int target, List<Integer> aux, List<List<Integer>> result) {
    if (idx == candidates.length) {
      if (target == 0) result.add(new ArrayList<>(aux));
      return;
    }
    // take the value
    if (candidates[idx] <= target) {
      aux.add(candidates[idx]);
      combinationSum(idx, candidates, target - candidates[idx], aux, result); // we stay at that idx because we have a choice to take it again
      // not take
      aux.removeLast();
    }
    combinationSum(idx + 1, candidates, target, aux, result); // didn't take it, so we need to move forward
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    combinationSum2(candidates, target, 0, result, new ArrayList<>());
    return result;
  }

  private void combinationSum2(int[] candidates, int target, int idx, List<List<Integer>> result, List<Integer> auxList) {
    if (target == 0) {
      result.add(new ArrayList<>(auxList));
      return;
    }

    for (int i = idx; i < candidates.length; i++) {
      if (i > idx && candidates[i] == candidates[i - 1]) continue;
      if (candidates[i] > target) break;
      // take
      auxList.add(candidates[i]);
      combinationSum2(candidates, target - candidates[i], i + 1, result, auxList);
      auxList.removeLast();
    }
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    subsets(nums, 0, new ArrayList<>(), result);
    return result;
  }

  private void subsets(int[] nums, int idx, List<Integer> aux, List<List<Integer>> result) {
    if (idx == nums.length) {
      result.add(new ArrayList<>(aux));
      return;
    }
    // take
    aux.add(nums[idx]);
    subsets(nums, idx + 1, aux, result); // we have a chance to take that again, so we don't move forward
    aux.removeLast();
    subsets(nums, idx + 1, aux, result); // since we've ignored it, move forward
  }

  public List<List<Integer>> subsetsWithDuplicate(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    subsetsWithDuplicate(nums, 0, new ArrayList<>(), result);
    return result;
  }

  private void subsetsWithDuplicate(int[] nums, int idx, List<Integer> aux, List<List<Integer>> result) {
    result.add(new ArrayList<>(aux));
    for (int i = idx; i < nums.length; ++i) {
      if (i > idx && nums[i - 1] == nums[i]) continue;
      aux.add(nums[i]);
      subsetsWithDuplicate(nums, i + 1, aux, result);
      aux.removeLast();
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    boolean[] freq = new boolean[nums.length];
    permute(nums, new ArrayList<>(), freq, result);
    return result;
  }

  private void permute(int[] nums, List<Integer> current, boolean[] map, List<List<Integer>> result) {
    if (current.size() == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }
    for (int i = 0; i < nums.length; ++i) {
      if (!map[i]) {
        current.add(nums[i]);
        map[i] = true;
        permute(nums, current, map, result);
        current.removeLast();
        map[i] = false;
      }
    }

  }

  public boolean wordSearch(char[][] board, String word) {
    int m = board.length, n = board[0].length;
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (dfs(board, i, j, m, n, 0, word)) return true;
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, int r, int c, int m, int n, int i, String word) {
    if (i >= word.length()) return true;
    if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] != word.charAt(i)) return false;
    boolean res = false;
    if (board[r][c] == word.charAt(i)) {
      board[r][c] += 100;
      res = dfs(board, r - 1, c, m, n, i + 1, word) ||
        dfs(board, r, c - 1, m, n, i + 1, word) ||
        dfs(board, r + 1, c, m, n, i + 1, word) ||
        dfs(board, r, c + 1, m, n, i + 1, word);
      board[r][c] -= 100;
    }
    return res;
  }

  public void solveSudoku(char[][] board) {
    solveSudoku(board, board.length, board[0].length);
  }

  private boolean solveSudoku(char[][] board, int m, int n) {
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (board[i][j] == '.') {
          for (char c = '1'; c <= '9'; ++c) {
            if (isValid(board, i, j, c)) {
              board[i][j] = c;
              if (solveSudoku(board, m, n)) return true;
              else board[i][j] = '.';
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  private boolean isValid(char[][] board, int r, int c, char ch) {
    for (int i = 0; i < 9; ++i) {
      if (board[i][c] == ch) return false;
      if (board[r][i] == ch) return false;
      if (board[3 * (r / 3) + i / 3][3 * (c / 3) + i % 3] == ch) return false;
    }
    return true;
  }

  public List<List<String>> partitionPalindrome(String s) {
    List<List<String>> result = new ArrayList<>();
    partitionPalindrome(0, s.length(), s, result, new ArrayList<>());
    return result;
  }

  private void partitionPalindrome(int idx, int n, String s, List<List<String>> result, List<String> current) {
    if (idx == n) {
      result.add(new ArrayList<>(current));
      return;
    }
    for (int i = idx; i < n; ++i) {
      if (isPalindrome(s, idx, i)) {
        current.add(s.substring(idx, i + 1));
        partitionPalindrome(i, n, s, result, current);
        current.removeLast();
      }
    }
  }

  private boolean isPalindrome(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i++) != s.charAt(j--)) return false;
    }
    return true;
  }
}
