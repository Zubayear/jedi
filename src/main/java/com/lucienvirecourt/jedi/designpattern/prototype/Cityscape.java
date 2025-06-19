package com.lucienvirecourt.jedi.designpattern.prototype;

abstract class Graphic {
  private int heightInPixels;

  public Graphic(int heightInPixels) {
    this.heightInPixels = heightInPixels;
  }

  public int getHeightInPixels() {
    return heightInPixels;
  }

  public void setHeightInPixels(int heightInPixels) {
    this.heightInPixels = heightInPixels;
  }

  public abstract Graphic clone();
}

class BuildingType {
  private String type;

  public BuildingType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "BuildingType{" +
      "type='" + type + '\'' +
      '}';
  }
}

class Building extends Graphic {
  private BuildingType buildingType;

  public Building(int heightInPixels, BuildingType buildingType) {
    super(heightInPixels);
    this.buildingType = buildingType;
  }

  public BuildingType getBuildingType() {
    return buildingType;
  }

  public void setBuildingType(BuildingType buildingType) {
    this.buildingType = buildingType;
  }

  @Override
  public Building clone() {
    return new Building(this.getHeightInPixels(), new BuildingType(buildingType.getType()));
  }

  public void display() {
    System.out.printf("%s building with pixel %d%n", this.getBuildingType().getType(), this.getHeightInPixels());
  }
}

public class Cityscape {
  public static void main(String[] args) {
    BuildingType houseBuildingType = new BuildingType("House");
    Building b1 = new Building(21, houseBuildingType);
    b1.display();
    Building b2 = b1.clone();
    b2.display();
    Building b3 = b1.clone();
    b3.display();
    Building b4 = b1.clone();
    b4.display();

    houseBuildingType.setType("House with yard");

  }
}
