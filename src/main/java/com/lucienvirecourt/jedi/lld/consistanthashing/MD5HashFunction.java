package com.lucienvirecourt.jedi.lld.consistanthashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5-based hash function
 * Thread-safe implementation using ThreadLocal
 */
public class MD5HashFunction implements HashFunction {

  // ThreadLocal to avoid synchronization overhead
  private static final ThreadLocal<MessageDigest> MD5_HOLDER =
    ThreadLocal.withInitial(() -> {
      try {
        return MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException e) {
        throw new IllegalStateException("MD5 algorithm not available", e);
      }
    });

  @Override
  public long hash(String key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    MessageDigest md5 = MD5_HOLDER.get();
    md5.reset();
    md5.update(key.getBytes(StandardCharsets.UTF_8));
    byte[] digest = md5.digest();

    // Use first 8 bytes to create long
    long hash = 0;
    for (int i = 0; i < 4; i++) {
      hash <<= 8;
      hash |= (digest[i] & 0xFF);
    }
    return hash;
  }
}

