package com.zubayear.dsaj.problems;

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
            int[] keyCounter  = new int[26];
            for (char ch : str.toCharArray()) {
                keyCounter[ch - 'a']++;
            }
            sb.append(Arrays.toString(keyCounter));
            String key = sb.toString();
            sb.setLength(0);
            map.computeIfAbsent(key, k -> new ArrayList<>());
            map.get(key).add(str);
        }

        result.addAll(map.values());
        return result;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; ++i) {
            if (flowerbed[i] == 0) {
                if (i == 0 || flowerbed[i-1] == 0 && i == flowerbed.length - 1 || flowerbed[i+1] == 0) {
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
}
