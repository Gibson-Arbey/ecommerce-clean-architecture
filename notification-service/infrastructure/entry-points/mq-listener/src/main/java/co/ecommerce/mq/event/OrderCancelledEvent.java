package co.ecommerce.mq.event;

public record OrderCancelledEvent(String orderNumber, String email, String reason) {

}
