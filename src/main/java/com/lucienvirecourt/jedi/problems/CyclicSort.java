package com.lucienvirecourt.jedi.problems;

import java.util.Arrays;

public class CyclicSort {

  public void cyclicSort(int[] numbers) {
    int i = 0;
    while (i < numbers.length) {
      // 5,4,1,3,2
      // 5 should be at index 4
      int shouldBeAt = numbers[i] - 1;
      // if 5 is not at index 4
      if (numbers[shouldBeAt] != numbers[i]) {
        numbers[shouldBeAt] = numbers[shouldBeAt] ^ numbers[i];
        numbers[i] = numbers[shouldBeAt] ^ numbers[i];
        numbers[shouldBeAt] = numbers[shouldBeAt] ^ numbers[i];
      } else {
        i++;
      }
    }
  }

  public int missingNumber(int[] nums) {
    int n = nums.length + 1, start = 0;
    while (start < n) {
      int idx = nums[start];
      if (nums[start] < n && nums[idx] != nums[start]) {
        int tmp = nums[start];
        nums[start] = nums[idx];
        nums[idx] = tmp;
      } else start++;
    }
    System.out.println(Arrays.toString(nums));
    for (int i = 0; i < n; ++i) {
      if (i != nums[i]) return i;
    }
    return n;
  }
}
