package com.lucienvirecourt.jedi.problems;

public class BinarySearchProblems {

  public static int lowerBound(int[] nums, int k) {
    // smallest index such that nums[idx] >= k
    int n = nums.length, left = 0, right = n - 1, res = n;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= k) {
        res = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return res;
  }

  public static int upperBound(int[] nums, int k) {
    // smallest index such that nums[idx] > k
    int n = nums.length, left = 0, right = n - 1, res = n;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > k) {
        res = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return res;
  }

  public static int floor(int[] nums, int k) {
    // largest number in nums <= k
    // nums = [10,20,30,40,50] k = 25
    int n = nums.length, left = 0, right = n, ans = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] <= k) {
        ans = nums[mid];
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return ans;
  }

  public static int ceil(int[] nums, int k) {
    // smallest number in nums >= k
    // nums = [10,20,30,40,50] k = 25 ans = 30
    int n = nums.length, left = 0, right = n, ans = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < k) {
        left = mid + 1;
      } else {
        ans = nums[mid];
        right = mid - 1;
      }
    }
    return ans;
  }

  public static int[] firstAndLastOccurrence(int[] nums, int k) {
    // lower bound and upper bound
    int lb = lowerBound(nums, k);
    if (lb == nums.length || nums[lb] != k) return new int[]{-1, -1};
    int up = upperBound(nums, k);
    return new int[]{lb, up - 1};
  }

  public int rotationTime(int[] nums) {
    // find the index of min value
    // find sorted section and get min and then remove sorted section
    int left = 0, right = nums.length - 1, minVal = Integer.MAX_VALUE, result = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      // [4,5,6,7,0,1,2] if mid is at 0 then we can see that left and right both sorted
      // i.e. [0,1] & [1,2] so we have the entire array sorted, just pick the lowest
      if (nums[left] <= nums[right]) {
//                minVal = Math.min(minVal, nums[left]);
        if (minVal <= nums[left]) result = right;
        else result = left;
        break;
      }
      if (nums[left] <= nums[mid]) { // sorted section
        if (minVal <= nums[left]) result = mid;
        else result = left;
//                minVal = Math.min(minVal, nums[left]);
        left = mid + 1;
      } else {
//                minVal = Math.min(minVal, nums[mid]);
        if (minVal <= nums[mid]) result = mid;
        else result = mid;
        right = mid - 1;
      }
    }
    return result;
  }

  public static int searchInRotatedSortedArray(int[] nums, int target) {
    // find middle
    // find sorted section
    // ask if target lies within sorted section
    // change pointers based on that
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) return mid;
      // [4,5,6,7,0,1,2]
      if (nums[left] <= nums[mid]) {
        if (nums[left] <= target && target <= nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if (nums[mid] <= target && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return -1;
  }

  public static boolean searchInRotatedSortedArrayWithDuplicates(int[] nums, int target) {
    int n = nums.length;
    int left = 0, right = n-1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) return true;
      // 3, 1, 2, 3, 3, 3, 3
      // we cannot say which portion is sorted since both left and mid are equal, also mid and right are equal too,
      // so in this case we shrink the space
      if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
        left++;
        right--;
        continue;
      }

      if (nums[left] <= nums[mid]) {
        if (nums[left] <= target && target <= nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        if (nums[mid] <= target && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return false;
  }

  public int singleNonDuplicate(int[] nums) {
    if (nums.length == 1) return nums[0];
    // for ignoring edge cases, we'll start at 1 and n-2
    int left = 1, right = nums.length - 2;
    // [1,1,2,3,3,4,4,8,8]
    // compares idx 0 & 1, for [0,1] return 0 cause as we go up we'll have 1 but can't have 0
    if (nums[0] != nums[1]) return nums[0];
    // compare last 2 idx, as we finish the array, we'll not have the same numbers i.e. [2,2,4]
    if (nums[nums.length - 1] != nums[nums.length - 2]) return nums[nums.length - 1];
    while (left <= right) {
      int mid = left + (right - left) / 2;
      // check for single element
      // [1,1,2,3,3,4,4,8,8]
      //    l     m     r
      if (nums[mid - 1] != nums[mid] && nums[mid + 1] != nums[mid]) return nums[mid];
      // either one is same so not single element
      // (even, odd) element (odd, even)
      // (even, odd) -> then an element on the right half => eliminates left half
      // (even, odd) i.e., I'm at odd then check if prev is equal to current
      // also I'm at even then check if next is equal to current
      // then I'm at left,
      if ((mid % 2 == 1 && nums[mid - 1] == nums[mid]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) left = mid + 1;
        // (odd, even) -> then element on the left half => eliminates right half
      else right = mid - 1;
    }
    return -1;
  }
}
