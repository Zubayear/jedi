package com.zubayear.dsaj.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionProblems {
    void printName(int i, int n) {
        if (i > n - 1) return;
        System.out.println("Name");
        printName(++i, n);
    }

    void oneToN(int i, int n) {
        if (i > n) return;
        System.out.println(i);
        oneToN(i + 1, n);
    }

    void nToOne(int i, int n) {
        if (i > n) return;
        nToOne(i + 1, n);
        System.out.println(i);
    }

    void reverse(int[] arr) {
        reverse(arr, 0);
    }

    private void reverse(int[] arr, int idx) {
        // 1, 2, 3, 4
        if (idx == arr.length / 2) return;
        int tmp = arr[idx];
        arr[idx] = arr[arr.length - 1 - idx];
        arr[arr.length - 1 - idx] = tmp;
        reverse(arr, idx + 1);
    }

    int sum(int[] arr) {
        // we can just calculate easily
//        if (arr.length == 0) return 0;
        // [1, 2, 3, 4]
        // 1 + [2, 3, 4]
//        return arr[0] + sum(Arrays.copyOfRange(arr, 1, arr.length));
        return sum(arr, 0);
    }

    private int sum(int[] arr, int idx) {
        if (idx == arr.length) return 0;
        return arr[idx] + sum(arr, idx + 1);
    }

    boolean palindrome(String str) {
        if (str.length() == 0 || str.length() == 1) return true;
        return palindrome(str.toCharArray(), 0);
    }

    private boolean palindrome(char[] chars, int idx) {
        if (idx == chars.length / 2) return true;
        char first = chars[idx];
        char last = chars[chars.length - 1 - idx];
        if (first != last) return false;
        return palindrome(chars, idx + 1);
    }

    void reverseStr(char[] str) {
        if (str.length == 0 || str.length == 1) return;
        reverseStr(str, 0);
    }

    private void reverseStr(char[] chars, int idx) {
        if (idx == chars.length / 2) return;
        char tmp = chars[idx];
        chars[idx] = chars[chars.length - 1 - idx];
        chars[chars.length - 1 - idx] = tmp;
        reverseStr(chars, idx + 1);
    }

    void subsequence(int[] arr) {
        subsequence(arr, new ArrayList<>(), 0);
    }

    private void subsequence(int[] arr, List<Integer> aux, int idx) {
        if (idx == arr.length) {
            System.out.println(aux);
            return;
        }
        // take
        aux.add(arr[idx]);
        subsequence(arr, aux, idx + 1);
        // not take
        aux.remove(aux.size() - 1);
        subsequence(arr, aux, idx + 1);
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
            aux.remove(aux.size() - 1);
        }
        combinationSum(idx + 1, candidates, target, aux, result); // didn't take it so we need to move forward
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum2(candidates, target, 0, result, new ArrayList<Integer>());
        return result;
    }

    private void combinationSum2(int[] candidates, int target, int idx, List<List<Integer>> result, List<Integer> auxList) {
        if (target == 0) {
            result.add(new ArrayList<>(auxList));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {

        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void subsets(int[] nums, int idx, List<Integer> aux, List<List<Integer>> result) {
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

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetsWithDup(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void subsetsWithDup(int[] nums, int idx, List<Integer> aux, List<List<Integer>> result) {
        result.add(new ArrayList<>(aux));
        for (int i = idx; i < nums.length; ++i) {
            if (i > idx && nums[i - 1] == nums[i]) continue;
            aux.add(nums[i]);
            subsetsWithDup(nums, i + 1, aux, result);
            aux.remove(aux.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];
        permute(nums, new ArrayList<>(), freq, result);
        return result;
    }

    private void permute(int[] nums, List<Integer> aux, boolean[] map, List<List<Integer>> result) {
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
