package com.lucienvirecourt.jedi.designpattern.decorator;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpHandlerDecoratorTest {
  @Test
  void testHttpHandler() {
    BaseHttpRequest baseHttpRequest = new BaseHttpRequest();
    // wrap with auth and time decorator
    AuthDecorator authDecorator = new AuthDecorator(baseHttpRequest);
    TimeDecorator timeDecorator = new TimeDecorator(authDecorator);
    boolean requestValid = timeDecorator.isRequestValid(Map.of("Authorization", "simple"));
    assertFalse(requestValid);
  }
}
