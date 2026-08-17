package co.ecommerce.model.inventoryevent;

import java.util.List;

public record OrderPlacedEvent(String orderNumber, String email, List<OrderItemEvent> items) {

    public record OrderItemEvent(String sku, String price, Integer quantity){}

}
