package com.lucienvirecourt.jedi.designpattern.template;

public abstract class DataProcessor {
  // Template method
  public final void process() {
    readData();
    processData();
    writeData();
  }

  abstract void readData();
  abstract void processData();

  void writeData() {
    IO.println("Default data writing method");
  }
}

class CSVProcessor extends DataProcessor {

  @Override
  void readData() {
    IO.println("Reading CSV file");
  }

  @Override
  void processData() {
    IO.println("Processing CSV file");
  }
}
