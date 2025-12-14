package com.lucienvirecourt.jedi.designpattern.adapter;

interface PaymentProcessor {
  void processPayment(double amount, String currency);

  boolean isPaymentSuccessful();

  String getTransactionId();
}

class DemoPaymentProcessor implements PaymentProcessor {

  private String transactionId;
  private boolean isPaymentSuccessful;

  @Override
  public void processPayment(double amount, String currency) {
    System.out.printf("processing payment with amount '%.2f' and currency '%s'%n", amount, currency);
    transactionId = "TXN_" + System.currentTimeMillis();
    isPaymentSuccessful = true;
    System.out.println("Done");
  }

  @Override
  public boolean isPaymentSuccessful() {
    return isPaymentSuccessful;
  }

  @Override
  public String getTransactionId() {
    return transactionId;
  }
}

class CheckoutService {
  private final PaymentProcessor paymentProcessor;

  CheckoutService(PaymentProcessor paymentProcessor) {
    this.paymentProcessor = paymentProcessor;
  }

  void checkout(double amount, String currency) {
    paymentProcessor.processPayment(amount, currency);
  }
}

class LegacyGateway {
  private long transactionRef;
  private boolean isPaymentSuccessful;

  public void executeTransaction(double amount, String currency) {
    transactionRef = System.currentTimeMillis();
    isPaymentSuccessful = true;
    System.out.println("Transaction executed with amount " + amount);
  }

  public boolean checkStatus() {
    return isPaymentSuccessful;
  }

  public long getTransactionRef() {
    return transactionRef;
  }
}

// working
class LegacyGatewayAdapter implements PaymentProcessor {
  private final LegacyGateway legacyGateway;
  private long currentRef;

  LegacyGatewayAdapter(LegacyGateway legacyGateway) {
    this.legacyGateway = legacyGateway;
  }

  @Override
  public void processPayment(double amount, String currency) {
    legacyGateway.executeTransaction(amount, currency);
    currentRef = legacyGateway.getTransactionRef();
  }

  @Override
  public boolean isPaymentSuccessful() {
    return legacyGateway.checkStatus();
  }

  @Override
  public String getTransactionId() {
    return "LEGACY_TXN_" + currentRef;
  }
}

public class EPay {
  static void main() {
    PaymentProcessor paymentProcessor = new DemoPaymentProcessor();
    CheckoutService checkoutService = new CheckoutService(paymentProcessor);
    checkoutService.checkout(99.09, "USD");

    paymentProcessor = new LegacyGatewayAdapter(new LegacyGateway());
    checkoutService = new CheckoutService(paymentProcessor);
    checkoutService.checkout(45.909, "USD");

  }
}
