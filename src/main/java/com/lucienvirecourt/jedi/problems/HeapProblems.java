package com.lucienvirecourt.jedi.problems;

import java.util.PriorityQueue;

public class HeapProblems {

  public static int findKthLargest(int[] nums, int k) {
    // since we need to find largest, we will create a min heap
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int n : nums) {
      minHeap.offer(n);
      if (minHeap.size() > k) minHeap.poll();
    }
    return !minHeap.isEmpty() ? minHeap.poll() : 0;
  }
}
