package com.zubayear.dsaj.datastructure.binsearchtree;

import com.zubayear.dsaj.datastructure.bintree.TreeNode;
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
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60);
        var root = binarySearchTree.sortedArrayToBST(list);
        binarySearchTree.insertIntoBST(root, 38);

        Assertions.assertNull(binarySearchTree.searchInBST(root, 69));
    }

    @Test
    void sortedArrayToBST() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60);
        var root = binarySearchTree.sortedArrayToBST(list);
        binarySearchTree.insertIntoBST(root, 38);
        String result = binarySearchTree.inorder(root);
        Assertions.assertEquals("[10,20,30,38,40,50,60]", result);

        binarySearchTree.insertRecursive(root, 78);
        String s = binarySearchTree.leverOrder(root);
        Assertions.assertEquals("[[30],[10,50],[20,40,60],[38,78]]", s);
    }

    @Test
    void insertRecursive() {
        TreeNode<Integer> root = binarySearchTree.insertRecursive(null, 30);
        binarySearchTree.insertRecursive(root, 10);
        binarySearchTree.insertRecursive(root, 20);
        binarySearchTree.insertRecursive(root, 40);
        binarySearchTree.insertRecursive(root, 50);
        binarySearchTree.insertRecursive(root, 60);
        binarySearchTree.insertRecursive(root, 38);
        String result = binarySearchTree.inorder(root);
        Assertions.assertEquals("[10,20,30,38,40,50,60]", result);

    }

    @Test
    void isValidBST() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60);
        var root = binarySearchTree.sortedArrayToBST(list);
        binarySearchTree.insertIntoBST(root, 38);
        Assertions.assertTrue(binarySearchTree.isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void deleteNode() {
        Integer[] numbers = new Integer[]{30, 20, 10, 15, 25, 40, 50, 45};
        var root = binarySearchTree.preorderToBST(numbers);
        var result = binarySearchTree.deleteNode(root, 30);
        String inorder = binarySearchTree.inorder(result);
        Assertions.assertEquals("[10,15,20,25,40,45,50]", inorder);
    }

    @Test
    void inorderPredecessor() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 60);
        var root = binarySearchTree.sortedArrayToBST(list);


        var result = binarySearchTree.inorderPredecessor(root, new TreeNode<>(40));
        System.out.println(result.val);
    }
}