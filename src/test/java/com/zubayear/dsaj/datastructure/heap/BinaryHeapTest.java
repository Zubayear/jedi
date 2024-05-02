package com.zubayear.dsaj.datastructure.heap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryHeapTest {

    BinaryHeap<Integer> binaryHeap;

    @BeforeEach
    void setUp() {
//        Integer[] minHeap = new Integer[]{5, 10, 30, 20, 35, 40, 15};
//        binaryHeap = new BinaryHeap<>(minHeap);
        binaryHeap = new BinaryHeap<>();
    }

    @AfterEach
    void tearDown() {
        binaryHeap = null;
    }

    @Test
    void testBinaryHeap() {
        Integer[] minHeap = new Integer[]{5, 10, 30, 20, 35, 40, 15};
        for (int i : minHeap) {
            binaryHeap.add(i);
        }
        Assertions.assertEquals(5, binaryHeap.poll());
        Assertions.assertEquals(15, binaryHeap.peek());
        Assertions.assertFalse(binaryHeap.contains(90));
        binaryHeap.clear();
        Assertions.assertTrue(binaryHeap.isEmpty());

        // max heap
        for (int i : minHeap) {
            binaryHeap.add(-i);
        }
        Assertions.assertEquals(40, -binaryHeap.poll());
        Assertions.assertEquals(35, -binaryHeap.peek());
    }
}