package com.lucienvirecourt.jedi.other;

import java.util.ArrayList;
import java.util.List;

class Addition {
  static double sum(List<? extends Number> numbers) {
    double s = 0;
    for (Number n : numbers) {
      s += n.doubleValue();
    }
    return s;
  }
}

class Payment {}
class CreditCardPayment extends Payment{}
class PaypalPayment extends Payment{}
class BitcoinPayment extends Payment{}


public class GenericExperiment {

  static void logAllPayment(List<? extends Payment> payments) {
    for (Payment p : payments) System.out.println("Logging: " + p);
  }

  public static void main(String[] args) {
    List<Number> integers = new ArrayList<>();
    integers.add(10);
    integers.add(11);
    integers.add(12);

    List<Double> doubles = new ArrayList<>();
    doubles.add(10.0);
    doubles.add(11.0);
    doubles.add(12.0);

    System.out.printf("%.2f%n", Addition.sum(integers));
    System.out.printf("%.2f%n", Addition.sum(doubles));

    // invariance
    List<Payment> payments = new ArrayList<Payment>();
    payments.add(new PaypalPayment());
    payments.add(new CreditCardPayment());
    List<CreditCardPayment> creditCardPayments = new ArrayList<CreditCardPayment>();

    // left and right type must be same
    // payments = creditCardPayments; // compiler error

    logAllPayment(payments);
    payments.add(new CreditCardPayment());
    logAllPayment(payments);
    logAllPayment(creditCardPayments);
  }
}
