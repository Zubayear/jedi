package com.lucienvirecourt.jedi.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums.length < 2) {
      return result;
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int j = i + 1, k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum < 0) {
          j++;
        } else if (sum > 0) {
          k--;
        } else {
          result.add(Arrays.asList(nums[i], nums[j], nums[k]));
          // move j and k til we find different element
          while (j < k && nums[j] != nums[j - 1]) {
            j++;
          }
          while (j < k && nums[k] != nums[k - 1]) {
            k--;
          }
        }
      }
    }
    return result;
  }
}
