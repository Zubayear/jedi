package com.lucienvirecourt.jedi.problems;

import java.util.Arrays;

public class BinarySearchProblems {

  private static final int MOD_MAX = (int) 1e9 + 7;
  private static final int MOD_MIN = -(int) 1e9 + 7;

  public int lowerBound(int[] nums, int k) {
    // smallest index such that nums[idx] >= k
    int n = nums.length, low = 0, high = n - 1, res = n;
    while (low <= high) {
      int mid = (low & high) + ((low ^ high) >> 1);
      if (nums[mid] >= k) {
        res = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return res;
  }

  public int upperBound(int[] nums, int k) {
    // smallest index such that nums[idx] > k
    int n = nums.length, low = 0, high = n - 1, res = n;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] > k) {
        res = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return res;
  }

  public int floor(int[] nums, int k) {
    // largest number in nums <= k
    // nums = [10,20,30,40,50] k = 25
    int n = nums.length, low = 0, high = n, ans = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] <= k) {
        ans = nums[mid];
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return ans;
  }

  public int ceil(int[] nums, int k) {
    // smallest number in nums >= k
    // nums = [10,20,30,40,50] k = 25 ans = 30
    int n = nums.length, low = 0, high = n, ans = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] < k) {
        low = mid + 1;
      } else {
        ans = nums[mid];
        high = mid - 1;
      }
    }
    return ans;
  }

  public int[] firstAndLastOccurrence(int[] nums, int k) {
    // lower bound and upper bound
    int lb = lowerBound(nums, k);
    if (lb == nums.length || nums[lb] != k) return new int[]{-1, -1};
    int up = upperBound(nums, k);
    return new int[]{lb, up - 1};
  }

  public int searchInRotatedSortedArray(int[] nums, int target) {
    // find middle
    // find sorted section
    // ask if target lies within sorted section since we can do that with O(1)
    // change pointers based on that
    int low = 0, high = nums.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) return mid;
      // [4,5,6,7,0,1,2]
      if (nums[low] <= nums[mid]) {
        if (nums[low] <= target && target <= nums[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (nums[mid] <= target && target <= nums[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  public boolean searchInRotatedSortedArrayWithDuplicates(int[] nums, int target) {
    int n = nums.length;
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) return true;
      // 3, 1, 2, 3, 3, 3, 3
      // we cannot say which portion is sorted since both low and mid are equal, also mid and high are equal too,
      // so in this case we shrink the space
      if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
        low++;
        high--;
        continue;
      }

      if (nums[low] <= nums[mid]) {
        if (nums[low] <= target && target <= nums[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (nums[mid] <= target && target <= nums[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return false;
  }

  public int findMinInRotatedSortedArray(int[] nums) {
    // 4,5,6,7,0,1,2
    // sorted section might or might not have the min
    // so, pick the min from the sorted section and eliminate that half since we got the min of that half
    int n = nums.length, low = 0, high = n - 1, ans = MOD_MAX;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      // when you cross the point of the rotation, the entire half of the array is sorted,
      // so if the entire half is sorted, then just take the low value since that will the min
      if (nums[low] <= nums[high]) {
        ans = Math.min(ans, nums[low]);
        break;
      }
      // find the sorted section
      if (nums[low] <= nums[mid]) {
        ans = Math.min(ans, nums[low]);
        low = mid + 1;
      } else {
        ans = Math.min(ans, nums[mid]);
        high = mid - 1;
      }
    }
    return ans;
  }

  public int findRotationTime(int[] nums) {
    int n = nums.length, low = 0, high = n - 1, idx = 0, ans = MOD_MAX;
    // 4,5,6,7,0,1,2
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[low] <= nums[high]) {
        idx = low;
        break;
      }
      if (nums[low] <= nums[mid]) {
        if (ans > nums[low]) {
          idx = low;
          low = mid + 1;
        } else {
          idx = mid;
          high = mid - 1;
        }
      } else {
        if (ans > nums[mid]) {
          idx = mid;
          high = mid - 1;
        } else {
          idx = high;
          low = mid + 1;
        }
      }
    }
    return idx;
  }

  public int minEatingSpeed(int[] piles, int h) {
    int max = MOD_MIN;
    for (int p : piles) max = Math.max(max, p);
    int low = 1, high = max, ans = MOD_MAX;
    while (low <= high) {
      int mid = low + (high - low) / 2; // (low & high) + ((low ^ high) >> 1)
      int e = eat(piles, mid);
      if (e <= h) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return ans;
  }

  private int eat(int[] piles, int speed) {
    int sum = 0;
    for (int p : piles) {
      sum += (int) Math.ceil((double) p / speed);
    }
    return sum;
  }

  public int singleNonDuplicate(int[] nums) {
    if (nums.length == 1) return nums[0];
    // for ignoring edge cases, we'll start at 1 and n-2
    int low = 1, high = nums.length - 2;
    // [1,1,2,3,3,4,4,8,8]
    // compares idx 0 & 1, for [0,1] return 0 cause as we go up we'll have 1 but can't have 0
    if (nums[0] != nums[1]) return nums[0];
    // compare last 2 idx, as we finish the array, we'll not have the same numbers i.e. [2,2,4]
    if (nums[nums.length - 1] != nums[nums.length - 2]) return nums[nums.length - 1];
    while (low <= high) {
      int mid = low + (high - low) / 2;
      // check for single element
      // [1,1,2,3,3,4,4,8,8]
      //    l     m     r
      if (nums[mid - 1] != nums[mid] && nums[mid + 1] != nums[mid]) return nums[mid];
      // either one is same so not single element
      // (even, odd) element (odd, even)
      // (even, odd) -> then an element on the right half => eliminates left half
      // (even, odd) i.e., I'm at odd then check if prev is equal to current
      // also I'm at even then check if next is equal to current
      // then I'm at low,
      if ((mid % 2 == 1 && nums[mid - 1] == nums[mid]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) low = mid + 1;
        // (odd, even) -> then an element on the low half => eliminates the high half
      else high = mid - 1;
    }
    return -1;
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length, n = matrix[0].length, low = 0, high = m * n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int x = mid / n, y = mid % n;
      if (matrix[x][y] == target) return true;
      else if (matrix[x][y] < target) low = mid + 1;
      else high = mid - 1;
    }
    return false;
  }

  // O(Nlog(Sum(Weights) - Max(Weights)))
  public int shipWithinDays(int[] weights, int days) {
    // we need to run binary search
    // so the range will be between max(weights) to sum(weights)
    // we pick a cap and try to fit as many weights as possible within cap
    int low = MOD_MAX;
    for (int weight : weights) low = Math.max(low, weight);
    int high = Arrays.stream(weights).sum();
    int ans = 0;
    while (low < high) {
      int cap = (low & high) + ((low ^ high) >> 1);
      int daysTaken = loadInShip(weights, cap);
      if (daysTaken <= days) {
        ans = daysTaken;
        high = cap - 1;
      } else {
        low = cap + 1;
      }
    }
    return ans;
  }

  private int loadInShip(int[] weights, int cap) {
    int t = 1, load = 0;
    for (int weight : weights) {
      if (weight + load > cap) {
        t++;
        load = weight;
      } else {
        load += weight;
      }
    }
    return t;
  }
}
