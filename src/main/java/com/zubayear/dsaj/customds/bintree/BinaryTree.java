package com.zubayear.dsaj.customds.bintree;

import java.util.*;

public class BinaryTree<T> {
    public TreeNode<T> root;

    public void insert(T val) {
        TreeNode<T> node = new TreeNode<>(val);
        // 1, 2, 3, 4, 5, 6
        // 3,9,20,null,null,15,7
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

    TreeNode<T> createBinaryTree(Integer[] nums) {
        return createBinaryTreeHelper(nums, 0);
    }

    private TreeNode<T> createBinaryTreeHelper(Integer[] array, int index) {
        if (index >= array.length || array[index] == null) return null;
        TreeNode<T> root = new TreeNode<>((T) array[index]);
        root.left = createBinaryTreeHelper(array, 2*index+1);
        root.right = createBinaryTreeHelper(array, 2*index+2);
        return root;
    }

    public List<List<T>> _bfs(TreeNode<T> root) {
        List<List<T>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode<T>> q = new ArrayDeque<>();
        q.offerFirst(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<T> tmp = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode<T> node = q.pollLast();
                if (node.left != null) q.offerFirst(node.left);
                if (node.right != null) q.offerFirst(node.right);
                tmp.add(node.val);
            }
            result.add(tmp);
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
            if (node.right != null) stack.offerFirst(node.right);
            if (node.left != null) stack.offerFirst(node.left);
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
                if (stack.isEmpty()) break;
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
            if (node.left != null) stack1.offerFirst(node.left);
            if (node.right != null) stack1.offerFirst(node.right);
        }
        while (!stack2.isEmpty()) {
            TreeNode<T> node = stack2.pollFirst();
            result.add(node.val);
        }
        return result;
    }

    int maxDepth(TreeNode<T> root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    boolean isBalanced(TreeNode<T> root) {
        if (root == null) return true;
        return isBalancedHelper(root) != -1;
    }

    private int isBalancedHelper(TreeNode<T> root) {
        if (root == null) return 0;
        int lh = isBalancedHelper(root.left);
        int rh = isBalancedHelper(root.right);
        if (lh == - 1 || rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    int diameterOfBinaryTree(TreeNode<T> root) {
        int[] diameter = new int[1];
        diameterOfBinaryTreeHelper(root, diameter);
        return diameter[0];
    }

    private int diameterOfBinaryTreeHelper(TreeNode<T> root, int[] diameter) {
        if (root == null) return 0;
        int lh = diameterOfBinaryTreeHelper(root.left, diameter);
        int rh = diameterOfBinaryTreeHelper(root.right, diameter);
        diameter[0] = Math.max(diameter[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }

    int maxPathSum(TreeNode<T> root) {
        if (root == null) return 0;
        int[] sum = new int[1];
        sum[0] = Integer.MIN_VALUE;
        maxPathSum(root, sum);
        return sum[0];
    }

    private int maxPathSum(TreeNode<T> root, int[] sum) {
        if (root == null) return 0;
        int lsum = Math.max(0, maxPathSum(root.left, sum));
        int rsum = Math.max(0, maxPathSum(root.right, sum));
        sum[0] = Math.max(sum[0], (int)root.val + lsum + rsum);
        return (int)root.val + Math.max(lsum, rsum); // which path will yield max value
    }

    List<List<Integer>> zigzagLevelOrder(TreeNode<T> root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode<T>> nodeDeque = new ArrayDeque<>();
        nodeDeque.offer(root);
        boolean leftToRight = true;
        while (!nodeDeque.isEmpty()) {
            int size = nodeDeque.size();
            List<Integer> tmp = new LinkedList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode<T> node = nodeDeque.poll();
                if (node.left != null) nodeDeque.offer(node.left);
                if (node.right != null) nodeDeque.offer(node.right);
                if (leftToRight) tmp.add((int)node.val);
                else tmp.add(0, (int)node.val);
            }
            leftToRight = !leftToRight;
            result.add(tmp);
        }
        return result;
    }

    List<Integer> boundaryOfBinaryTree(TreeNode<T> root) {
        List<Integer> result = new ArrayList<>();
        if (!isLeaf(root)) result.add((int)root.val);
        getLeftBoundary(root, result);
        getLeaves(root, result);
        getRightBoundary(root, result);
        return result;
    }

    private void getLeftBoundary(TreeNode<T> node, List<Integer> result) {
        /*
        * get left boundary w/o leaves
        * */
        TreeNode<T> curr = node.left;
        while (curr != null) {
            if (!isLeaf(curr)) result.add((int)curr.val);
            // go to left
            if (curr.left != null) curr = curr.left;
            else curr = curr.right; // if current is null we go right
        }
    }

    private boolean isLeaf(TreeNode<T> node) {
        return node.left == null && node.right == null;
    }

    private void getLeaves(TreeNode<T> node, List<Integer> result) {
        /*
        * get leaves with preorder traversal
        * */
        if (isLeaf(node)) {
            result.add((int) node.val);
            return;
        }
        if (node.left != null) getLeaves(node.left, result);
        if (node.right != null) getLeaves(node.right, result);
    }

    private void getRightBoundary(TreeNode<T> node, List<Integer> result) {
        /*
        * get right boundary w/o leaves
        * */
        TreeNode<T> curr = node.right;
        List<Integer> tmp = new ArrayList<>();
        while (curr != null) {
            if (!isLeaf(curr)) tmp.add((int) curr.val);
            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        for (int i = tmp.size() - 1; i >= 0; --i) result.add(tmp.get(i));
    }
}

