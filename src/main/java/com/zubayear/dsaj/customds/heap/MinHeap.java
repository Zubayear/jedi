package com.zubayear.dsaj.customds.heap;

public class MinHeap {
    private int[] minHeap;
    private int heapSize;
    private int realSize = 0;

    public MinHeap(int heapSize) {
        this.heapSize = heapSize;
        minHeap = new int[heapSize + 1]; // 0th index will have nothing, we start at 1
        minHeap[0] = 0;
    }

    public void add(int val) throws Exception {
        realSize++;
        if (realSize > heapSize) {
            realSize--;
            throw new Exception("added too many elements");
        }
        minHeap[realSize] = val;
        // swap parent and newly inserted child until
        // it satisfy the min-heap property i.e. parent < val
        // 0, 6, 9, 10, 15, 12, val
        int index = realSize;
        int parentIdx = index / 2;
        while (minHeap[parentIdx] > minHeap[index] && index > 1) {
            int tmp = minHeap[parentIdx];
            minHeap[parentIdx] = minHeap[index];
            minHeap[index] = tmp;
            parentIdx = index / 2;
        }
    }

    public int peek() {
        return minHeap[1];
    }

    public int size() {
        return realSize;
    }

    public int pop() {
        if (realSize < 1) return -1;
        int removeElement = minHeap[1]; // 6
        // put the last element in the root
        minHeap[1] = minHeap[realSize]; // 0, 12, 9, 10, 15, 12
        realSize--;
        int index = 1;
        while (index <= realSize / 2) {
            int leftChildIdx = index * 2;
            int rightChildIdx = (index * 2) + 1;
            // if parent is larger than children
            if (minHeap[index] > minHeap[leftChildIdx] || minHeap[index] > minHeap[rightChildIdx]) {
                int tmp;
                if (minHeap[leftChildIdx] < minHeap[index]) {
                    // swap with left child
                    tmp = minHeap[leftChildIdx];
                    minHeap[leftChildIdx] = minHeap[index];
                    index = leftChildIdx;
                } else {
                    // swap with right child
                    tmp = minHeap[rightChildIdx];
                    minHeap[rightChildIdx] = minHeap[index];
                    index = rightChildIdx;
                }
                minHeap[index] = tmp;
            } else {
                break;
            }
        }
        return removeElement;
    }
}
