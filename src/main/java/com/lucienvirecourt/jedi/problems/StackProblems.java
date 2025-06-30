package com.lucienvirecourt.jedi.problems;

import java.util.*;

public class StackProblems {
  public static String removingStarsFromAString(String s) {
    int starCount = 0;
    StringBuilder result = new StringBuilder();
    for (int i = s.length() - 1; i >= 0; ) {
      char ch = s.charAt(i);
      if (ch == '*') {
        int j = i;
        while (j > 0 && s.charAt(j--) == '*') {
          starCount++;
        }
        i = i - starCount * 2;
      } else {
        starCount = 0;
        result.append(ch);
        --i;
      }
    }
    return result.reverse().toString();
  }

  public static Integer[] asteroidCollision(int[] asteroids) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (int asteroid : asteroids) {
      if (asteroid > 0) {
        stack.offerFirst(asteroid);
      } else {
        while (!stack.isEmpty() && stack.peekFirst() > 0 && stack.peekFirst() < -asteroid) {
          stack.pollFirst();
        }
        if (!stack.isEmpty() && stack.peekFirst() == -asteroid) {
          stack.pollFirst();
        } else if (stack.isEmpty() && asteroid < 0) {
          stack.offerFirst(asteroid);
        }
      }
    }
    List<Integer> result = new ArrayList<>();
    while (!stack.isEmpty()) {
      result.add(stack.pollLast());
    }
    return result.toArray(new Integer[0]);
  }

  public static int[] nextGreaterElement(int[] nums) {
    Deque<Integer> stack = new ArrayDeque<>();
    int n = nums.length;
    int[] result = new int[n];
    for (int i = n - 1; i >= 0; --i) {
      int val = nums[i];
      while (!stack.isEmpty() && val >= stack.peekFirst()) {
        stack.pollFirst();
      }
      if (stack.isEmpty()) result[i] = 0;
      else result[i] = stack.peekFirst();
      stack.offerFirst(val);
    }
    return result;
  }

  public static int[] dailyTemperatures(int[] temperatures) {
    Deque<Integer> stack = new ArrayDeque<>();
    int n = temperatures.length;
    int[] result = new int[n];
    for (int i = n - 1; i >= 0; --i) {
      while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peekFirst()]) {
        stack.pollFirst();
      }
      if (stack.isEmpty()) result[i] = 0;
      else result[i] = stack.peekFirst() - i;
      stack.offerFirst(i);
    }
    return result;
  }

  public static String decodeString(String str) {
    int n = str.length();
    Deque<String> stack = new ArrayDeque<>();
    StringBuilder sb = new StringBuilder();
    // 3[a2[c]]
    // a2[c]a2[c]a2[c]
    // 54[cba]
    // accaccacc
    // we push everything until we encounter ]
    // then we keep on popping value from the stack until we get [
    int num = 0;
    for (int i = 0; i < n; ++i) {
      char ch = str.charAt(i);
      if (ch >= '0' && ch <= '9') num = num * 10 + ch - '0';
      else if (ch == '[') {
        stack.push(num + "");
        stack.push("[");
        num = 0;
      } else if (ch == ']') {
        while (!Objects.equals(stack.peekFirst(), "[")) {
          sb.append(stack.pollFirst());
        }
        stack.pollFirst(); // pop [
        int count = Integer.parseInt(Objects.requireNonNull(stack.pollFirst()));
        sb.append(String.valueOf(sb).repeat(Math.max(0, count - 1)));
        stack.offerFirst(sb.toString());
      } else {
        stack.offerFirst(String.valueOf(ch));
      }
      sb.delete(0, sb.length());
    }
    sb.delete(0, sb.length());
    while (!stack.isEmpty()) sb.append(stack.pollFirst());
    return sb.reverse().toString();
  }
}
