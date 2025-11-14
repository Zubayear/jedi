package com.lucienvirecourt.jedi.problems;

import java.util.Arrays;

public class GreedyProblems {

  public boolean canAttendMeetings(int[][] meetings) {
    Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
    int count = 1, end = meetings[0][1], n = meetings.length;
    for (int i = 1; i < n; ++i) {
      int start = meetings[i][0];
      if (start > end) {
        count++;
        end = meetings[i][1];
      }
    }
    return count == meetings.length;
  }

  public int meetingCount(int[][] meetings) {
    Arrays.sort(meetings, (a, b) -> a[1] - b[1]);
    int count = 1, end = meetings[0][1], n = meetings.length;
    for (int i = 1; i < n; ++i) {
      int start = meetings[i][0];
      if (start > end) {
        count++;
        end = meetings[i][1];
      }
    }
    return count;
  }

  public int eraseOverlapIntervals(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
    int count = 1, end = intervals[0][1], n = intervals.length;
    for (int i = 1; i < n; ++i) {
      int start = intervals[i][0];
      if (start >= end) {
        count++;
        end = intervals[i][1];
      }
    }
    return n - count;
  }

  public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);

    // [7,8,9,10] [5,6,7,8,8,11]
    // we take 2 pointers and with j pointer try to cover i
    int i = 0, j = 0, m = g.length, n = s.length, ans = 0;
    while (i < m && j < n) {
      if (s[j] >= g[i]) {
        ans++;
        i++;
      }
      j++;
    }
    return ans;
  }

  public boolean lemonadeChange(int[] bills) {
    int five = 0, ten = 0;
    // if it's $5 bill then we can keep it
    // if it's $10 bill then we can keep $5 and give back $5
    // if it's $20 bill then we can keep $5 and give back 3 $5 bill or 1 $10 + $5 bill
    for (int bill : bills) {
      if (bill == 5) five++;
      else if (bill == 10) {
        if (five > 0) {
          five--;
          ten++;
        } else {
          return false;
        }
      } else {
        if (ten > 0 && five > 0) {
          // we give back one $5 bill & one $10 bill
          five--;
          ten--;
        } else if (five >= 3) {
          five -= 3;
        } else {
          return false;
        }
      }
    }
    return true;
  }

  public int maxSubArray(int[] nums) {
    // we will keep a running sum and the find the max so far
    // if the sum is negative it doesn't contribute anything
    // so we will reset the sum essentially staring a new sum
    // -2,1,-3,4,-1,2,1,-5,4
    int sum = 0, max = -(int) 1e9;
    for (int n : nums) {
      sum += n;
      max = Math.max(max, sum);
      if (sum < 0) sum = 0;
    }
    return max;
  }

  public boolean canJump(int[] nums) {
    // we keep a max index we can reach, we will update it everytime
    // we check if we have cross the maxIdx, if so then that case isn't valid
    int maxIdx = 0, n = nums.length;
    for (int i = 0; i < n; ++i) {
      if (maxIdx < i) return false;
      if (maxIdx == n) return true;
      maxIdx = Math.max(maxIdx, nums[i] + i);
    }
    return true;
  }
}
