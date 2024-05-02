package com.zubayear.dsaj.datastructure.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class DoublyLinkedList<T> {
    protected ListNode<T> head;
    protected ListNode<T> tail;

    public DoublyLinkedList(T val) {
        head = new ListNode<>(val);
        tail = new ListNode<>(val);
        head.next = tail;
        tail.prev = head;
    }

    public void insertAtHead(T val) {
        ListNode<T> node = new ListNode<>(val);
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public void insertAtTail(T val) {
        ListNode<T> node = new ListNode<>(val);
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    public void printList() {
        ListNode<T> currentNode = head.next;
        while (currentNode.next != null) {
            System.out.print(currentNode.val + " -> ");
            currentNode = currentNode.next;
        }
        System.out.print("null");
    }

    public List<T> getList(boolean forward) {
        List<T> result = new LinkedList<>();
        ListNode<T> currentNode;
        if (forward) {
            currentNode = head.next;
            while (currentNode.next != null) {
                result.add(currentNode.val);
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail.prev;
            while (currentNode.prev != null) {
                result.add(currentNode.val);
                currentNode = currentNode.prev;
            }
        }
        return result;
    }

    public void reversePrint() {
        ListNode<T> currentNode = tail.prev;
        while (currentNode.prev != null) {
            System.out.print(currentNode.val + " -> ");
            currentNode = currentNode.prev;
        }
        System.out.print("null");
    }

    public boolean delete(T val) {
        ListNode<T> currentNode = head.next;
        while (currentNode.next != null) {
            if (currentNode.val == val) {
                ListNode<T> prevNode = currentNode.prev;
                ListNode<T> nextNode = currentNode.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }
}
