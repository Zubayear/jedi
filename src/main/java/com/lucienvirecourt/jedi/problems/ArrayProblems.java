package com.lucienvirecourt.jedi.problems;

import java.util.*;

public class ArrayProblems {

  public int[] replaceElements(int[] arr) {
    int runningMax = -1, i = arr.length - 1, t;
    while (i >= 0) {
      t = arr[i];
      arr[i] = runningMax;
      runningMax = Math.max(runningMax, t);
      i--;
    }
    return arr;
  }

  public String longestCommonPrefix(String[] strs) {
    Arrays.sort(strs);
    String s1 = strs[0], s2 = strs[strs.length - 1];
    int i = 0;
    while (i < s1.length() && i < s2.length()) {
      if (s1.charAt(i) == s2.charAt(i)) {
        i++;
      } else {
        break;
      }
    }
    return s1.substring(0, i);

  }

  public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> result = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();
    if (strs == null || strs.length == 0) {
      return result;
    }
    StringBuilder sb = new StringBuilder();
    for (String str : strs) {
      int[] keyCounter = new int[26];
      for (char ch : str.toCharArray()) {
        keyCounter[ch - 'a']++;
      }
      sb.append(Arrays.toString(keyCounter));
      String key = sb.toString();
      sb.setLength(0);
      map.computeIfAbsent(key, _ -> new ArrayList<>());
      map.get(key).add(str);
    }

    result.addAll(map.values());
    return result;
  }

  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    int count = 0;
    for (int i = 0; i < flowerbed.length; ++i) {
      if (flowerbed[i] == 0) {
        if (i == 0 || flowerbed[i - 1] == 0 && i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
          flowerbed[i] = 1;
          count++;
        }
      }
    }
    return count >= n;
  }

  public boolean wordPattern(String pattern, String s) {
    String[] words = s.split(" ");
    if (words.length != pattern.length()) {
      return false;
    }
    Map<Object, Integer> map = new HashMap<>();
    for (int i = 0; i < words.length; ++i) {
      // << (a,0) >> -> returns 0
      // << (dot, 0) >> ->> returns 0
      if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(words[i], i))) {
        return false;
      }
    }
    return true;
  }

  public int[] topKFrequent(int[] nums, int k) {
    int[] result = new int[k];
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    for (var m : map.entrySet()) {
      minHeap.offer(new int[]{m.getValue(), m.getKey()});
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }
    int i = 0;
    while (!minHeap.isEmpty()) {
      int[] top = minHeap.poll();
      result[i] = top[1];
      i++;
    }
    return result;
  }

  public int secondMax(int[] nums) {
    int largest = nums[0], secondMax = -1;
    int n = nums.length;
    for (int i = 1; i < n; ++i) {
      if (nums[i] > largest) {
        secondMax = largest; // 4
        largest = nums[i]; // 7
      } else if (nums[i] < largest && nums[i] > secondMax) {
        secondMax = nums[i];
      }
    }
    return secondMax;
  }

  public int removeDuplicates(int[] nums) {
    int pt1 = 0, pt2 = 1, n = nums.length, ans = 1;
    while (pt2 < n) {
      if (nums[pt1] != nums[pt2]) {
        nums[++pt1] = nums[pt2++];
        ans++;
      } else {
        pt2++;
      }
    }
    return ans;
  }

  public int longestSubarrayWithSumK(int[] nums, int k) {
    int sum = 0, maxLen = 0, n = nums.length;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < n; ++i) {
      sum += nums[i];
      if (sum == k) {
        maxLen = Math.max(maxLen, i + 1);
      }
      int rem = sum - k;
      if (map.containsKey(rem)) {
        int len = i - map.get(rem);
        maxLen = Math.max(maxLen, len);
      }
      if (!map.containsKey(sum)) map.put(sum, i);
    }
    return maxLen;
  }

  public boolean isValidSubsequence(int[] nums, int[] sequence) {
    int m = nums.length, n = sequence.length, i = 0, j = 0;
    while (i < m && j < n) {
      if (nums[i] == sequence[j]) j++;
      i++;
    }
    return j == n;
  }

  public int smallestDifference(int[] A, int[] B) {
    Arrays.sort(A);
    Arrays.sort(B);
    int i = 0, j = 0, m = A.length, n = B.length, minDiff = (int) 1e9;
    while (i < m && j < n) {
      minDiff = Math.min(minDiff, Math.abs(A[i] - B[j]));
      // since both are sorted, we would want to move i when it's A[i] < B[j]
      // had we moved j instead of i, we might have a worse difference
      // since the right of the array is increasing
      if (A[i] < B[j]) i++;
      else j++;
    }
    return minDiff;
  }

  public void moveElementToEnd(int[] A, int t) {
    // keep count of t
    // when we find something other than t we swap that with A[t-tc]
    // [0,1,0,3,12]
    // at index 1 we see value 1 so we swap with A[1-1]
    int tc = 0, n = A.length;
    for (int i = 0; i < n; ++i) {
      if (A[i] == t) tc++;
      else swap(A, i, tc);
    }
  }

  public boolean isMonotonic(int[] nums) {
    boolean inc = false, dec = false;
    int n = nums.length;
    for (int i = 1; i < n; ++i) {
      if (nums[i] > nums[i - 1]) inc = true;
      if (nums[i] < nums[i - 1]) dec = true;
    }
    return inc && dec ? false : true;
  }

  public int[] productExceptSelf(int[] nums) {
    int n = nums.length, mul = 1;
    int[] result = new int[n];
    for (int i = 0; i < n; ++i) {
      result[i] = mul;
      mul *= nums[i];
    }
    mul = 1;
    for (int i = n - 1; i >= 0; --i) {
      result[i] *= mul;
      mul *= nums[i];
    }
    return result;
  }

  // LC287
  public int firstDuplicateValue(int[] A) {
    // since we have 1.n numbers in A,
    // we can apply an A[i]-1 trick to make the value of an index to negative,
    // then, if we find that already seen negative, we found the ans
    int n = A.length;
    for (int i = 0; i < n; ++i) {
      int idx = Math.abs(A[i]) - 1;
      if (A[idx] < 0) return Math.abs(A[i]);
      A[idx] *= -1;
    }
    return 0;
  }

  class EncodeDecodeString {
    public String encode(List<String> str) {
      StringBuilder sb = new StringBuilder();
      for (String s : str) {
        // 4#leet4#code
        sb.append(s.length()).append("#").append(s);
      }
      return sb.toString();
      // return String.join(", ", str);
    }

    public List<String> decode(String str) {
      // 4#leet4#code
      List<String> result = new ArrayList<>();
      int i = 0, n = str.length();
      while (i < n) {
        int j = i;
        // 4#neet5#killl
        //  j
        while (str.charAt(j) != '#') j++;
        int len = Integer.parseInt(str.substring(i, j));
        i = j + 1 + len;
        result.add(str.substring(j + 1, i));
      }
      return result;
      // return List.of(str.split(", "));
    }
  }

  public boolean isValidSudoku(char[][] board) {
    Set<String> set = new HashSet<>();
    // we keep each entry seen before in the row,col,box position in a hash set.
    // if we see the same string again, we can safely return false
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        char ch = board[i][j];
        if (ch != '.') {
          if (!set.add(ch + " in row " + i) ||
            !set.add(ch + " in col " + j) ||
            !set.add(ch + " in box " + i / 3 + "-" + j / 3)) {
          }
        }
      }
    }
    return true;
  }

  private void swap(int[] A, int a, int b) {
    int t = A[a];
    A[a] = A[b];
    A[b] = t;
  }
}
