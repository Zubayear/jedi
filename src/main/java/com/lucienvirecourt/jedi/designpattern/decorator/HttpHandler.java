package com.lucienvirecourt.jedi.designpattern.decorator;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public interface HttpHandler {
  boolean isRequestValid(Map<String, String> headers);
}

class BaseHttpRequest implements HttpHandler {

  @Override
  public boolean isRequestValid(Map<String, String> headers) {
    return headers.size() > 1;
  }
}

abstract class HttpHandlerDecorator implements HttpHandler {
  protected final HttpHandler httpHandler;

  HttpHandlerDecorator(HttpHandler httpHandler) {
    this.httpHandler = httpHandler;
  }

  @Override
  public boolean isRequestValid(Map<String, String> headers) {
    return httpHandler.isRequestValid(headers);
  }
}

// we can have multiple decorator like auth, logger, rate limiter
class AuthDecorator extends HttpHandlerDecorator {
  AuthDecorator(HttpHandler httpHandler) {
    super(httpHandler);
  }

  @Override
  public boolean isRequestValid(Map<String, String> headers) {
    return httpHandler.isRequestValid(headers) && headers.containsKey("Authorization") && Objects.equals(headers.get("Authorization"), "simple");
  }
}

class TimeDecorator extends HttpHandlerDecorator {

  TimeDecorator(HttpHandler httpHandler) {
    super(httpHandler);
  }

  @Override
  public boolean isRequestValid(Map<String, String> headers) {
    int hour = LocalDateTime.now().getHour();
    return httpHandler.isRequestValid(headers) && (hour > 7 && hour < 19);
  }
}
