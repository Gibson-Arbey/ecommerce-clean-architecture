package co.ecommerce.model.inventoryevent;

public record OrderCancelledEvent(String orderNumber, String email, String reason) {

}
