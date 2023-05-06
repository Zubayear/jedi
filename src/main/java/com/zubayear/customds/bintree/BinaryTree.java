package com.zubayear.customds.bintree;

import java.util.*;

public class BinaryTree<T> {
    public TreeNode<T> root;

    public void insert(T val) {
        TreeNode<T> node = new TreeNode<>(val);
        // 1, 2, 3, 4, 5, 6
        if (root == null) {
            root = node;
            return;
        }
        Deque<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.poll();
            if (currentNode.left == null) {
                currentNode.left = node;
                return;
            }
            if (currentNode.right == null) {
                currentNode.right = node;
                return;
            }
            queue.offer(currentNode.left);
            queue.offer(currentNode.right);
        }
    }

    public List<List<T>> bfs(TreeNode<T> root) {
        List<List<T>> result = new ArrayList<>();
        Deque<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<T> tmpList = new ArrayList<>();
            for (int i = 0; i < queueSize; ++i) {
                TreeNode<T> currentNode = queue.poll();
                assert currentNode != null;
                tmpList.add(currentNode.val);
                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            result.add(tmpList);
        }
        return result;
    }

    public List<T> dfs(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        dfsHelper(root, result);
        return result;
    }

    private void dfsHelper(TreeNode<T> root, List<T> result) {
        if (root == null) return;
        // add the root value to result
        result.add(root.val);
        // visit left and then visit right subtree
        dfsHelper(root.left, result);
        dfsHelper(root.right, result);
    }
}
