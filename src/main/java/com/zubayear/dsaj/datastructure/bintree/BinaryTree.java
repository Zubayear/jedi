package com.zubayear.dsaj.datastructure.bintree;

import java.util.*;

public class BinaryTree<T> {
    public TreeNode<T> root;

    public TreeNode<T> buildTree(T[] preorder, T[] inorder) {
        return buildTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode<T> buildTree(T[] preorder, T[] inorder, int preStart, int inStart, int inEnd) {
        if (inStart > inEnd || preStart > preorder.length - 1) {
            return null;
        }
        // preorder's first node is root
        TreeNode<T> root = new TreeNode<>(preorder[preStart]);
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; ++i) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }
        root.left = buildTree(preorder, inorder, preStart + 1, inStart, inIndex - 1);
        // (inIndex - inStart) is size of roots left subtree
        // 7,6,9,3 root 5,8,2,1
        // to get to right portion we do preStart + inIndex - inStart + 1
        root.right = buildTree(preorder, inorder, preStart + inIndex - inStart + 1, inIndex + 1, inEnd);
        return root;
    }

    List<T> iterativePreorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) return result;
        // push root
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

