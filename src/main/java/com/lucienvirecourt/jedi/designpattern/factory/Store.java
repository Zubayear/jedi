package com.lucienvirecourt.jedi.designpattern.factory;


interface Snack {
  int getPrice();
  String getName();
}

class Chips implements Snack {
  @Override
  public int getPrice() {
    return 9;
  }

  @Override
  public String getName() {
    return "Chips";
  }
}

class ChocolateBar implements Snack {
  @Override
  public int getPrice() {
    return 10;
  }

  @Override
  public String getName() {
    return "ChocolateBar";
  }
}

class Drink implements Snack {
  @Override
  public int getPrice() {
    return 89;
  }

  @Override
  public String getName() {
    return "Drink";
  }
}

abstract class VendingMachine {
  private Snack snack;

  abstract Snack createSnack();

  private Snack getSnack() {
    if (snack == null) snack = createSnack();
    return snack;
  }

  public int getPrice() {
    return getSnack().getPrice();
  }

  public String getName() {
    return getSnack().getName();
  }
}

class DrinkProducer extends VendingMachine {
  @Override
  Snack createSnack() {
    return new Drink();
  }
}

class ChipsProducer extends VendingMachine {
  @Override
  Snack createSnack() {
    return new Chips();
  }
}

class ChocolateBarProducer extends VendingMachine {
  @Override
  Snack createSnack() {
    return new ChocolateBar();
  }
}

public class Store {

  public static void main(String[] args) {
    VendingMachine machine;
    machine = new ChocolateBarProducer();
    buySnack(machine);
    machine = new DrinkProducer();
    buySnack(machine);
    machine = new ChipsProducer();
    buySnack(machine);
  }

  private static void buySnack(VendingMachine vendingMachine) {
    System.out.printf("One %s purchased with price %d%n", vendingMachine.getName(), vendingMachine.getPrice());
  }
}
