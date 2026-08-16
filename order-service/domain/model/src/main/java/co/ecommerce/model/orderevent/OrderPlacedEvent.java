package co.ecommerce.model.orderevent;

import co.ecommerce.model.order.Order;

import java.util.List;

public record OrderPlacedEvent(String orderNumber, String email, List<OrderItemEvent> items) {

    public record OrderItemEvent(String sku, String price, Integer quantity){}

    public static OrderPlacedEvent from(Order order, String email) {
        return new OrderPlacedEvent(
                order.getOrderNumber(),
                email,
                order.getItems()
                        .stream()
                        .map(item -> new OrderItemEvent(
                                item.getSku(),
                                item.getPrice().toString(),
                                item.getQuantity()
                        ))
                        .toList()
        );
    }

}
