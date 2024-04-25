package com.zubayear.dsaj.customds.heap;

import java.util.PriorityQueue;

public class HeapProblems {
    public int findKthLargest(int[] nums, int k) {
        // {3,2,1,5,6,4};
        // we need min heap
        // try to kee the heap size to k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.poll();
    }
}
