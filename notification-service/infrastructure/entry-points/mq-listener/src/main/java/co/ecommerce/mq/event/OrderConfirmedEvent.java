package co.ecommerce.mq.event;

public record OrderConfirmedEvent(String orderNumber, String email) {
}
