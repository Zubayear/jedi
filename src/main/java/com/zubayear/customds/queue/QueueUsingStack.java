package com.zubayear.customds.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueUsingStack {
    private final Deque<Integer> stack1;
    private final Deque<Integer> stack2;
    private final Deque<Integer> inputStack; // for optimized version
    private final Deque<Integer> outputStack; // for optimized version

    public QueueUsingStack() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
        inputStack = new ArrayDeque<>();
        outputStack = new ArrayDeque<>();
    }

    /**
     * take elements from stack1 to stack2
     * push x to stack1
     * take elements from stack2 to stack1
     * @param x
     */
    public void enqueue(int x) {
        while (!stack1.isEmpty()) {
            stack2.offerLast(stack1.pollLast()); // add & remove from same end - stack
        }
        stack1.offer(x);
        while (!stack2.isEmpty()) {
            stack1.offerLast(stack2.pollLast());
        }
    }

    /**
     *
     * @return stack1.pop()
     */
    public int dequeue() {
        return stack1.pollLast();
    }

    public void print() {
        System.out.println(stack1);
    }

    /*
    * optimized version
    * */


    /**
     * add x to inputStack
     * @param x
     * @return
     */
    public boolean enqueueOpt(int x) {
        inputStack.offerFirst(x);
        return true;
    }

    /**
     * if outputStack isn't empty then poll from there
     * otherwise move inputStack elements to outputStack
     * then poll from outputStack
     * @return
     */
    public int dequeueOpt() {
        if (!outputStack.isEmpty()) {
            return outputStack.pollFirst();
        } else {
            while (!inputStack.isEmpty()) {
                outputStack.offerFirst(inputStack.pollFirst());
            }
            return outputStack.pollFirst();
        }
    }

    /**
     * if outputStack isn't empty then poll from there
     * otherwise move inputStack elements to outputStack
     * then poll from outputStack
     * @return
     */
    public int peekOpt() {
        if (!outputStack.isEmpty()) {
            return outputStack.peekFirst();
        } else {
            while (!inputStack.isEmpty()) {
                outputStack.offerFirst(inputStack.pollFirst());
            }
            return outputStack.peekFirst();
        }
    }

    public void printOpt() {
        System.out.println(outputStack);
    }

}
