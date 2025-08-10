package com.lucienvirecourt.jedi.designpattern.decorator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCoffeeTest {
  @Test
  void testCoffeeDecoration() {
    SimpleCoffee simpleCoffee = new SimpleCoffee();
    MilkDecorator milkDecorator = new MilkDecorator(simpleCoffee);
    SugarDecorator sugarDecorator = new SugarDecorator(milkDecorator);
    assertEquals(3.4, sugarDecorator.getCost());
    assertEquals("Simple Coffee, Milk, Sugar", sugarDecorator.getDescription());
  }
}
