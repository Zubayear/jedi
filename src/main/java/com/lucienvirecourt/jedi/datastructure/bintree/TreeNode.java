package com.lucienvirecourt.jedi.datastructure.bintree;

import java.util.Objects;

public class TreeNode<T> {
  public T val;
  public TreeNode<T> left, right;

  public TreeNode() {
  }

  public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public TreeNode(T val) {
    this.val = val;
    this.left = null;
    this.right = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TreeNode<?> treeNode)) return false;
    return Objects.equals(val, treeNode.val) && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(val, left, right);
  }

  @Override
  public String toString() {
    return "TreeNode{" +
      "val=" + val +
      ", left=" + left +
      ", right=" + right +
      '}';
  }
}
