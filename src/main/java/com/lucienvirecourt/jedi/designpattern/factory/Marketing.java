package com.lucienvirecourt.jedi.designpattern.factory;

// A Factory Pattern defines an interface for creating an object
// but lets subclasses or methods decide which class to instantiate.
interface Notification {
  void send(String from, String to, String message);
}

class EmailNotification implements Notification {
  @Override
  public void send(String from, String to, String message) {
    System.out.printf("Send email to '%s' from '%s' with content '%s'%n", to, from, message);
  }
}

class AppNotification implements Notification {
  @Override
  public void send(String from, String to, String message) {
    System.out.printf("App notification to '%s' from '%s' with content '%s'%n", to, from, message);
  }
}

class SmsNotification implements Notification {
  @Override
  public void send(String from, String to, String message) {
    System.out.printf("Sms notification to '%s' from '%s' with content '%s'%n", to, from, message);
  }
}

abstract class NotificationCreator {
  abstract Notification createNotification();

  void send(String from, String to, String message) {
    createNotification().send(from, to, message);
  }
}

class EmailNotificationCreator extends NotificationCreator {

  @Override
  Notification createNotification() {
    return new EmailNotification();
  }
}

class PushNotificationCreator extends NotificationCreator {
  @Override
  Notification createNotification() {
    return new AppNotification();
  }
}

class SmsNotificationCreator extends NotificationCreator {
  @Override
  Notification createNotification() {
    return new SmsNotification();
  }
}


public class Marketing {
  public static void main(String[] args) {
    NotificationCreator notificationCreator;
    notificationCreator = new SmsNotificationCreator();
    notificationCreator.send("abc@gmail.com", "sj.kin@gmail.com", "Hello, you are chosen.");

    notificationCreator = new EmailNotificationCreator();
    notificationCreator.send("hello", "123242322", "Hi, we are good, how are you?");

    notificationCreator = new PushNotificationCreator();
    notificationCreator.send("gorm", "iPhone12_122skaldk", "Are you ready to play?");
  }
}
