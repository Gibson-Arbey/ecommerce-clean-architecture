package co.ecommerce.model.orderevent.gateways;

import co.ecommerce.model.orderevent.OrderPlacedEvent;

public interface OrderEventRepository {

    boolean publishOrderPlaced(OrderPlacedEvent event);
}
