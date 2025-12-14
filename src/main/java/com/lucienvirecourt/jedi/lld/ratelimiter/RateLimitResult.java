package com.lucienvirecourt.jedi.lld.ratelimiter;

public class RateLimitResult {
  private final boolean allowed;
  private final int remainingTokens;
  private final long resetTimeMs;
  private final long retryAfterMs;

  private RateLimitResult(boolean allowed, int remainingTokens,
                          long resetTimeMs, long retryAfterMs) {
    this.allowed = allowed;
    this.remainingTokens = remainingTokens;
    this.resetTimeMs = resetTimeMs;
    this.retryAfterMs = retryAfterMs;
  }

  public static RateLimitResult allowed(int remaining, long resetTimeMs) {
    return new RateLimitResult(true, remaining, resetTimeMs, 0);
  }

  public static RateLimitResult rejected(long retryAfterMs, long resetTimeMs) {
    return new RateLimitResult(false, 0, resetTimeMs, retryAfterMs);
  }

  public boolean isAllowed() { return allowed; }
  public int getRemainingTokens() { return remainingTokens; }
  public long getResetTimeMs() { return resetTimeMs; }
  public long getRetryAfterMs() { return retryAfterMs; }

  @Override
  public String toString() {
    if (allowed) {
      return String.format("Allowed [remaining=%d, resetAt=%d]",
        remainingTokens, resetTimeMs);
    } else {
      return String.format("Rejected [retryAfter=%dms]", retryAfterMs);
    }
  }
}
