package com.lucienvirecourt.jedi.problems;

import com.lucienvirecourt.jedi.datastructure.bintree.BinaryTree;
import com.lucienvirecourt.jedi.datastructure.bintree.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeProblemsTest {
  BinaryTree<Integer> binaryTree;
  BinaryTreeProblems btp;

  @BeforeEach
  void setup() {
    binaryTree = new BinaryTree<>();
    btp = new BinaryTreeProblems();
  }

  @Test
  void testIsSameTree() {
    TreeNode<Integer> p = binaryTree.buildTree(new Integer[]{1, 2, 3});
    TreeNode<Integer> q = binaryTree.buildTree(new Integer[]{1, 2, 3});
    assertTrue(btp.isSameTree(p, q));
  }
}
