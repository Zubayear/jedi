package com.zubayear.customds.heap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {

    MaxHeap mh;

    @BeforeEach
    void setUp() {
        mh = new MaxHeap(10);
    }

    @AfterEach
    void tearDown() {
        mh = null;
    }

    @Test
    void add() {
        try {
            mh.add(40);
            mh.add(35);
            mh.add(15);
            mh.add(30);
            mh.add(10);
            mh.add(12);
            mh.add(6);
            mh.add(5);
            mh.add(20);
            mh.add(50);
//            mh.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void pop() {
        add();
        mh.print();
        mh.pop();
        System.out.println(mh.size);
        mh.print();
    }

    @Test
    void sort() {
        add();
        mh.print();
        mh.sort();
        mh.print();
//        mh.print();
    }
}