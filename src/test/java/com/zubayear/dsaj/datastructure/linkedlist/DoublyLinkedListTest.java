package com.zubayear.dsaj.datastructure.linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {

    DoublyLinkedList<Integer> dll;

    @BeforeEach
    void setUp() {
        dll = new DoublyLinkedList<>();
    }

    @AfterEach
    void tearDown() {
        dll = null;
    }

    @Test
    void testLinkedListOperations() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 67, 8};
        for (int a : arr) {
            dll.addLast(a);
        }
        dll.addFirst(30);
        dll.addFirst(300);
        dll.addFirst(35);
        dll.addFirst(34);
        Assertions.assertEquals(11, dll.size());
        Assertions.assertEquals(34, dll.peekFirst());
        Assertions.assertEquals(8, dll.peekLast());
        dll.add(45);
        dll.addAt(4, 69);
        dll.removeFirst();
        Assertions.assertEquals(35, dll.peekFirst());
        var val = dll.removeAt(3);
        Assertions.assertEquals(69, val);
        Assertions.assertFalse(dll.remove(900));
        Assertions.assertTrue(dll.contains(45));
        Assertions.assertEquals(7, dll.indexOf(4));
        dll.addFirst(4);
        Assertions.assertEquals(1, dll.indexOf(4));
        dll.clear();
        Assertions.assertTrue(dll.isEmpty());
        Assertions.assertEquals("[]", dll.toString());
    }
}