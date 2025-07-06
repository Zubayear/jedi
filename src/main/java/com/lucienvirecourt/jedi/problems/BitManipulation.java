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
}
