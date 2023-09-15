package com.zubayear.customds.binsearchtree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    BinarySearchTree bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
    }

    @AfterEach
    void tearDown() {
        bst = null;
    }

    @Test
    void insert() {
        int[] nums = {10,5,20,8,30};
        for (int n : nums) {
            bst.insert(n);
        }
        bst.inorder(bst.root);
    }

    @Test
    void inorder() {
    }

    @Test
    void search() {
        int[] nums = {10,5,20,8,30};
        for (int n : nums) {
            bst.insert(n);
        }
        boolean search = bst.search(20);
        System.out.println(search);
        boolean search1 = bst.search(90);
        System.out.println(search1);
    }

    @Test
    void insertRecur() {
        int[] nums = {10,5,20,8,30};
        BinSearchNode root = null;
        for (int i = 0; i < nums.length; ++i) {
            if (i != 0) {
                bst.insertRecur(bst.root, nums[i]);
                continue;
            }
            root = bst.insertRecur(bst.root, nums[i]);
        }
        bst.inorder(root);
    }

    @Test
    void pattern() {
        int k = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i; --j) {
                System.out.print(k++);
            }
            k = 1;
            System.out.println();
        }
    }
}