package com.lucienvirecourt.jedi.lld.ratelimiter;

import java.time.Duration;

public interface RateLimitStorage {
  <T> T get(String key);
  <T> void set(String key, T value, Duration ttl);
  boolean compareAndSet(String key, Object expected, Object updated);
  void delete(String key);
  long increment(String key, long delta);
  void expire(String key, Duration ttl);
}
