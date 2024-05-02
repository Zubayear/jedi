package com.zubayear.dsaj.customds.heap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertEquals(binaryHeap.poll(), 5);
        Assertions.assertEquals(binaryHeap.peek(), 15);
        Assertions.assertFalse(binaryHeap.contains(90));
        binaryHeap.clear();
        Assertions.assertTrue(binaryHeap.isEmpty());

        // max heap
        for (int i : minHeap) {
            binaryHeap.add(-i);
        }
        Assertions.assertEquals(-binaryHeap.poll(), 40);
        Assertions.assertEquals(-binaryHeap.peek(), 35);
    }
}