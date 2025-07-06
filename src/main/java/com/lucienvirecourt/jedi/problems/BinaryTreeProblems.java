package com.lucienvirecourt.jedi.problems;

import com.lucienvirecourt.jedi.datastructure.bintree.TreeNode;

import java.util.*;

public class BinaryTreeProblems {
  public  <T extends Comparable<T>> boolean isSameTree(TreeNode<T> p, TreeNode<T> q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    if (p.val != q.val) return false;
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  public  <T extends Comparable<T>> int maxDepth(TreeNode<T> root) {
    if (root == null) return 0;
    int lh = maxDepth(root.left);
    int rh = maxDepth(root.right);
    return 1 + Math.max(lh, rh);
  }

  public  <T extends Comparable<T>> boolean isBalanced(TreeNode<T> root) {
    return getHeight(root) != -1;
  }

  private  <T extends Comparable<T>> int getHeight(TreeNode<T> root) {
    if (root == null) return 0;
    int lh = getHeight(root.left);
    int rh = getHeight(root.right);
    if (Math.abs(lh - rh) > 1) return -1;
    return 1 + Math.max(lh, rh);
  }

  public  <T extends Comparable<T>> int diameter(TreeNode<T> root) {
    int[] max = new int[]{-(int) 1e9};
    diameter(root, max);
    return max[0];
  }

  private  <T extends Comparable<T>> int diameter(TreeNode<T> root, int[] max) {
    if (root == null) return 0;
    int lh = diameter(root.left, max);
    int rh = diameter(root.right, max);
    max[0] = Math.max(max[0], lh + rh);
    return 1 + Math.max(lh, rh);
  }

  public  int maxPathSum(TreeNode<Integer> root) {
    int[] max = new int[]{-(int) 1e9};
    maxPathSum(root, max);
    return max[0];
  }

  private  int maxPathSum(TreeNode<Integer> root, int[] max) {
    if (root == null) return 0;
    int ls = Math.max(0, maxPathSum(root.left, max)); // if we get a negative sum from the left
    int rs = Math.max(0, maxPathSum(root.right, max));
    max[0] = Math.max(max[0], root.val + ls + rs);
    return root.val + Math.max(ls, rs); // we return this since we need to know which path to take that would yield max value
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode<Integer> root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    boolean lToR = true;
    Deque<TreeNode<Integer>> queue = new ArrayDeque<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> temp = new LinkedList<>();
      for (int i = 0; i < size; ++i) {
        TreeNode<Integer> current = queue.poll();
        assert current != null;
        if (lToR) {
          temp.add(current.val);
        } else {
          temp.addFirst(current.val);
        }
      }
      result.add(temp);
      lToR = !lToR;
    }
    // l20 comeback
    // l21 comeback
    return result;
  }

  public <T extends Comparable<T>> List<T> rightView(TreeNode<T> root) {
    List<T> result = new ArrayList<>();
    rightView(root, 0, result);
    return result;
  }

  private <T extends Comparable<T>> void rightView(TreeNode<T> root, int level, List<T> result) {
    // Root Right Left
    if (root == null) return;
    if (level == result.size()) result.add(root.val); // right most element of that level
    rightView(root.right, level + 1, result);
    rightView(root.left, level + 1, result);
  }

  public  <T extends Comparable<T>> List<T> leftView(TreeNode<T> root) {
    List<T> result = new ArrayList<>();
    leftView(root, 0, result);
    return result;
  }

  private  <T extends Comparable<T>> void leftView(TreeNode<T> root, int level, List<T> result) {
    // Root Left Right
    if (root == null) return;
    if (level == result.size()) result.add(root.val); // left most element of that level
    leftView(root.left, level + 1, result);
    leftView(root.right, level + 1, result);
  }

  public  TreeNode<Integer> lcs(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
    if (root == null || root == p || root == q) return root;
    TreeNode<Integer> left = lcs(root.left, p, q);
    TreeNode<Integer> right = lcs(root.right, p, q);
    if (left != null && right != null) return root;
    return left != null ? left : right;
  }

  public  void childrenSumProperty(TreeNode<Integer> root) {
    if (root == null) return;
    int child = 0;
    if (root.left != null) child += root.left.val;
    if (root.right != null) child += root.right.val;
    if (child < root.val) {
      if (root.left != null) root.left.val = child;
      if (root.right != null) root.right.val = child;
    } else {
      root.val = child;
    }
    childrenSumProperty(root.left);
    childrenSumProperty(root.right);
    int total = 0;
    if (root.left != null) total += root.left.val;
    if (root.right != null) total += root.right.val;
    if (root.left != null || root.right != null) root.val = total;
  }

  public  List<Integer> distanceK(TreeNode<Integer> root, TreeNode<Integer> target, int k) {
    Map<TreeNode<Integer>, TreeNode<Integer>> parentMap = new HashMap<>();
    markParent(root, parentMap);
    Map<TreeNode<Integer>, Boolean> visited = new HashMap<>();
    Deque<TreeNode<Integer>> queue = new ArrayDeque<>();
    queue.offer(target);
    visited.put(target, true);
    int currentLevel = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      if (currentLevel == k) break;
      currentLevel++;
      for (int i = 0; i < size; ++i) {
        TreeNode<Integer> current = queue.poll();
        assert current != null;
        if (current.left != null && visited.get(current.left) == null) {
          queue.offer(current.left);
          visited.put(current.left, true);
        }
        if (current.right != null && visited.get(current.right) == null) {
          queue.offer(current.right);
          visited.put(current.right, true);
        }
        if (parentMap.get(current) != null && visited.get(parentMap.get(current)) == null) {
          TreeNode<Integer> val = parentMap.get(current);
          queue.offer(val);
          visited.put(val, true);
        }
      }
    }
    List<Integer> result = new ArrayList<>();
    while (!queue.isEmpty()) {
      TreeNode<Integer> current = queue.poll();
      result.add(current.val);
    }
    return result;
  }

  private  void markParent(TreeNode<Integer> root, Map<TreeNode<Integer>, TreeNode<Integer>> map) {
    Deque<TreeNode<Integer>> queue = new ArrayDeque<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode<Integer> current = queue.poll();

      if (current.left != null) {
        map.put(current.left, current);
        queue.offer(current.left);
      }

      if (current.right != null) {
        map.put(current.right, current);
        queue.offer(current.right);
      }
    }
  }

}
