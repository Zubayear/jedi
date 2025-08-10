package com.lucienvirecourt.jedi.designpattern.decorator;

// The Decorator Pattern allows you to dynamically add new behavior or responsibilities to objects at runtime, without modifying their code.
// It works by wrapping the original object inside another object (the decorator) that implements the same interface and delegates calls to it — but with extra logic.
interface Coffee {
  String getDescription();
  double getCost();
}

class SimpleCoffee implements Coffee {
  @Override
  public String getDescription() {
    return "Simple Coffee";
  }

  @Override
  public double getCost() {
    return 2.5;
  }
}

// this actually wraps coffee
abstract class CoffeeDecorator implements Coffee {
  protected Coffee decoratedCoffee;

  public CoffeeDecorator(Coffee coffee) {
    this.decoratedCoffee = coffee;
  }

  @Override
  public double getCost() {
    return decoratedCoffee.getCost();
  }

  @Override
  public String getDescription() {
    return decoratedCoffee.getDescription();
  }
}

class MilkDecorator extends CoffeeDecorator {

  public MilkDecorator(Coffee coffee) {
    super(coffee);
  }

  @Override
  public String getDescription() {
    return decoratedCoffee.getDescription() + ", Milk";
  }

  @Override
  public double getCost() {
    return decoratedCoffee.getCost() + 0.5;
  }
}

class SugarDecorator extends CoffeeDecorator {

  public SugarDecorator(Coffee coffee) {
    super(coffee);
  }

  @Override
  public String getDescription() {
    return decoratedCoffee.getDescription() + ", Sugar";
  }

  @Override
  public double getCost() {
    return decoratedCoffee.getCost() + 0.4;
  }
}
