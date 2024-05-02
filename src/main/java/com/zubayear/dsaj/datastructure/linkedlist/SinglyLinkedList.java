package com.zubayear.dsaj.datastructure.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class SinglyLinkedList<T> {
    protected ListNode<T> head;
    protected ListNode<T> tail;

    public void insertAtHead(T val) {
        ListNode<T> node = new ListNode<>(val);
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public void printList() {
        ListNode<T> currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.val + " -> ");
            currentNode = currentNode.next;
        }
        System.out.print("null");
    }

    public List<T> getList() {
        List<T> result = new LinkedList<>();
        ListNode<T> currentNode = head;
        while (currentNode != null) {
            result.add(currentNode.val);
            currentNode = currentNode.next;
        }
        return result;
    }

    public void insertAtTail(T val) {
        ListNode<T> node = new ListNode<>(val);
        if (head == null && tail == null) {
            head = tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public void insertAt(T val, int pos) {
        ListNode<T> node = new ListNode<>(val), currentNode = head;
        int idx = 0;
        while (currentNode != null) {
            if ((pos - idx) == 1) {
                node.next = currentNode.next;
                currentNode.next = node;
                return;
            }
            ++idx;
            currentNode = currentNode.next;
        }
    }

    public boolean delete(T val) {
        ListNode<T> dummyNode = head, currentNode = head.next;
        while (currentNode != null) {
            if (currentNode.val == val) {
                dummyNode.next = currentNode.next;
                currentNode.next = null;
                return true;
            }
            currentNode = currentNode.next;
            dummyNode = dummyNode.next;
        }
        return false;
    }

    public void reverse() {
        // 1 -> 2 -> 3 -> 4 -> 5 -> null
        // take 3 pointers and slide them and reverse
        ListNode<T> p = head, q = null, r = null;
        while (p != null) {
            // slide 3 pointers
            r = q;
            q = p;
            p = p.next;
            q.next = r;
        }
        head = q;
    }

    public void recursiveReverse() {
        recursiveReverseHelper(null, head);
    }

    public void recursiveReverseHelper(ListNode<T> q, ListNode<T> p) {
        if (p != null) {
            // moving forward
            recursiveReverseHelper(p, p.next);
            // at return time just join the link from p to q
            p.next = q;
        } else {
            // shift head to q
            // 1 -> 2 -> 3 -> 4 -> 5 -> null
            //                     q    p
            head = q;
        }
    }
}
