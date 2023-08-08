package com.zubayear.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursionSolution {
    void printName(int i, int n) {
        if (i > n-1) return;
        System.out.println("Name");
        printName(++i, n);
    }

    void oneToN(int i, int n) {
        if (i > n) return;
        System.out.println(i);
        oneToN(i+1, n);
    }

    void nToOne(int i, int n) {
        if (i > n) return;
        nToOne(i+1, n);
        System.out.println(i);
    }

    void reverse(int[] arr) {
        reverse(arr, 0);
    }

    private void reverse(int[] arr, int idx) {
        // 1, 2, 3, 4
        if (idx == arr.length/2) return;
        int tmp = arr[idx];
        arr[idx] = arr[arr.length - 1 - idx];
        arr[arr.length - 1 - idx] = tmp;
        reverse(arr, idx+1);
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
        return arr[idx] + sum(arr, idx+1);
    }

    boolean palindrome(String str) {
        if (str.length() == 0 || str.length() == 1) return true;
        return palindrome(str.toCharArray(), 0);
    }

    private boolean palindrome(char[] chars, int idx) {
        if (idx == chars.length/2) return true;
        char first = chars[idx];
        char last = chars[chars.length-1-idx];
        if (first != last) return false;
        return palindrome(chars, idx+1);
    }

    void reverseStr(char[] str) {
        if (str.length == 0 || str.length == 1) return;
        reverseStr(str, 0);
    }

    private void reverseStr(char[] chars, int idx) {
        if (idx == chars.length/2) return;
        char tmp = chars[idx];
        chars[idx] = chars[chars.length-1-idx];
        chars[chars.length-1-idx] = tmp;
        reverseStr(chars, idx+1);
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
        subsequence(arr, aux, idx+1);
        // not take
        aux.remove(aux.size()-1);
        subsequence(arr, aux, idx+1);
    }

    void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length-1);
    }

    private void mergeSort(int[] arr, int low, int high) {
        if (low == high) return;
        int mid = (low+high)/2;
        mergeSort(arr, low, mid); // divide
        mergeSort(arr, mid+1, high); // divide
        merge(arr, low, mid, high);
    }

    private void merge(int[] arr, int low, int mid, int high) {
        List<Integer> tmp = new ArrayList<>();
        // [1,   3, 6]      [2,    4,   5]
        // low     mid     mid+1     high
        int left = low, right = mid+1;
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
        combinationSum(idx+1, candidates, target, aux, result); // didn't take it so we need to move forward
    }
}
