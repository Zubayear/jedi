package com.lucienvirecourt.jedi.lld.ratelimiter;

import java.io.Serializable;
import java.time.Clock;

public class TokenBucketAlgorithm implements RateLimiterAlgorithm {

  private final RateLimitStorage storage;

  public TokenBucketAlgorithm(RateLimitStorage storage) {
    this.storage = storage;
  }

  @Override
  public RateLimitResult allowRequest(String key, RateLimiterConfig config, Clock clock) {
    String bucketKey = "token_bucket:" + key;

    TokenBucket bucket = storage.get(bucketKey);
    if (bucket == null) {
      bucket = new TokenBucket(
        config.maxRequest(),
        config.maxRequest(),
        clock.millis()
      );
    }

    bucket = refillBucket(bucket, config, clock.millis());

    if (bucket.tokens > 0) {
      bucket = new TokenBucket(
        bucket.cap,
        bucket.tokens - 1,
        clock.millis()
      );

      storage.set(bucketKey, bucket, config.window());

      return RateLimitResult.allowed(
        bucket.tokens,
        calculateResetTime()
      );
    } else {
//      return RateLimitResult.rejected(
//
//      );
    }

    return null;
  }

  @Override
  public void reset(String key) {
  }

  @Override
  public String getName() {
    return "TOKEN_BUCKET";
  }

  private TokenBucket refillBucket(TokenBucket bucket, RateLimiterConfig config, long nowMs) {
    long elapsedTime = nowMs - bucket.lastRefilledTime;

    double refillRate = (double) bucket.cap / config.window().toMillis();
    int tokensToAdd = (int) (elapsedTime * refillRate);

    if (tokensToAdd > 0) {
      int newTokens = Math.min(bucket.cap, bucket.tokens + tokensToAdd);
      return new TokenBucket(bucket.cap, newTokens, nowMs);
    }

    return bucket;
  }

  private long calculateResetTime() {
    return 0;
  }

  static class TokenBucket implements Serializable {
    final int cap;
    final int tokens;
    final long lastRefilledTime;

    TokenBucket(int cap, int tokens, long lastRefilledTime) {
      this.cap = cap;
      this.tokens = tokens;
      this.lastRefilledTime = lastRefilledTime;
    }
  }
}
