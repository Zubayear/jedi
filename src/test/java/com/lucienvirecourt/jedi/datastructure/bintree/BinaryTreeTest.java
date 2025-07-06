package com.lucienvirecourt.jedi.datastructure.bintree;

import com.lucienvirecourt.jedi.problems.BinaryTreeProblems;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.lucienvirecourt.jedi.problems.BinaryTreeProblems.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class BinaryTreeTest {

  BinaryTree<Integer> binaryTree;
  BinaryTreeProblems btp;

  @BeforeEach
  void setUp() {
    binaryTree = new BinaryTree<>();
    btp = new BinaryTreeProblems();
  }

  @AfterEach
  void tearDown() {
    binaryTree = null;
  }

  @Test
  void testBinaryTreeCreationAndTraversals() {
    Integer[] preorder = new Integer[]{3, 9, 20, 15, 7};
    Integer[] inorder = new Integer[]{9, 3, 15, 20, 7};
    TreeNode<Integer> root = binaryTree.buildTree(preorder, inorder);

    var actualLevelOrderTraversal = binaryTree.levelOrderTraversal(root);
    var expectedLevelOrderTraversal = List.of(List.of(3), Arrays.asList(9, 20), Arrays.asList(15, 7));
    assertThat("Level order traversal",
      actualLevelOrderTraversal,
      containsInAnyOrder(expectedLevelOrderTraversal.toArray()));

    var actualPreorderTraversal = binaryTree.iterativePreorder(root);
    assertThat("Preorder traversal", actualPreorderTraversal.toArray(), is(preorder));

    var actualInorderTraversal = binaryTree.iterativeInorder(root);
    assertThat("Inorder traversal", actualInorderTraversal.toArray(), is(inorder));

    var actualPostOrderTraversal = binaryTree.iterativePostorder(root);
    Integer[] postorder = new Integer[]{9, 15, 7, 20, 3};
    assertThat("Postorder traversal", actualPostOrderTraversal.toArray(), is(postorder));

  }

  @Test
  void testBuildTree() {
    var p = binaryTree.buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
    var q = binaryTree.buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7, null, 8});
    boolean sameTree = btp.isSameTree(p, q);
    assertThat("Build tree from level order traversal", false, is(sameTree));
  }

  @Test
  void testIsBalanced() {
    assertThat("Is tree balanced",
      false,
      is(btp.isBalanced(binaryTree.buildTree(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4})))
    );
    assertThat("Tree balanced",
      true,
      is(btp.isBalanced(binaryTree.buildTree(new Integer[]{3, 9, 20, null, null, 15, 7})))
    );
  }

  @Test
  void testDiameter() {
    assertThat("Test Diameter", 3, is(btp.diameter(binaryTree.buildTree(new Integer[]{1, 2, 3, 4, 5}))));
    assertThat("Test Diameter", 1, is(btp.diameter(binaryTree.buildTree(new Integer[]{1, 2}))));
  }

  @Test
  void testLeftView() {
    Integer[] nodes = {1, 2, 3, 4, 5, null, 7, null, null, 6, null, 10, null, null, null, null, 12};

    var root = binaryTree.buildTree(nodes);
    assertThat("Left side view of the tree",
      List.of(1, 2, 4, 6, 12),
      is(btp.leftView(root))
    );
  }
}
