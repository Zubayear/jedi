package com.lucienvirecourt.jedi.other;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CountingBloomFilter {
  int m = 10000;
  int[] counters = new int[m];
  int k; // k hash functions

  public CountingBloomFilter(int k) {
    this.k = k;
  }

  public void add(String word) {
    for (int pos : getHashes(word)) counters[pos]++;
  }

  public boolean contains(String word) {
    for (int pos : getHashes(word)) if (counters[pos] == 0) return false;
    return true;
  }

  public void remove(String word) {
    if (!contains(word)) return;
    for (int pos : getHashes(word)) counters[pos] = Math.max(0, counters[pos] - 1);
  }

  private int hash1(String word) {
    return Math.abs(word.hashCode());
  }

  private int hash2(String word) {
    int hash = 0;
    byte[] bytes = word.getBytes(StandardCharsets.UTF_8);
    for (byte b : bytes) hash = (hash * 31) ^ (b & 0xff);
    return Math.abs(hash);
  }

  private List<Integer> getHashes(String word) {
    int h1 = hash1(word);
    int h2 = hash2(word);
    List<Integer> positions = new ArrayList<>(k);
    for (int i = 0; i < k; ++i) {
      long combinedHash = (h1 + (long) i * h2) % m;
      positions.add((int) combinedHash);
    }
    return positions;
  }
}
