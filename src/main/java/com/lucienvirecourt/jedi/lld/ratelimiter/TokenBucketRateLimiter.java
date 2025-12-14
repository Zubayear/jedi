package com.lucienvirecourt.jedi.lld.ratelimiter;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {

  private final RateLimiterConfig config;
  private final ConcurrentHashMap<String, UserBucket> buckets;
  private final Clock clock;

  public TokenBucketRateLimiter(RateLimiterConfig config) {
    this(config, Clock.systemUTC());
  }

  TokenBucketRateLimiter(RateLimiterConfig config, Clock clock) {
    this.config = config;
    this.clock = clock;
    this.buckets = new ConcurrentHashMap<>();
  }

  @Override
  public boolean allowRequest(String userId) {
    if (userId == null || userId.isEmpty()) throw new IllegalArgumentException("UserId cannot be null or empty");
    UserBucket userBucket = buckets.computeIfAbsent(userId, k -> new UserBucket(config.maxRequest(), clock.instant()));
    return userBucket.tryConsume(clock.instant(), config);
  }

  @Override
  public void reset(String userId) {
    buckets.remove(userId);
  }

  private static class UserBucket {
    private int tokens;
    private Instant lastRefillTime;

    public UserBucket(int tokens, Instant lastRefillTime) {
      this.tokens = tokens;
      this.lastRefillTime = lastRefillTime;
    }

    synchronized boolean tryConsume(Instant now, RateLimiterConfig config) {
      refill(now, config);

      if (tokens > 0) {
        tokens--;
        return true;
      }
      return false;
    }

    private void refill(Instant now, RateLimiterConfig config) {
      Duration elapsed = Duration.between(lastRefillTime, now);
      if (elapsed.compareTo(config.window()) >= 0) {
        tokens = config.maxRequest();
        lastRefillTime = now;
      }
    }
  }
}
