package com.lucienvirecourt.jedi.datastructure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {
  private final List<T> heap;

  public BinaryHeap() {
    this(1);
  }

  public BinaryHeap(int size) {
    heap = new ArrayList<>(size);
  }

  public BinaryHeap(T[] arr) {
    int heapSize = arr.length;
    heap = new ArrayList<>(heapSize);

    heap.addAll(Arrays.asList(arr).subList(0, heapSize));

    for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
      sink(i);
    }
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void clear() {
    heap.clear();
  }

  public int size() {
    return heap.size();
  }

  public T peek() {
    if (heap.isEmpty()) {
      return null;
    }
    return heap.get(0);
  }

  // O(log(n))
  // always delete top
  public T poll() {
    return removeAt(0);
  }

  // O(n)
  public boolean contains(T t) {
    for (int i = 0; i < size(); i++) {
      if (heap.get(i).equals(t)) {
        return true;
      }
    }
    return false;
  }

  // O(log(n)) - each insert
  public void add(T t) {
    if (t == null) {
      throw new IllegalArgumentException("provided null");
    }
    heap.add(t);
    int indexOfLastEntry = size() - 1;
    swim(indexOfLastEntry);
  }

  private boolean less(int i, int j) {
    T node1 = heap.get(i), node2 = heap.get(j);
    return node1.compareTo(node2) <= 0;
  }

  private void swap(int i, int j) {
    T val1 = heap.get(i), val2 = heap.get(j);
    heap.set(i, val2);
    heap.set(j, val1);
  }

  // bottom up node swim - O(log(n))
  private void swim(int k) {
    if (k < 0) {
      throw new IllegalArgumentException("invalid index");
    }
    int parent = (k - 1) / 2;
    while (k > 0 && less(k, parent)) {
      swap(k, parent);
      k = parent;
      parent = (k - 1) / 2;
    }
  }

  // heapify - O(n)
  private void sink(int k) {
    int heapSize = size();

    while (true) {
      int left = 2 * k + 1;
      int right = 2 * k + 2;
      int smallest = left;

      // find the smaller child
      if (right < heapSize && less(right, left)) {
        smallest = right;
      }

      if (left >= heapSize || less(k, smallest)) {
        break;
      }

      swap(smallest, k);
      k = smallest;
    }
  }

  private T removeAt(int i) {
    if (isEmpty()) {
      return null;
    }
    int indexOfLastElement = size() - 1;
    T replacement = heap.get(i);
    T lastElement = heap.get(indexOfLastElement);
    heap.set(0, lastElement);
    heap.remove(indexOfLastElement);
    int j = 0, k = 2 * (j + 1);
    while (k < size() - 1) {
      if (heap.get(k + 1).compareTo(heap.get(k)) < 0) {
        k = k + 1;
      }
      if (heap.get(k).compareTo(heap.get(j)) < 0) {
        swap(j, k);
        j = k;
        k = 2 * k;
      } else {
        break;
      }
    }

    return replacement;
  }

  @Override
  public String toString() {
    return heap.toString();
  }
}
