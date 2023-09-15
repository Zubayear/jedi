package com.zubayear.customds.binsearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree {
    public BinSearchNode root;

    public void insert(int k) {
        BinSearchNode t = root, r = t;
        if (t == null) {
            // first node
            root = new BinSearchNode(k);
            return;
        }
        while (t != null) {
            r = t;
            if (k > t.val) t = t.right;
            else if (k < t.val) t = t.left;
            else return;
        }
        BinSearchNode node = new BinSearchNode(k);
        if (k > r.val) r.right = node;
        else r.left = node;
    }

    public BinSearchNode insertRecur(BinSearchNode root, int k) {
        if (root == null) {
            return new BinSearchNode(k);
        }
        if (k < root.val) root.left = insertRecur(root.left, k);
        else if (k > root.val) root.right = insertRecur(root.right, k);
        return root;
    }

    public boolean search(int k) {
        if (root.val == k) return true;
        BinSearchNode cur = root;
        while (cur != null) {
            if (k < cur.val) cur = cur.left;
            else if (k > cur.val) cur = cur.right;
            else return true;
        }
        return false;
    }

    public void inorder(BinSearchNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }
}