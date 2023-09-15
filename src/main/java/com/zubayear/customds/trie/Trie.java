package com.zubayear.customds.trie;

import java.util.*;

public class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch); // go to next node
        }
        current.isTerminal = true;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) return false;
            current = current.children.get(ch);
        }
        return true;
    }

    public boolean exists(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) return false;
            current = current.children.get(ch);
        }
        return current.isTerminal;
    }

    public List<String> dfs(TrieNode node) {
        List<String> result = new ArrayList<>();
        dfsHelper(node, new StringBuilder(), result);
        return result;
    }

    public List<String> dfs(TrieNode node, String prefix) {
        List<String> result = new ArrayList<>();
        dfsHelper(node, new StringBuilder(prefix), result);
        return result;
    }

    private void dfsHelper(TrieNode root, StringBuilder runningWord, List<String> result) {
        if (root.isTerminal) {
            // we found the word
            result.add(runningWord.toString());
        }
        for (char c : root.children.keySet()) {
            runningWord.append(c); // a
            dfsHelper(root.children.get(c), runningWord, result); // a's child
            runningWord.deleteCharAt(runningWord.length() - 1); // remove last char i.e. apple remove e and explore from l
        }
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = findNodeForPrefix(prefix);
        if (node == null) return result;
        return dfs(node, prefix);
    }

    /**
     * @param prefix prefix to find the TrieNode corresponding to the prefix.
     * @return
     */
    private TrieNode findNodeForPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) return null;
        }
        return current; // this will return TireNode of p of app
    }

    public String reorganizeString(String s) {
        // aabbcaabcd
        int[] charFreq = new int[26];
        for (char c : s.toCharArray()) {
            charFreq[c - 'a'] += 1;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[1], x[1]));
        for (int i = 0; i < 26; ++i) {
            if (charFreq[i] > 0) pq.offer(new int[]{i + 'a', charFreq[i]});
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (sb.length() == 0 || cur[0] != sb.charAt(sb.length() - 1)) {
                // either empty sb or the previous value is not equal
                // first we've added 'a' then if we don't get another 'a' we'll add
                sb.append((char) cur[0]);
                if (--cur[1] > 0) pq.offer(cur); // a will be a -> 3
            } else {
                if (pq.isEmpty()) return ""; // for aaab, after adding last a i.e. abaa at this instance pq will be empty
                // first char is same as last char
                // a -> 3, b -> 3, ...
                // in this case we'll get 'a' again but we want 'b'
                int[] next = pq.poll();
                sb.append((char) next[0]);
                if (--next[1] > 0) pq.offer(next);
                pq.offer(cur); // push the cur i.e. we have polled (a,3) but we might need it next
            }
        }
        return sb.toString();
    }
}
