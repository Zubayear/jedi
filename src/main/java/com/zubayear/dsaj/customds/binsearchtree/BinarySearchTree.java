package com.zubayear.dsaj.customds.binsearchtree;

import com.zubayear.dsaj.customds.bintree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    public TreeNode<T> sortedArrayToBST(List<T> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return null;
        }
        return sortedArrayToBST(numbers, 0, numbers.size() - 1);
    }

    public TreeNode<T> sortedArrayToBST(List<T> numbers, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode<T> root = new TreeNode<>(numbers.get(mid));
        // 10,20,30,40,50,60
        // -----|   |-------
        // left     right
        root.left = sortedArrayToBST(numbers, left, mid - 1);
        root.right = sortedArrayToBST(numbers, mid + 1, right);
        return root;
    }

    // TC - O(H); log(n)<=h<=n
    public TreeNode<T> insertIntoBST(TreeNode<T> root, T val) {
        if (root == null) {
            return null;
        }
        TreeNode<T> tail = null;
        TreeNode<T> newNode = new TreeNode<>(val);
        while (root != null) {
            tail = root;
            if (root.val == val) {
                return root;
            } else if (val.compareTo(root.val) < 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        if (val.compareTo(tail.val) < 0) {
            tail.left = newNode;
        } else {
            tail.right = newNode;
        }
        return newNode;
    }


    public String preorder(TreeNode<T> root) {
        /*
         * when t is not null print and push it to stack then go to left
         * otherwise pop from stack and go to right
         */
        if (root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> t = root;
        result.append("[");
        while (t != null || !stack.isEmpty()) {
            if (t != null) {
                result.append(t.val).append(",");
                stack.offerFirst(t);
                t = t.left;
            } else {
                t = stack.pollFirst();
                t = t.right;
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    public String inorder(TreeNode<T> root) {
        if (root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> t = root;
        result.append("[");
        while (t != null || !stack.isEmpty()) {
            if (t != null) {
                stack.offerFirst(t);
                t = t.left;
            } else {
                t = stack.pollFirst();
                result.append(t.val).append(",");
                t = t.right;
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    public String postorder(TreeNode<T> root) {
        if (root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Deque<Object[]> stack = new ArrayDeque<>();
        TreeNode<T> t = root;
        result.append("[");
        while (t != null || !stack.isEmpty()) {
            if (t != null) {
                stack.offerFirst(new Object[]{t, "+"});
                t = t.left;
            } else {
                Object[] objects = stack.pollFirst();
                t = (TreeNode<T>) objects[0];
                if (objects[1] == "+") {
                    stack.offerFirst(new Object[]{t, "-"});
                    t = t.right;
                } else {
                    result.append(t.val).append(",");
                    t = null;
                }
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
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