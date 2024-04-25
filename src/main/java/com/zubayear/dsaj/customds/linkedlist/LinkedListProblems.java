package com.zubayear.dsaj.customds.linkedlist;

public class LinkedListProblems {
    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        // 2 ->4->3
        // 5->6->4
        ListNode<Integer> first = l1, second = l2, cur = new ListNode<>(-1);
        ListNode<Integer> dummyNode = cur;
        int carry = 0;
        while (first != null || second != null) {
            int total = 0;
            if (first != null) {
                total += first.val;
            }
            if (second != null) {
                total += second.val;
            }
            total += carry;
            int value = total % 10;
            carry = total / 10;
            cur.next = new ListNode<>(value);
            cur = cur.next;
            if (first != null) {
                first =  first.next;
            }
            if (second != null) {
                second = second.next;
            }
        }
        if (carry > 0) {
            cur.next = new ListNode<>(carry);
        }
        return dummyNode.next;

    }
}
