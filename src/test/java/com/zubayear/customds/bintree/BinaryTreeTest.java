package com.zubayear.customds.bintree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BinaryTreeTest {

    BinaryTree<Integer> btree;
    TreeNode<Integer> root;

    @BeforeEach
    void setUp() {
        btree = new BinaryTree<>();
        Integer[] numbers = {1,null,2,3,4};
        root = btree.createBinaryTree(numbers);
    }

    @AfterEach
    void tearDown() {
        btree = null;
    }

    @Test
    void insert() {
        List<List<Integer>> expected = btree._bfs(btree.root);
        System.out.println(expected);
    }

    @Test
    void bfs() {
    }

    @Test
    void dfs() {
    }

    @Test
    void preorderIter() {
    }

    @Test
    void iterativePreorder() {
        List<Integer> result = btree.iterativePreorder(btree.root);
        System.out.println(result);
    }

    @Test
    void iterativeInorder() {
        List<Integer> result = btree.iterativeInorder(btree.root);
        System.out.println(result);
    }

    @Test
    void iterativePostorder() {
        List<Integer> result = btree.iterativePostorder(btree.root);
        System.out.println(result);
    }

    @Test
    void maxDepth() {
        Assertions.assertEquals(btree.maxDepth(root), 3);
    }

    @Test
    void isBalanced() {
        Assertions.assertTrue(btree.isBalanced(root));
    }


    @Test
    void diameterOfBinaryTree() {
        Assertions.assertEquals(btree.diameterOfBinaryTree(root), 1);
    }

    @Test
    void maxPathSum() {
        Assertions.assertEquals(btree.maxPathSum(root), 2);
    }

    @Test
    void zigzagLevelOrder() {
        List<List<Integer>> expected = new ArrayList<>();

        expected.add(List.of(3));
        expected.add(Arrays.asList(20, 9));
        expected.add(Arrays.asList(15, 7));
        Assertions.assertEquals(btree.zigzagLevelOrder(root), expected);
    }

    @Test
    void boundaryOfBinaryTree() {
        List<List<Integer>> res = btree._bfs(root);
        System.out.println(res);
//        Assertions.assertEquals(btree.boundaryOfBinaryTree(root), List.of(1,3,4,2));
    }
}