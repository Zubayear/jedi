package com.lucienvirecourt.jedi.designpattern.template;

public abstract class DatabaseConnection {
  protected String connectionString;

  public void log(String message) {
    IO.println(message);
  }

  abstract void connect();
  abstract void disconnect();
}

class PostgresqlConnection extends DatabaseConnection {

  @Override
  void connect() {
    // connect
  }

  @Override
  void disconnect() {
    // disconnect
  }
}
