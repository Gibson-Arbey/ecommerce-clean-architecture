package co.ecommerce.mq.listener.event;

public record OrderConfirmedEvent(String orderNumber, String email) {
}
