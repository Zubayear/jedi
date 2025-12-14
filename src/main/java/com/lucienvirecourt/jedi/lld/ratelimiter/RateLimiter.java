package com.lucienvirecourt.jedi.lld.ratelimiter;

public interface RateLimiter {
  boolean allowRequest(String userId);
  void reset(String userId);
}
