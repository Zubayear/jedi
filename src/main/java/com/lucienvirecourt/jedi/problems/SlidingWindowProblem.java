package com.lucienvirecourt.jedi.problems;

import java.util.*;

public class SlidingWindowProblem {
  public int[] maxSlidingWindow(int[] nums, int k) {
    int ws = 0, we = 0, size = nums.length;
    if (k > size) return new int[]{Arrays.stream(nums).max().getAsInt()};
    int[] result = new int[size - k + 1];
    Deque<Integer> q = new LinkedList<>();
    while (we < size) {
      // calculation
      // before insert remove value < nums[we]
      // elements < nums[we] won't be of any use so we remove them before pushing nums[we]
      while (!q.isEmpty() && q.peekLast() < nums[we]) q.pollLast();
      q.add(nums[we]);
      if (we - ws + 1 < k) we++;

      else if (we - ws + 1 == k) {
        // ans from calculation
        Integer peek = q.peek();
        result[ws] = peek;
        // slide the window
        // we have to remove the calculation
        if (peek == nums[ws]) q.pollFirst();
        ws++;
        we++;
      }
    }
    return result;
  }

  public int[] firstNegative(int[] nums, int k) {
    int traveler = 0, left = 0, n = nums.length;
    int[] result = new int[n - k + 1];
    Deque<Integer> queue = new ArrayDeque<>();
    while (traveler < n) {
      // calculation
      if (nums[traveler] < 0) {
        queue.offer(nums[traveler]);
      }
      if (traveler - left + 1 < k) {
        traveler++;
      } else if (traveler - left + 1 == k) {
        // get answer from calculation
        Integer val = Objects.requireNonNullElse(queue.peek(), 0);
        result[left] = val;
        if (nums[left] == val) {
          queue.poll();
        }
        // slide window
        left++;
        traveler++;
      }

    }
    return result;
  }

  public int longestSubstringKDistinct(String s, int k) {
    int result = -1, traveler = 0, left = 0;
    Map<Character, Integer> map = new HashMap<>();
    while (traveler < s.length()) {
      map.put(s.charAt(traveler), map.getOrDefault(s.charAt(traveler), 0) + 1);
      if (map.size() < k) {
        traveler++;
      } else if (map.size() == k) {
        result = Math.max(result, (traveler - left + 1));
        traveler++;
      } else {
        while (map.size() != k) {
          char ch = s.charAt(left);
          if (map.containsKey(ch)) {
            map.put(ch, map.get(ch) - 1);
            if (map.get(ch) == 0) {
              map.remove(ch);
            }
            left++;
          }
        }
        traveler++;
      }
    }
    return result;
  }

  public static int lengthOfLongestSubstring(String s) {
    // cadbzabcd
    int maxLen = 0, n = s.length(), left = 0, right = 0, windowLen;
    Map<Character, Integer> map = new HashMap<>();
    while (right < n) {
      char ch = s.charAt(right);
      if (map.containsKey(ch)) {
        if (map.get(ch) >= left) {
          left = map.get(ch) + 1;
        }
      }
      windowLen = right - left + 1;
      maxLen = Math.max(maxLen, windowLen);
      map.put(ch, right);
      right++;
    }
    return maxLen;
  }
}

