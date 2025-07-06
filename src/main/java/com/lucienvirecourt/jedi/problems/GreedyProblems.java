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
}
