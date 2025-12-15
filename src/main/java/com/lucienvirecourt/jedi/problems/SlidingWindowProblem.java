package com.lucienvirecourt.jedi.problems;

import java.util.*;

public class SlidingWindowProblem {
  public int[] maxSlidingWindow(int[] nums, int k) {
    int ws = 0, we = 0, size = nums.length;
    int max = -(int) 1e9;

    if (k > size) {
      for (int n : nums) max = Math.max(max, n);
      return new int[]{max};
    }
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

  public int lengthOfLongestSubstring(String s) {
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

  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int n = s.length(), l = 0, r = 0, ans = 0;
    Map<Character, Integer> map = new HashMap<>();
    while (r < n) {
      char ch = s.charAt(r);
      map.put(ch, map.getOrDefault(ch, 0) + 1);
      if (map.size() > k) {
        char key = s.charAt(l);
        if (map.get(key) == 1) {
          map.remove(key);
        } else {
          map.computeIfPresent(key, (_, v) -> v - 1);
        }
        l++;
      }
      ans = Math.max(ans, r - l + 1);
      r++;
    }
    return ans;
  }

  public int numberOfStrings(String s) {
    // try to find the minimum window having a,b,c
    // get the min idx of the window and add 1 to count the ans we can form
    // e.g., abcabc at idx = 2 we can form a minimum window having a,b.c so the len will be min index out of (a,b,c)+1
    // at idx = 3 the len will be min idx out of a,b,c and add 1
    int a = -1, b = -1, c = -1, n = s.length(), ans = 0;
    for (int i = 0; i < n; ++i) {
      char ch = s.charAt(i);
      if (ch == 'a') a = i;
      else if (ch == 'b') b = i;
      else c = i;
      if (a != -1 && b != -1 && c != -1) ans += Math.min(a, Math.min(b, c)) + 1;
    }
    return ans;
  }

  public int characterReplacement(String s, int k) {
    // if windowSize - maxFreq <= k, then we can expand the window
    // AAABBCCD, k = 2 for this, when we at 5th index 6-3 <= 2 so, we need to shrink
    int n = s.length(), l = 0, r = 0, maxLen = 0, maxFreq = 0;
    int[] map = new int[26];
    while (r < n) {
      char ch = s.charAt(r);
      map[ch - 'A']++;
      maxFreq = Math.max(maxFreq, map[ch - 'A']);
      while (r - l + 1 - maxFreq > k) { // we can just replace while with if too. think about it
        char key = s.charAt(l);
        map[key - 'A']--;
        maxFreq = 0;
        for (int i = 0; i < 26; ++i) {
          maxFreq = Math.max(maxFreq, map[i]);
        }
        l++;
      }
      if (r - l + 1 - maxFreq <= k) maxLen = Math.max(maxLen, r - l + 1);
      r++;
    }
    return maxLen;
  }

  public int numSubarraysWithSum(int[] nums, int goal) {
    // we will try to find sum <= goal, then find sum <= goal-1
    int a = findNumSubarraysWithSum(nums, goal);
    int b = findNumSubarraysWithSum(nums, goal - 1);
    return a - b;
  }

  private int findNumSubarraysWithSum(int[] nums, int goal) {
    if (goal < 0) return 0;
    int l = 0, r = 0, n = nums.length, sum = 0, count = 0;
    while (r < n) {
      sum += nums[r];
      while (sum > goal) {
        sum -= nums[l];
        l++;
      }
      count += r - l + 1;
      r++;
    }
    return count;
  }
}

