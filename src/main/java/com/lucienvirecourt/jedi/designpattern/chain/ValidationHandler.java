//// Handler interface
//public interface ValidationHandler {
//  ValidationResult validate(Order order);
//  void setNext(ValidationHandler next);
//}
//
//// Base implementation
//abstract class BaseValidationHandler implements ValidationHandler {
//  private ValidationHandler next;
//
//  @Override
//  public void setNext(ValidationHandler next) {
//    this.next = next;
//  }
//
//  protected ValidationResult validateNext(Order order) {
//    if (next != null) {
//      return next.validate(order);
//    }
//    return ValidationResult.success();
//  }
//}
//
//// Concrete handlers
//class PriceValidationHandler extends BaseValidationHandler {
//  @Override
//  public ValidationResult validate(Order order) {
//    if (order.getTotal() <= 0) {
//      return ValidationResult.failure("Price must be positive");
//    }
//    return validateNext(order);
//  }
//}
//
//class InventoryValidationHandler extends BaseValidationHandler {
//  private final InventoryService inventoryService;
//
//  public InventoryValidationHandler(InventoryService inventoryService) {
//    this.inventoryService = inventoryService;
//  }
//
//  @Override
//  public ValidationResult validate(Order order) {
//    if (!inventoryService.isAvailable(order.getItems())) {
//      return ValidationResult.failure("Items not in stock");
//    }
//    return validateNext(order);
//  }
//}
//
//class CustomerCreditValidationHandler extends BaseValidationHandler {
//  private final CreditService creditService;
//
//  public CustomerCreditValidationHandler(CreditService creditService) {
//    this.creditService = creditService;
//  }
//
//  @Override
//  public ValidationResult validate(Order order) {
//    if (!creditService.hasCredit(order.getCustomerId(), order.getTotal())) {
//      return ValidationResult.failure("Insufficient credit");
//    }
//    return validateNext(order);
//  }
//}
//
//// Easy to add new validation without touching existing code
//class FraudDetectionHandler extends BaseValidationHandler {
//  private final FraudDetectionService fraudService;
//
//  public FraudDetectionHandler(FraudDetectionService fraudService) {
//    this.fraudService = fraudService;
//  }
//
//  @Override
//  public ValidationResult validate(Order order) {
//    if (fraudService.isSuspicious(order)) {
//      return ValidationResult.failure("Fraud detected");
//    }
//    return validateNext(order);
//  }
//}
//
//// Build chain
//public class ValidationChainBuilder {
//  public static ValidationHandler buildChain(/* dependencies */) {
//    ValidationHandler priceValidator = new PriceValidationHandler();
//    ValidationHandler inventoryValidator = new InventoryValidationHandler(inventoryService);
//    ValidationHandler creditValidator = new CustomerCreditValidationHandler(creditService);
//    ValidationHandler fraudValidator = new FraudDetectionHandler(fraudService); // NEW!
//
//    priceValidator.setNext(inventoryValidator);
//    inventoryValidator.setNext(creditValidator);
//    creditValidator.setNext(fraudValidator);  // Easy to insert
//
//    return priceValidator;
//  }
//}
//
//static class ValidationResult {
//  public static ValidationResult success() {
//    return null;
//  }
//}
//
//class Order {
//
//}
//
//void main() {
//  // Usage
//  ValidationHandler chain = ValidationChainBuilder.buildChain();
//  ValidationResult result = chain.validate(order);
//}
//
//
