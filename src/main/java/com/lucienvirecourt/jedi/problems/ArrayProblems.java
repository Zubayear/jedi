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
    // when we sort, we get the lexicographically lowest string and the highest string,
    // so, if we find the common part between these two, we will get the answer
    // [flower, flow, flight]
    // getting the common part of flower and flight is enough
    Arrays.sort(strs);
    String s1 = strs[0], s2 = strs[strs.length - 1];
    int i = 0, m = s1.length(), n = s2.length();
    while (i < m && i < n) {
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
    return !inc || !dec;
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

  static class EncodeDecodeString {
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

  public int numRescueBoats(int[] people, int limit) {
    // we will put light and heavy people on the same boat,
    // so if we sort, we will have lowest in the left and highest in the right
    // first we sort the input i.e., [1,2,2,3] and then we use two pointers
    Arrays.sort(people);
    int l = 0, r = people.length - 1, count = 0;
    while (l <= r) {
      int sum = people[l] + people[r];
      // light and heavy people can go on the same boat
      if (sum <= limit) {
        l++;
      }
      r--;
      count++;
    }
    return count;
  }

  public int maxDifference(String s) {
    int[] frequency = new int[26];
    int max = -(int) 1e9, min = (int) 1e9;
    for (char ch : s.toCharArray()) {
      frequency[ch - 'a']++;
    }
    for (int i = 0; i < 26; ++i) {
      if (frequency[i] > 0 && frequency[i] % 2 == 0) min = Math.min(min, frequency[i]);
      else max = Math.max(max, frequency[i]);
    }
    return max - min;
  }

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int n2 = nums2.length, n1 = nums1.length;
    int[] nextGreaterElements = new int[n2], result = new int[n1];
    Deque<Integer> stack = new ArrayDeque<>();
    nextGreaterElements[n2 - 1] = -1;
    stack.offerFirst(nums2[n2 - 1]);
    for (int i = n2 - 2; i >= 0; --i) {
      // (x>top) pop these elems
      while (!stack.isEmpty() && nums2[i] > stack.peek()) {
        stack.poll();
      }
      nextGreaterElements[i] = !stack.isEmpty() ? stack.peek() : -1;
      stack.offerFirst(nums2[i]);
    }
    Map<Integer, Integer> valToIdx = new HashMap<>(), idxToVal = new HashMap<>();
    for (int i = 0; i < n2; ++i) {
      valToIdx.put(nums2[i], i);
    }
    for (int i = 0; i < n2; ++i) {
      idxToVal.put(i, nextGreaterElements[i]);
    }
    for (int i = 0; i < n1; ++i) {
      result[i] = idxToVal.get(valToIdx.get(nums1[i]));
    }
    return result;
  }

  // O(m+n) | O(1)
  public int appendCharacters(String s, String t) {
    int i = 0, j = 0, m = s.length(), n = t.length();
    while (i < m && j < n) {
      if (s.charAt(i) == t.charAt(j)) j++;
      i++;
    }
    return n - j;
  }

  public int[] vowelStrings(String[] words, int[][] queries) {
    int n = words.length;
    int[] prefixSum = new int[n];
    int[] vowelCount = new int[n];
    int j = 0;
    for (String word : words) {
      if (isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) {
        vowelCount[j] = 1;
      }
      j++;
    }
    prefixSum[0] = vowelCount[0];
    for (int i = 1; i < n; ++i) {
      prefixSum[i] = prefixSum[i - 1] + vowelCount[i];
    }
    int m = queries.length;
    int[] result = new int[m];
    for (int i = 0; i < m; ++i) {
      int left = queries[i][0], right = queries[i][1];
      if (left == 0) result[i] = prefixSum[right];
      else result[i] = prefixSum[right] - prefixSum[left - 1];
    }
    return result;
  }

  public String mergeAlternately(String word1, String word2) {
    int m = word1.length(), n = word2.length(), i = 0, j = 0;
    StringBuilder sb = new StringBuilder();
    while (i < m && j < n) {
      sb.append(word1.charAt(i)).append(word2.charAt(j));
      i++;
      j++;
    }
    if (i < m) sb.append(word1, i, m);
    if (j < n) sb.append(word2, j, n);
    return sb.toString();
  }

  public String firstPalindrome(String[] words) {
    for (String word : words) {
      if (isPalindrome(word)) return word;
    }
    return "";
  }

  private boolean isPalindrome(String word) {
    int i = 0, j = word.length() - 1;
    while (i < j) {
      if (word.charAt(i) != word.charAt(j)) return false;
      i++;
      j--;
    }
    return true;
  }

  // LC-2109
  public String addSpaces(String s, int[] spaces) {
    StringBuilder sb = new StringBuilder();
    int n = s.length(), i = 0, j = 0, jn = spaces.length;
    sb.ensureCapacity(n + jn);
    while (i < n) {
      if (j < jn && i == spaces[j]) {
        sb.append(" ");
        j++;
      }
      sb.append(s.charAt(i));
      i++;
    }
    return sb.toString();
  }

  public int[] sortArrayByParity(int[] nums) {
    int l = 0, r = nums.length - 1;
    // if the even number is at left, move on
    // if the odd number is at right, just move on
    // else swap
    while (l < r) {
      if (nums[l] % 2 == 0) l++;
      else if (nums[r] % 2 == 1) r--;
      else {
        swap(nums, l, r);
        l++;
        r--;
      }
    }
    return nums;
  }

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums); // need to sort so that we can ignore duplicate triplets
    List<List<Integer>> result = new ArrayList<>();
    int n = nums.length;
    // -1,0,1,2,-1,-4
    // i j          k
    for (int i = 0; i < n; ++i) {
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      int j = i + 1, k = n - 1;
      while (j < k) {
        // we keep the i pointer fixed and move j & k based on condition
        int sum = nums[i] + nums[j] + nums[k];
        if (sum < 0) j++;
        else if (sum > 0) k--;
        else {
          result.add(Arrays.asList(nums[i], nums[j], nums[k]));
          // here we move j & k till we find different element i.e, ignoring duplicate
          while (j < k && nums[j] == nums[j + 1]) j++;
          while (j < k && nums[k] == nums[k + 1]) k--;
          j++;
          k--;
        }
      }
    }
    return result;
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    int n = nums.length;
    // -1,0,1,2,-1,-4
    // i j          k

    // we fix i & j pointer, then move k & l pointer
    for (int i = 0; i < n; ++i) {
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      for (int j = i + 1; j < n; ++j) {
        if (j > i + 1 && nums[j] == nums[j - 1]) continue;
        int k = j + 1, l = n - 1;
        while (k < l) {
          int sum = nums[i] + nums[j] + nums[k] + nums[l];
          if (sum < target) k++;
          else if (sum > target) l--;
          else {
            result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
            while (k < l && nums[k] == nums[k + 1]) k++;
            while (k < l && nums[l] == nums[l - 1]) l--;
            k++;
            l--;
          }
        }
      }
    }
    return result;
  }

  public int trap(int[] height) {
    int n = height.length;
    int[] leftMax = new int[n], rightMax = new int[n];
    for (int i = 1; i < n; ++i)
      leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
    for (int i = n - 2; i >= 0; --i)
      rightMax[i] = Math.max(height[i+1], rightMax[i+1]);
    System.out.println(Arrays.toString(leftMax) + " " + Arrays.toString(rightMax));
    return 1;
  }

  private boolean isVowel(char ch) {
    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
  }


  private void swap(int[] A, int a, int b) {
    int t = A[a];
    A[a] = A[b];
    A[b] = t;
  }

}
