package com.lucienvirecourt.jedi.designpattern.prototype;

import java.util.HashMap;
import java.util.Map;

interface EnemyPrototype {
  EnemyPrototype clone();
}

class Enemy implements EnemyPrototype {
  private final String type;
  private int health;
  private final double speed;
  private final boolean armored;
  private final String weapon;

  public Enemy(String type, int health, double speed, boolean armored, String weapon) {
    this.type = type;
    this.health = health;
    this.speed = speed;
    this.armored = armored;
    this.weapon = weapon;
  }

  @Override
  public Enemy clone() {
    return new Enemy(type, health, speed, armored, weapon);
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void displayState() {
    System.out.printf("%s [Health %d, speed %.2f, armored %b, weapon %s]%n", type, health, speed, armored, weapon);
  }
}

class EnemyRegistry {
  private final Map<String, Enemy> prototypes = new HashMap<>();

  public void register(String key, Enemy value) {
    prototypes.put(key, value);
  }

  public Enemy get(String key) {
    Enemy enemy = prototypes.get(key);
    if (enemy != null) return enemy.clone();
    throw new IllegalArgumentException("No prototype registered for: " + key);
  }
}

public class Game {
  public static void main(String[] args) {
    EnemyRegistry registry = new EnemyRegistry();

    registry.register("flying", new Enemy("FlyingEnemy", 100, 12.0, false, "Laser"));
    registry.register("armored", new Enemy("ArmoredEnemy", 300, 6.0, true, "Cannon"));

    Enemy enemy1 = registry.get("flying");
    Enemy enemy2 = registry.get("flying");
    enemy2.setHealth(80);

    Enemy enemy3 = registry.get("armored");
    enemy1.displayState();
    enemy2.displayState();
    enemy3.displayState();
  }
}
