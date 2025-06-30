package com.lucienvirecourt.jedi.datastructure.stack;

import java.util.*;

public class MinStack {
  private final Deque<int[]> stack;

  public MinStack() {
    this.stack = new ArrayDeque<>();
  }

  public void push(int val) {
    if (stack.isEmpty()) {
      stack.offerFirst(new int[]{val, val});
      return;
    }
    int[] top = stack.peekFirst();

    stack.offerFirst(new int[]{val, Math.min(top[1], val)});
  }

  public void pop() {
    stack.pollFirst();
  }

  public int top() {
    assert stack.peekFirst() != null;
    return stack.peekFirst()[0];
  }

  public int getMin() {
    return Objects.requireNonNull(stack.pollFirst())[1];
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
