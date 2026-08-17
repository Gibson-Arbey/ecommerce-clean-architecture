package co.ecommerce.model.inventoryevent.gateways;

import co.ecommerce.model.inventoryevent.OrderCancelledEvent;
import co.ecommerce.model.inventoryevent.OrderConfirmedEvent;

public interface InventoryEventRepository {

    void publishOrderCancelled(OrderCancelledEvent event);

    void publishOrderConfirmed(OrderConfirmedEvent event);
}
