package com.lucienvirecourt.jedi.lld.ratelimiter;

public class SystemTimeProvider implements TimeProvider {

  @Override
  public long nowMs() {
    return System.currentTimeMillis();
  }
}
