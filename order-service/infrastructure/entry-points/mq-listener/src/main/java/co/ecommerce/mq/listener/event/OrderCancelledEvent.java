package co.ecommerce.mq.listener.event;

public record OrderCancelledEvent(String orderNumber, String email, String reason) {

}
