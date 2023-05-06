package com.zubayear.customds.linkedlist;

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
}
