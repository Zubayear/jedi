package com.lucienvirecourt.jedi.datastructure.bintree;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> {
  public TreeNode<T> root;

  public TreeNode<T> buildTree(T[] nodes) {
    if (nodes == null || nodes.length == 0 || nodes[0] == null) {
      root = null;
      return null;
    }
    root = new TreeNode<>(nodes[0]);
    Deque<TreeNode<T>> queue = new LinkedList<>();
    queue.offer(root);

    int i = 1;
    while (!queue.isEmpty() && i < nodes.length) {
      TreeNode<T> current = queue.poll();
      if (nodes[i] != null) {
        current.left = new TreeNode<>(nodes[i]);
        queue.offer(current.left);
      }
      i++;
      if (i < nodes.length && nodes[i] != null) {
        current.right = new TreeNode<>(nodes[i]);
        queue.offer(current.right);
      }
      i++;
    }
    return root;
  }

  public TreeNode<T> buildTree(T[] preorder, T[] inorder) {
    Map<T, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; ++i) {
      map.put(inorder[i], i); // 9 -> 0, 3 -> 1
    }
    return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
  }

  private TreeNode<T> buildTree(T[] preorder, int preStart, int preEnd, T[] inorder, int inStart, int inEnd, Map<T, Integer> inMap) {
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }
    // inorder [9,3,15,20,7], preorder [3,9,20,15,7]
    // preorder's first node is root
    TreeNode<T> root = new TreeNode<>(preorder[preStart]);
    int inRoot = inMap.get(root.val); // 1
    int numsOnLeft = inRoot - inStart; // 1 - 0, we found this inorder to distribute the left subtree and right subtree

    root.left = buildTree(preorder, preStart + 1, preStart + numsOnLeft, inorder, inStart, inRoot - 1, inMap);
    root.right = buildTree(preorder, preStart + numsOnLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);
    return root;
  }

  List<T> iterativePreorder(TreeNode<T> root) {
    List<T> result = new ArrayList<>();
    if (root == null) return result;
    // push root,
    // get the top of stack
    // push right then left
    Deque<TreeNode<T>> stack = new ArrayDeque<>();
    stack.offerFirst(root);
    while (!stack.isEmpty()) {
      TreeNode<T> node = stack.pollFirst();
      result.add(node.val);
      if (node.right != null) {
        stack.offerFirst(node.right);
      }
      if (node.left != null) {
        stack.offerFirst(node.left);
      }
    }
    return result;
  }

  List<T> iterativeInorder(TreeNode<T> root) {
    List<T> result = new ArrayList<>();
    if (root == null) return result;

    Deque<TreeNode<T>> stack = new ArrayDeque<>();
    TreeNode<T> node = root;
    while (true) {
      // go as far left as possible
      if (node != null) {
        stack.offerFirst(node);
        node = node.left;
      } else {
        if (stack.isEmpty()) {
          break;
        }
        node = stack.pollFirst();
        result.add(node.val);
        // go right
        node = node.right;
      }
    }
    return result;
  }

  List<T> iterativePostorder(TreeNode<T> root) {
    List<T> result = new ArrayList<>();
    if (root == null) return result;
    Deque<TreeNode<T>> stack1 = new ArrayDeque<>();
    Deque<TreeNode<T>> stack2 = new ArrayDeque<>();
    stack1.offerFirst(root);
    while (!stack1.isEmpty()) {
      TreeNode<T> node = stack1.pollFirst();
      stack2.offerFirst(node);
      if (node.left != null) {
        stack1.offerFirst(node.left);
      }
      if (node.right != null) {
        stack1.offerFirst(node.right);
      }
    }
    while (!stack2.isEmpty()) {
      TreeNode<T> node = stack2.pollFirst();
      result.add(node.val);
    }
    return result;
  }

  public List<List<T>> levelOrderTraversal(TreeNode<T> root) {
    List<List<T>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Deque<TreeNode<T>> queue = new ArrayDeque<>();
    List<T> tempList = new ArrayList<>();
    queue.offerLast(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        var top = queue.pollFirst();
        assert top != null;
        tempList.add(top.val);

        if (top.left != null) {
          queue.offerLast(top.left);
        }
        if (top.right != null) {
          queue.offerLast(top.right);
        }
      }
      result.add(new ArrayList<>(tempList));
      tempList.clear();
    }
    return result;
  }
}

