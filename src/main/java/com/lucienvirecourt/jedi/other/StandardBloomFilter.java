package com.lucienvirecourt.jedi.other;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class StandardBloomFilter {
  int m = 100;
  BitSet bloomFilter = new BitSet(m);
  int k; // k hash functions

  public StandardBloomFilter(int k) {
    this.k = k;
  }

  // hash word with k hash functions
  // then make bit[pos] = 1 for a list of hash
  public void add(String word) {
    List<Integer> hashes = getHashes(word);
    for (int pos : hashes) bloomFilter.set(pos);
  }

  // if even one of the bit[pos] = 0, then we know the word is not in the set
  public boolean contains(String word) {
    List<Integer> hashes = getHashes(word);
    for (int pos : hashes) if (!bloomFilter.get(pos)) return false;
    return true;
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


