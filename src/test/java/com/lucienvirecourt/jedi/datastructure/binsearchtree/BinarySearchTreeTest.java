package com.lucienvirecourt.jedi.datastructure.binsearchtree;

import com.lucienvirecourt.jedi.datastructure.bintree.TreeNode;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BinarySearchTreeTest {

  BinarySearchTree<Integer> bst;

  @BeforeEach
  void setUp() {
    bst = new BinarySearchTree<>();
    bst.add(4);
    bst.add(2);
    bst.add(7);
    bst.add(1);
    bst.add(3);
    bst.add(5);
  }

  @AfterEach
  void tearDown() {
    bst = null;
  }

  @Test
  @DisplayName("Test binary search tree creation")
  void testBinarySearchTree() {
    TreeNode<Integer> root = bst.getRoot();
    assertThat("[[4],[2,7],[1,3,5]]", is(bst.leverOrder(root)));
  }
}
