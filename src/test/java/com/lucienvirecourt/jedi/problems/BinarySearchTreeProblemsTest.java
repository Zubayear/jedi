package com.lucienvirecourt.jedi.problems;

import com.lucienvirecourt.jedi.datastructure.bintree.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeProblemsTest {

  BinarySearchTreeProblems bst;

  @BeforeEach
  void setup() {
    bst = new BinarySearchTreeProblems();
  }

  @Test
  void bstFromPreorder_basic() {
    TreeNode<Integer> root = bst.bstFromPreorder(new int[]{8, 3, 1, 6, 4, 7, 10, 14});
    assertNotNull(root);
    assertEquals(8, root.val);
  }

  @Test
  void bstFromPreorder_singleNode() {
    TreeNode<Integer> root = bst.bstFromPreorder(new int[]{5});
    assertEquals(5, root.val);
    assertNull(root.left);
    assertNull(root.right);
  }

  @Test
  void bstFromPreorder_descending() {
    TreeNode<Integer> root = bst.bstFromPreorder(new int[]{5, 4, 3, 2, 1});
    assertEquals(5, root.val);
    assertNull(root.right);
    assertEquals(4, root.left.val);
  }

  @Test
  void bstFromPreorder_empty() {
    TreeNode<Integer> root = bst.bstFromPreorder(new int[]{});
    assertNull(root);
  }

  @Test
  void kthLargestElement_singleNode() {
    TreeNode<Integer> root = new TreeNode<>(5);
    assertEquals(5, bst.kthLargestElement(root, 1));
  }

  @Test
  void kthLargestElement_nullRoot() {
    assertEquals(0, bst.kthLargestElement(null, 1));
  }

  @Test
  void removeLeafNodes_nullRoot() {
    assertNull(bst.removeLeafNodes(null, 1));
  }
}