package com.lucienvirecourt.jedi.lld.ratelimiter;

import java.time.Clock;

public interface RateLimiterAlgorithm {
  RateLimitResult allowRequest(String key, RateLimiterConfig config, Clock clock);
  void reset(String key);
  String getName();
}
