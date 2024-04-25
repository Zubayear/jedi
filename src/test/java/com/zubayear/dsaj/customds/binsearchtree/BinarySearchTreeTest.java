package com.zubayear.dsaj.customds.binsearchtree;

import com.zubayear.dsaj.customds.bintree.TreeNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BinarySearchTreeTest {

    BinarySearchTree<Integer> binarySearchTree;

    @BeforeEach
    void setUp() {
        binarySearchTree = new BinarySearchTree<>();
    }

    @AfterEach
    void tearDown() {
        binarySearchTree = null;
    }

    @Test
    void insertIntoBST() {
        TreeNode<Integer> root = new TreeNode<>(30);
        binarySearchTree.insertIntoBST(root, 20);
        binarySearchTree.insertIntoBST(root, 40);
        binarySearchTree.insertIntoBST(root, 10);
        binarySearchTree.insertIntoBST(root, 25);
        binarySearchTree.insertIntoBST(root, 35);
        binarySearchTree.insertIntoBST(root, 50);
        binarySearchTree.insertIntoBST(root, 38);

        String preorder = binarySearchTree.preorder(root);
        System.out.println(preorder);
    }

    @Test
    void sortedArrayToBST() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60);
        var root = binarySearchTree.sortedArrayToBST(list);
        binarySearchTree.insertIntoBST(root, 38);
        String result = binarySearchTree.inorder(root);
        Assertions.assertEquals("[10,20,30,38,40,50,60]", result);

        binarySearchTree.insertIntoBST(root, 78);
        String s = binarySearchTree.leverOrder(root);
        Assertions.assertEquals("[[30],[10,50],[20,40,60],[38,78]]", s);
    }
}