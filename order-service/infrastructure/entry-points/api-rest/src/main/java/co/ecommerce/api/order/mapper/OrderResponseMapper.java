package co.ecommerce.api.order.mapper;

import co.ecommerce.api.order.response.OrderItemResponse;
import co.ecommerce.api.order.response.OrderResponse;
import co.ecommerce.model.order.Order;

import java.util.List;

public class OrderResponseMapper {

    public static OrderResponse toOrderResponse(Order order) {
        if(order == null) return null;
        var items = order.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getId(),
                        item.getSku(),
                        item.getPrice(),
                        item.getQuantity()
                ))
                .toList();
        return new OrderResponse(order.getId(), order.getOrderNumber(), items);
    }

    public static List<OrderResponse> toOrderResponseList(List<Order> orders) {
        if(orders == null || orders.isEmpty()) return List.of();
        return orders.stream()
                .map(OrderResponseMapper::toOrderResponse)
                .toList();
    }
}
