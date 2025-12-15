import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

record OrderEvent(Order order, EventType eventType) {
  public enum EventType {
    CREATED, UPDATED, CANCELLED, SHIPPED
  }
}

static class Order {

}

// Observer (anyone implementing this would be a subscriber)
interface OrderEventListener {
  void onOrderEvent(OrderEvent event);
}

// Event publisher
static public class OrderService {
  private final List<OrderEventListener> listeners = new CopyOnWriteArrayList<>();

  public void addListener(OrderEventListener listener) {
    listeners.add(listener);
  }

  public void removeListener(OrderEventListener listener) {
    listeners.remove(listener);
  }

  public void createOrder(Order order) {
    // do something
    notifyListeners(new OrderEvent(order, OrderEvent.EventType.CREATED));
  }

  private void notifyListeners(OrderEvent event) {
    listeners.forEach(listener -> listener.onOrderEvent(event));
  }
}

static class EmailNotificationListener implements OrderEventListener {
  @Override
  public void onOrderEvent(OrderEvent event) {
    if (event.eventType() == OrderEvent.EventType.CREATED) {
      System.out.println("Send Email that an order is created.");
    }
  }
}

static class InventoryListener implements OrderEventListener {
  @Override
  public void onOrderEvent(OrderEvent event) {
    if (event.eventType() == OrderEvent.EventType.CREATED) {
      System.out.println("Reserve inventory");
    }
  }
}

static class AnalyticsListener implements OrderEventListener {
  @Override
  public void onOrderEvent(OrderEvent event) {
    System.out.println("track order");
  }
}

// NEW: Add fraud detection without touching OrderService
static class FraudDetectionListener implements OrderEventListener {
  @Override
  public void onOrderEvent(OrderEvent event) {
    if (event.eventType() == OrderEvent.EventType.CREATED) {
      System.out.println("check for fruad");
    }
  }
}

void main() {
  var orderService = new OrderService();
  orderService.addListener(new EmailNotificationListener());
  orderService.addListener(new InventoryListener());
  orderService.addListener(new AnalyticsListener());
  orderService.addListener(new FraudDetectionListener());

  orderService.createOrder(
    new Order()
  );
}
