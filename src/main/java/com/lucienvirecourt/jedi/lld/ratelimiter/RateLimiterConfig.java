package com.lucienvirecourt.jedi.lld.ratelimiter;

import java.time.Duration;

public record RateLimiterConfig(int maxRequest, Duration window) {
  public RateLimiterConfig {
    if (maxRequest <= 0) throw new IllegalArgumentException("Max request must be positive");
    if (window == null || window.isNegative()) throw new IllegalArgumentException("Invalid time window");
  }
}
