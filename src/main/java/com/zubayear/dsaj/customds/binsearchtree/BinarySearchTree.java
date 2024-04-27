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

    public TreeNode<T> insertRecursive(TreeNode<T> root, T val) {
        if (root == null) {
            return new TreeNode<>(val);
        }
        if (val.compareTo(root.val) < 0) {
            root.left = insertRecursive(root.left, val);
        } else {
            root.right = insertRecursive(root.right, val);
        }
        return root;
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
            // if the p.val is less than then go to left
            if (numbers[i].compareTo(p.val) < 0) {
                TreeNode<T> newNode = new TreeNode<>(numbers[i++]);
                p.left = newNode;
                stack.offerFirst(p);
                p = newNode;
            } else {
                // in this case check if the value is between p.val and node at the top of the stack
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

    public boolean isValidBST(TreeNode<T> root, T left, T right) {
        if (root == null) {
            return true;
        }
        return isValidBSTHelper(root, left, right);
    }

    private boolean isValidBSTHelper(TreeNode<T> node, T left, T right) {
        // For large test cases we need to use long for the boundary value
        if (node == null) {
            return true;
        }
        // -inf < val < right
        if (left.compareTo(node.val) > 0 || right.compareTo(node.val) < 0) {
            return false;
        }
        var leftSubtree = isValidBSTHelper(node.left, left, node.val);
        var rightSubtree = isValidBSTHelper(node.right, node.val, right);
        return leftSubtree && rightSubtree;
    }

    public TreeNode<T> searchInBST(TreeNode<T> root, T key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.val) < 0) {
            return searchInBST(root.left, key);
        } else {
            return searchInBST(root.right, key);
        }
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