package com.lucienvirecourt.jedi.problems;

import com.lucienvirecourt.jedi.datastructure.bintree.BinaryTree;
import com.lucienvirecourt.jedi.datastructure.bintree.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lucienvirecourt.jedi.problems.BinaryTreeProblems.isSameTree;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeProblemsTest {
  BinaryTree<Integer> binaryTree;

  @BeforeEach
  void setup() {
    binaryTree = new BinaryTree<>();
  }

  @Test
  void testIsSameTree() {
    TreeNode<Integer> p = binaryTree.buildTree(new Integer[]{1,2,3});
    TreeNode<Integer> q = binaryTree.buildTree(new Integer[]{1,2,3});

    assertTrue(isSameTree(p, q));
  }
}
