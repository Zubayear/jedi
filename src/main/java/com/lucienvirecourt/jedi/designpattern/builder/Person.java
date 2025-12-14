package com.lucienvirecourt.jedi.designpattern.builder;

import java.time.LocalDate;

// The Builder Pattern lets you construct complex objects step-by-step,
// allowing you to produce different representations of an object
// using the same construction process.
public class Person {
  // required field
  private final String firstName;
  private final String lastName;

  private final String middleName;
  private final String emailAddress;
  private final LocalDate dob;
  private final String phoneNumber;

  private Person(PersonBuilder personBuilder) {
    this.lastName = personBuilder.lastName;
    this.middleName = personBuilder.middleName;
    this.emailAddress = personBuilder.emailAddress;
    this.firstName = personBuilder.firstName;
    this.dob = personBuilder.dob;
    this.phoneNumber = personBuilder.phoneNumber;
  }

  @Override
  public String toString() {
    return "Person{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", middleName='" + middleName + '\'' +
      ", emailAddress='" + emailAddress + '\'' +
      ", dob=" + dob +
      ", phoneNumber='" + phoneNumber + '\'' +
      '}';
  }

  public static class PersonBuilder {
    private final String firstName;
    private final String lastName;

    private String middleName;
    private String emailAddress;
    private LocalDate dob;
    private String phoneNumber;

    public PersonBuilder(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public PersonBuilder middleName(String middleName) {
      this.middleName = middleName;
      return this;
    }

    public PersonBuilder mailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
      return this;
    }

    public PersonBuilder dob(LocalDate dob) {
      this.dob = dob;
      return this;
    }

    public PersonBuilder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public Person build() {
      return new Person(this);
    }
  }

}

class PersonDemo {
  static void main() {
    var person = new Person.PersonBuilder("Bob", "Marley")
      .mailAddress("bob.marley@gmail.com")
      .build();
    System.out.println(person);
  }
}
