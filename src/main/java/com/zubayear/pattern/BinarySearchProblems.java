package com.zubayear.pattern;

public class BinarySearchProblems {
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

    public int searchInRotatedSorted(int[] nums, int target) {
        // find mid
        // find sorted section
        // ask if target lies within sorted section
        // change pointers based on that
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // [4,5,6,7,0,1,2]
            if (nums[left] <= nums[mid]) {
                // sorted section
                if (nums[left] <= target && target <= nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                // [4,5,6,7,0,1,2]
                // target in unsorted section i.e. [7,0,1,2]
                if (nums[mid] <= target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }

    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) return nums[0];
        // for ignoring edge cases we'll start at 1 and n-2
        int left = 1, right = nums.length - 2;
        // [1,1,2,3,3,4,4,8,8]
        // compare idx 0 & 1, for [0,1] return 0 cause as we go up we'll have 1 but can't have 0
        if (nums[0] != nums[1]) return nums[0];
        // compare last 2 idx, as we finish the array we'll not have same numbers i.e. [2,2,4]
        if (nums[nums.length-1] != nums[nums.length-2]) return nums[nums.length-1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // check for single element
            // [1,1,2,3,3,4,4,8,8]
            //    l     m     r
            if (nums[mid - 1] != nums[mid] && nums[mid + 1] != nums[mid]) return nums[mid];
            // either one is same so not single element
            // (even, odd) element (odd, even)
            // (even, odd) -> then element on the right half => eliminate left half
            // (even, odd) i.e. i'm at odd then check if prev is equal to current
            // also i'm at even then check if next is equal to current
            // then i'm at left,
            if ((mid % 2 == 1 && nums[mid - 1] == nums[mid]) || (mid % 2 == 0 && nums[mid] == nums[mid+1])) left = mid + 1;
            // (odd, even) -> then element on the left half => eliminate right half
            else right = mid - 1;
        }
        return -1;
    }
}
