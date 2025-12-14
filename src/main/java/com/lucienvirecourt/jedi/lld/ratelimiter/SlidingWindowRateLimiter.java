package com.lucienvirecourt.jedi.lld.ratelimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements RateLimiter {

  private final int maxRequests;
  private final long windowMs;
  private final TimeProvider timeProvider;

  private final ConcurrentHashMap<String, UserRecord> users = new ConcurrentHashMap<>();

  public SlidingWindowRateLimiter(int maxRequests, long windowMs, TimeProvider timeProvider) {
    if (maxRequests <= 0) throw new IllegalArgumentException("maxRequests must be > 0");
    if (windowMs <= 0) throw new IllegalArgumentException("windowMs must be > 0");
    this.maxRequests = maxRequests;
    this.windowMs = windowMs;
    this.timeProvider = timeProvider == null ? new SystemTimeProvider() : timeProvider;
  }

  @Override
  public boolean allowRequest(String userId) {
    return false;
  }

  @Override
  public void reset(String userId) {

  }

  private static class UserRecord {
    final Deque<Long> timestamps = new ArrayDeque<>();
  }
}
