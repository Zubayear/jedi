package com.lucienvirecourt.jedi.problems;

import java.util.*;

public class HeapProblems {

  public int findKthLargest(int[] nums, int k) {
    // since we need to find largest, we will create a min heap
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int n : nums) {
      minHeap.offer(n);
      if (minHeap.size() > k) minHeap.poll();
    }
    return !minHeap.isEmpty() ? minHeap.poll() : 0;
  }

  public String frequencySort(String s) {
    int[] freq = new int[62];
    for (char ch : s.toCharArray()) {
      if (Character.isLowerCase(ch)) freq[ch - 'a']++;
      else if (Character.isUpperCase(ch)) freq[ch - 'A' + 26]++;
      else freq[ch - '0' + 52]++;
    }
    // [char,freq]
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    for (int i = 0; i < 62; ++i) {
      if (freq[i] > 0) minHeap.offer(new int[]{i, freq[i]});
    }

    StringBuilder result = new StringBuilder();
    while (!minHeap.isEmpty()) {
      int[] current = minHeap.poll(); // [char,freq]
      int ch = current[0], fr = current[1];
      char append;
      if (ch < 26) append = (char) ('a' + ch);
      else if (ch < 52) append = (char) ('A' + (ch - 26));
      else append = (char) ('0' + (ch - 52));
      result.append(String.valueOf(append).repeat(Math.max(0, fr)));
    }

    return result.toString();
  }

  public int[] getOrder(int[][] tasks) {
    // modify the tasks to have index too
    int n = tasks.length;
    int[] result = new int[n];
    int[][] modifiedTask = new int[n][3];
    for (int i = 0; i < n; ++i) {
      modifiedTask[i][0] = tasks[i][0];
      modifiedTask[i][1] = tasks[i][1];
      modifiedTask[i][2] = i;
    }
    Arrays.sort(modifiedTask, Comparator.comparingInt(a -> a[0]));
    // keep (duration,index) in heap
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    int current = 0;
    int pos = 0;
    int pointer = 0;
    while (pointer < n || !minHeap.isEmpty()) {

      // [[1, 1, 5], [5, 2, 0], [5, 10, 4], [6, 3, 3], [7, 2, 1], [9, 4, 2]]
      // after the first poll current will be 2
      // if we didn't have this, then no other task would be queued since nothing is <= 2
      // so, we need to update current to the next starting point as in to index 1 i.e., with 5
      if (minHeap.isEmpty() && current < modifiedTask[pointer][0]) {
        current = modifiedTask[pointer][0];
      }

      // we will get those tasks which start before current i.e., modifiedTasks[0] <= current
      while (pointer < n && modifiedTask[pointer][0] <= current) {
        minHeap.offer(new int[]{modifiedTask[pointer][1], modifiedTask[pointer][2]});
        pointer++;
      }
      // poll the top and update the current so that we can next task that need to be executed
      if (!minHeap.isEmpty()) {
        int[] top = minHeap.poll();
        current += top[0];
        result[pos++] = top[1];
      }
    }
    return result;
  }

  public int[][] kClosest(int[][] points, int k) {
    int[][] result = new int[k][2];
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    for (int[] point : points) {
      maxHeap.offer(new int[]{point[0] * point[0] + point[1] * point[1], point[0], point[1]});
      if (maxHeap.size() > k) {
        maxHeap.poll();
      }
    }
    int i = 0;
    while (!maxHeap.isEmpty()) {
      int[] top = maxHeap.poll();
      result[i++] = new int[]{top[1], top[2]};
    }
    return result;
  }

  public int leastInterval(char[] tasks, int n) {
    // pull max freq value, since we take it we need to wait for n times
    // so we pull another n values following the same policy
    int[] freq = new int[26];
    for (char ch : tasks) freq[ch - 'A']++;

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    for (int i = 0; i < 26; ++i) {
      if (freq[i] > 0) maxHeap.offer(freq[i]);
    }

    int time = 0;

    while (!maxHeap.isEmpty()) {
      List<Integer> store = new ArrayList<>();
      int count = 0;
      for (int i = 1; i <= n + 1; ++i) {
        // so if we pick a value we will need to wait for n+1 to get that value again
        // ex. A__|
        // we pick A now we have two space left, we'll put another char there
        // so if we have other char like B/C we put in those spaces
        if (!maxHeap.isEmpty()) {
          int top = maxHeap.poll();
          if (top > 1) store.add(top - 1);
          count++;
        }
      }
      store.forEach(maxHeap::offer);
      // AB_|AB_|AB
      // for the last one heap will be empty, so we put the actual count
      time += maxHeap.isEmpty() ? count : n + 1;
    }
    return time;
  }
}
