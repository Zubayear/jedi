package com.lucienvirecourt.jedi.datastructure.binsearchtree;

import com.lucienvirecourt.jedi.datastructure.bintree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTree<T extends Comparable<T>> {

  private TreeNode<T> root;

  public TreeNode<T> getRoot() {
    return root;
  }

  public boolean add(T val) {
    if (doesExist(root, val)) {
      return false;
    } else {
      root = add(root, val);
      return true;
    }
  }

  private TreeNode<T> add(TreeNode<T> node, T val) {
    if (node == null) {
      node = new TreeNode<>(val);
    } else {
      if (val.compareTo(node.val) < 0) {
        node.left = add(node.left, val);
      } else {
        node.right = add(node.right, val);
      }
    }
    return node;
  }

  private boolean doesExist(TreeNode<T> node, T val) {
    if (node == null) return false;
    int cmp = val.compareTo(node.val);
    if (cmp < 0) return doesExist(node.left, val);
    else if (cmp > 0) return doesExist(node.right, val);
    else return true;
  }

  public TreeNode<T> deleteNode(TreeNode<T> root, T key) {
    if (root == null) {
      return null;
    }
    if (key.compareTo(root.val) < 0) {
      root.left = deleteNode(root.left, key);
    } else if (key.compareTo(root.val) > 0) {
      root.right = deleteNode(root.right, key);
    } else {
      if (root.left == null) {
        return root.right;
      }
      if (root.right == null) {
        return root.left;
      }
      TreeNode<T> successor = successor(root.right);
      root.val = successor.val;
      root.right = deleteNode(root.right, root.val);
    }
    return root;
  }

  public TreeNode<T> preorderToBST(T[] numbers) {
    if (numbers == null || numbers.length == 0) {
      return null;
    }
    int n = numbers.length;
    int i = 0;
    TreeNode<T> root = new TreeNode<>(numbers[i++]);
    TreeNode<T> p = root;
    Deque<TreeNode<T>> stack = new ArrayDeque<>();

    while (i < n) {
      // if the p.val is less than, then go to left
      assert p != null;
      if (numbers[i].compareTo(p.val) < 0) {
        TreeNode<T> newNode = new TreeNode<>(numbers[i++]);
        p.left = newNode;
        stack.offerFirst(p);
        p = newNode;
      } else {
        // in this case, check if the value is between p.val and the node at the top of the stack
        var top = stack.peekFirst() == null ? Integer.MAX_VALUE : (Integer) stack.peekFirst().val;
        if (numbers[i].compareTo(p.val) > 0 && (Integer) numbers[i] < top) {
          TreeNode<T> newNode = new TreeNode<>(numbers[i++]);
          p.right = newNode;
          p = newNode;
        } else {
          p = stack.pollFirst();
        }
      }
    }
    return root;
  }

  private TreeNode<T> successor(TreeNode<T> node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  public TreeNode<T> inorderPredecessor(TreeNode<T> root, TreeNode<T> node) {
    if (root == null) {
      return null;
    }
    TreeNode<T> predecessor = null;
    TreeNode<T> traveler = root;
    while (traveler != null) {
      if (node.val.compareTo(traveler.val) < 0) {
        traveler = traveler.left;
      } else if (node.val.compareTo(traveler.val) > 0) {
        predecessor = traveler;
        traveler = traveler.right;
      } else {
        break;
      }
    }
    return predecessor;
  }

  public String leverOrder(TreeNode<T> root) {
    StringBuilder sb = new StringBuilder();
    if (root == null) {
      return "";
    }
    sb.append("[");
    Deque<TreeNode<T>> queue = new ArrayDeque<>();
    queue.offerFirst(root);
    while (!queue.isEmpty()) {
      int qSize = queue.size();
      sb.append("[");
      for (int i = 0; i < qSize; i++) {
        TreeNode<T> node = queue.pollLast();
        assert node != null;
        sb.append(node.val).append(",");
        if (node.left != null) {
          queue.offerFirst(node.left);
        }
        if (node.right != null) {
          queue.offerFirst(node.right);
        }
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append("],");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    return sb.toString();
  }
}
