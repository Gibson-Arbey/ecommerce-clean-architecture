package co.ecommerce.model.inventoryevent;

public record OrderConfirmedEvent(String orderNumber, String email) {
}
