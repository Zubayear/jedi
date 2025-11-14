package com.lucienvirecourt.jedi.problems;

public class BitManipulation {

  public int minBitFlips(int start, int goal) {
    int xorResult = start ^ goal; // we will get the differing bits
    int count = 0;
    // do & with 1 and then right shift so that we can do & again to get 1
    while (xorResult != 0) {
      count += xorResult & 1;
      xorResult >>= 1;
    }
    return count;
  }

  public int missingNumbers(int[] nums) {
    // since value is from [0,n] we will have index and value relationship
    // so, we will do idx ^ nums[idx]
    // if we had all the values the result would be 0
    // But since we have missing value we will get the result this way
    int n = nums.length, res = n;
    for (int i = 0; i < nums.length; ++i) {
      res ^= i ^ nums[i];
    }
    return res;
  }
}
