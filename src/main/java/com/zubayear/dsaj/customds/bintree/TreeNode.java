package com.zubayear.dsaj.customds.bintree;

public class TreeNode<T> {
    protected T val;
    protected TreeNode<T> left, right;

    public TreeNode(T val) {
        this.val = val;
    }
}
