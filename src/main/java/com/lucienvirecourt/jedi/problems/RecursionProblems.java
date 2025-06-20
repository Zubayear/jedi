package com.lucienvirecourt.jedi.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionProblems {
  static void nameList(int i, int n, List<String> result, String name) {
    if (i == n) return;
    result.add(name);
    nameList(i + 1, n, result, name);
  }

  static void oneToN(int n, List<Integer> result) {
    oneToN(1, n, result);
  }

  private static void oneToN(int i, int n, List<Integer> result) {
    if (i == n + 1) return;
    result.add(i);
    oneToN(i + 1, n, result);
  }

  static void nToOne(int n, List<Integer> result) {
    // one way
    /*if (n == 0) return;
    result.add(n);
    nToOne(n - 1, result);*/

    nToOne(1, n, result);
  }

  private static void nToOne(int i, int n, List<Integer> result) {
    if (i == n + 1) return;
    nToOne(i + 1, n, result);
    result.add(i);
  }

  static void reverse(int[] nums) {
    reverse(0, nums.length, nums);
  }

  private static void reverse(int i, int n, int[] nums) {
    if (i == n / 2) return;
    int t = nums[i];
    nums[i] = nums[n - i - 1];
    nums[n - i - 1] = t;
    reverse(i + 1, n, nums);
  }

  static int sum(int[] arr) {
    return sum(arr, 0);
  }

  private static int sum(int[] arr, int idx) {
    if (idx == arr.length) return 0;
    return arr[idx] + sum(arr, idx + 1);
  }

  static boolean palindrome(String str) {
    if (str.isEmpty() || str.length() == 1) return true;
    return palindrome(0, str.length(), str.toCharArray());
  }

  private static boolean palindrome(int i, int n, char[] chars) {
    if (i == n / 2) return true;
    char first = chars[i];
    char last = chars[n - 1 - i];
    if (first != last) return false;
    return palindrome(i + 1, n, chars);
  }

  static String reverseStr(String str) {
    if (str.length() == 1) return str;
    char[] chars = str.toCharArray();
    reverseStr(0, str.length(), chars);
    return new String(chars);
  }

  private static void reverseStr(int i, int n, char[] chars) {
    if (i == n / 2) return;
    char tmp = chars[i];
    chars[i] = chars[n - 1 - i];
    chars[n - 1 - i] = tmp;
    reverseStr(i + 1, n, chars);
  }

  static List<List<Integer>> subsequence(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<>());
    subsequence(0, nums.length, nums, result, new ArrayList<>());
    return result;
  }

  private static void subsequence(int i, int n, int[] nums, List<List<Integer>> result, List<Integer> temp) {
    if (i == n) {
      return;
    }
    // take
    temp.add(nums[i]);
    result.add(new ArrayList<>(temp));
    subsequence(i + 1, n, nums, result, temp);
    // not take
    temp.removeLast();
    subsequence(i + 1, n, nums, result, temp);
  }

  static void mergeSort(int[] arr) {
    mergeSort(arr, 0, arr.length - 1);
  }

  private static void mergeSort(int[] arr, int low, int high) {
    if (low == high) return;
    int mid = (low + high) / 2;
    mergeSort(arr, low, mid); // divide
    mergeSort(arr, mid + 1, high); // divide
    merge(arr, low, mid, high);
  }

  private static void merge(int[] arr, int low, int mid, int high) {
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
    // remaining in left sub-array
    while (left <= mid) {
      tmp.add(arr[left]);
      left++;
    }
    // remaining int right sub-array
    while (right <= high) {
      tmp.add(arr[right]);
      right++;
    }
    for (int i = low; i <= high; ++i) arr[i] = tmp.get(i - low);
  }

  static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    combinationSum(0, candidates, target, new ArrayList<>(), result);
    return result;
  }

  private static void combinationSum(int idx, int[] candidates, int target, List<Integer> aux, List<List<Integer>> result) {
    if (idx == candidates.length) {
      if (target == 0) result.add(new ArrayList<>(aux));
      return;
    }
    // take the value
    if (candidates[idx] <= target) {
      aux.add(candidates[idx]);
      combinationSum(idx, candidates, target - candidates[idx], aux, result); // we stay at that idx because we have a choice to take it again
      // not take
      aux.remove(aux.size() - 1);
    }
    combinationSum(idx + 1, candidates, target, aux, result); // didn't take it so we need to move forward
  }

  static public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    combinationSum2(candidates, target, 0, result, new ArrayList<Integer>());
    return result;
  }

  private static void combinationSum2(int[] candidates, int target, int idx, List<List<Integer>> result, List<Integer> auxList) {
    if (target == 0) {
      result.add(new ArrayList<>(auxList));
      return;
    }
    for (int i = idx; i < candidates.length; i++) {

    }
  }

  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    subsets(nums, 0, new ArrayList<>(), result);
    return result;
  }

  private static void subsets(int[] nums, int idx, List<Integer> aux, List<List<Integer>> result) {
    if (idx >= nums.length) {
      result.add(new ArrayList<>(aux));
//            Integer sum = aux.stream().reduce(0, Integer::sum);
//            System.out.print(sum);
      return;
    }
    // take
    aux.add(nums[idx]);
    subsets(nums, idx + 1, aux, result);
    // not take
    // even if i'm not taking i'm not gonna stay there
    // i need to move because i can't take same elem multiple times
    aux.remove(aux.size() - 1);
    subsets(nums, idx + 1, aux, result);
  }

  public static List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    subsetsWithDup(nums, 0, new ArrayList<>(), result);
    return result;
  }

  private static void subsetsWithDup(int[] nums, int idx, List<Integer> aux, List<List<Integer>> result) {
    result.add(new ArrayList<>(aux));
    for (int i = idx; i < nums.length; ++i) {
      if (i > idx && nums[i - 1] == nums[i]) continue;
      aux.add(nums[i]);
      subsetsWithDup(nums, i + 1, aux, result);
      aux.remove(aux.size() - 1);
    }
  }

  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    boolean[] freq = new boolean[nums.length];
    permute(nums, new ArrayList<>(), freq, result);
    return result;
  }

  private static void permute(int[] nums, List<Integer> aux, boolean[] map, List<List<Integer>> result) {
    if (aux.size() == nums.length) {
      result.add(new ArrayList<>(aux));
      return;
    }
    for (int i = 0; i < nums.length; ++i) {
      if (!map[i]) {
        aux.add(nums[i]);
        map[i] = true;
        permute(nums, aux, map, result);
        aux.remove(aux.size() - 1);
        map[i] = false;
      }
    }

  }

}
