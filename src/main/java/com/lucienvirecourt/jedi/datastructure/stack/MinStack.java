package com.lucienvirecourt.jedi.datastructure.stack;

import java.util.*;

public class MinStack<T extends Comparable<T>> {
  private final Deque<List<T>> stack;

  public MinStack() {
    this.stack = new ArrayDeque<>();
  }

  public void push(T val) {
    if (stack.isEmpty()) {
      stack.offerFirst(Arrays.asList(val, val));
      return;
    }
    List<T> top = stack.peekFirst();
    T currentMin = top.get(1);

    T newMin = (val.compareTo(currentMin) < 0) ? val : currentMin;

    stack.offerFirst(Arrays.asList(val, newMin));
  }

  public void pop() {
    stack.pollFirst();
  }

  public T getTop() {
    assert stack.peekFirst() != null;
    return stack.peekFirst().getFirst();
  }

  public T getMin() {
    return Objects.requireNonNull(stack.peekFirst()).get(1);
  }

  @Override
  public String toString() {
    return "MinStack{" +
      "stack=" + Arrays.deepToString(stack.toArray()) +
      '}';
  }
}

class MinStackOptimal {
  private final Deque<Integer> stack;
  private int min = (int) 1e9;

  public MinStackOptimal() {
    this.stack = new ArrayDeque<>();
  }

  public void push(int val) {
    if (stack.isEmpty()) {
      min = val;
      stack.offerFirst(val);
    } else {
      if (val > min) stack.push(val);
      else {
        // we keep the modified value at the top when it's smaller than min
        stack.push(2 * val - min);
        min = val;
      }
    }
  }

  public void pop() {
    if (stack.isEmpty()) throw new EmptyStackException();
    int item = stack.pollFirst();
    // if this is a modified value, then change min
    if (item < min) min = 2 * min - item;
  }

  public int top() {
    assert stack.peekFirst() != null;
    int item = stack.peekFirst();
    if (item < min) return min;
    return item;
  }

  public int getMin() {
    return min;
  }
}
