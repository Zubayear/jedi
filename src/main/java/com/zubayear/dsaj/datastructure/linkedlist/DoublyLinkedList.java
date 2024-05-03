package com.zubayear.dsaj.datastructure.linkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        throwExceptionWhenEmpty();

        ListNode<T> traveler = head;
        while (traveler != null) {
            ListNode<T> next = traveler.next;
            traveler.next = traveler.prev = null;
            traveler.val = null;
            traveler = next;
        }
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T elem) {
        addLast(elem);
    }

    private void throwExceptionWhenEmpty() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }
    }

    // add at tail - O(1)
    public void addLast(T elem) {
        if (isEmpty()) {
            addAtEmptyList(elem);
            return;
        }
        ListNode<T> node = new ListNode<>(elem);
        node.prev = tail;
        tail.next = node;
        tail = node;

        size++;
    }

    // add at head - O(1)
    public void addFirst(T elem) {
        if (isEmpty()) {
            addAtEmptyList(elem);
            return;
        }
        ListNode<T> node = new ListNode<>(elem);
        node.next = head;
        node.prev = head.prev;
        head = node;
        size++;
    }

    // O(1)
    private void addAtEmptyList(T elem) {
        ListNode<T> node = new ListNode<>(elem);
        head = tail = node;
        size++;
    }

    // O(n)
    public void addAt(int idx, T elem) {
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (idx == 0) {
            addFirst(elem);
            return;
        }
        if (idx == size) {
            addLast(elem);
            return;
        }
        ListNode<T> traveler = head;
        int prevIdx = 0;
        while (traveler != null) {
            if (prevIdx == idx - 2) {
                ListNode<T> node = new ListNode<>(elem);
                node.next = traveler.next;
                node.prev = traveler;
                traveler.next.prev = node;
                traveler.next = node;
            }
            prevIdx++;
            traveler = traveler.next;
        }
        size++;
    }

    // O(1)
    public T peekFirst() {
        throwExceptionWhenEmpty();
        return head.val;
    }

    // O(1)
    public T peekLast() {
        throwExceptionWhenEmpty();
        return tail.val;
    }

    // O(1)
    public T removeFirst() {
        throwExceptionWhenEmpty();

        T val = head.val;
        head = head.next;
        --size;

        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }

        return val;
    }

    // O(1)
    public T removeLast() {
        throwExceptionWhenEmpty();

        T val = tail.val;
        tail = tail.prev;
        --size;

        if (isEmpty()) {
            head = null; // if empty then tail is already null so make head null too
        } else {
            tail.next = null;
        }
        return val;
    }

    // O(n)
    public T removeAt(int idx) {
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (idx == 1) {
            return removeFirst();
        }
        if (idx == size) {
            return removeLast();
        }
        int prevIdx = 0;
        ListNode<T> traveler = head;
        while (traveler != null) {
            if (prevIdx == idx - 2) {
                ListNode<T> nodeToRemove = traveler.next;
                T val = nodeToRemove.val;
                traveler.next = nodeToRemove.next;
                nodeToRemove.next.prev = traveler;
                nodeToRemove.next = null;
                nodeToRemove.prev = null;
                --size;
                return val;
            }
            prevIdx++;
            traveler = traveler.next;
        }
        return null;
    }

    public boolean remove(T elem) {
        int idx = indexOf(elem);
        if (idx == -1) {
            return false;
        } else {
            return removeAt(idx) != null;
        }
    }

    public int indexOf(T elem) {
        int idx = 1;
        ListNode<T> traveler = head;
        while (traveler != null) {
            if (traveler.val == elem) {
                return idx;
            }
            idx++;
            traveler = traveler.next;
        }
        return -1;
    }

    public boolean contains(T elem) {
        return indexOf(elem) != -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode<T> traveler = head;
        while (traveler != null) {
            sb.append(traveler.val).append(",");
            traveler = traveler.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            ListNode<T> traveler = head;

            @Override
            public boolean hasNext() {
                return traveler != null;
            }

            @Override
            public T next() {
                T val = traveler.val;
                traveler = traveler.next;
                return val;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
