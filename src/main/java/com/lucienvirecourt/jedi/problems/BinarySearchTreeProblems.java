package com.lucienvirecourt.jedi.problems;

import com.lucienvirecourt.jedi.datastructure.bintree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTreeProblems {

  int index = 0;

  public TreeNode<Integer> bstFromPreorder(int[] preorder) {
    return build(preorder.length, -(int) 1e9, (int) 1e9, preorder);
  }

  public TreeNode<Integer> build(int n, int lower, int upper, int[] preorder) {
    if (index == n) return null;
    int val = preorder[index];
    if (val < lower || val > upper) return null; // violates the BST property
    index++;
    TreeNode<Integer> root = new TreeNode<>(val);
    root.left = build(n, lower, val, preorder); // left subtree (upper bound is the parent value)
    root.right = build(n, upper, val, preorder); // the lower bound is the parent value, the upper bound is the biggest value
    return root;
  }

  // O(H + k) | O(H)
  public int kthLargestElement(TreeNode<Integer> root, int k) {
    if (root == null) return 0;
    // left node right
    // when we encounter node we increment the counter, and if the counter == k, we found the ans
    Deque<TreeNode<Integer>> stack = new ArrayDeque<>();
    while (true) {
      while (root != null) {
        stack.offer(root);
        root = root.left; // so we go to the left
      }
      // we encounter the node
      root = stack.poll();
      if (--k == 0 && root != null) return root.val;
      assert root != null;
      root = root.right; // we go to the right
    }
  }

  public TreeNode<Integer> removeLeafNodes(TreeNode<Integer> root, int target) {
    if (root == null) return null;
    // recursively delete leaf from the left subtree and attach the null with nodes' left
    root.left = removeLeafNodes(root.left, target);
    // recursively delete leaf from the right subtree and attach the null with nodes' right
    root.right = removeLeafNodes(root.right, target);

    // if we are at leaf and found, the value returns null; this will attach with the calling node
    if (root.left == null && root.right == null && root.val == target) return null;
    return null;
  }
}
