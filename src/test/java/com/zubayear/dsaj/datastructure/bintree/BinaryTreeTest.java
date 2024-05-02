package com.zubayear.dsaj.datastructure.bintree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class BinaryTreeTest {

    BinaryTree<Integer> binaryTree;

    @BeforeEach
    void setUp() {
        binaryTree = new BinaryTree<>();
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
}