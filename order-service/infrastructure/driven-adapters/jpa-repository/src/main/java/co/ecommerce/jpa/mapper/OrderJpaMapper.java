package co.ecommerce.jpa.mapper;

import co.ecommerce.jpa.entity.OrderItemJpaEntity;
import co.ecommerce.jpa.entity.OrderJpaEntity;
import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.OrderItem;

import java.util.List;

public class OrderJpaMapper {

    public static OrderJpaEntity toEntity(Order order) {
        return OrderJpaEntity.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .items(toEntityItem(order.getItems()))
                .build();
    }

    public static Order toDomain(OrderJpaEntity entity) {
        return Order.restore(entity.getId(), entity.getOrderNumber(), toDomainItem(entity.getItems()));
    }

    public static List<OrderItemJpaEntity> toEntityItem(List<OrderItem> items) {
        return items
                .stream()
                .map(item -> OrderItemJpaEntity.builder()
                    .id(item.getId())
                    .sku(item.getSku())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .build())
                .toList();
    }

    public static List<OrderItem> toDomainItem(List<OrderItemJpaEntity> entity) {
        return entity
                .stream()
                .map(item -> OrderItem.restore(
                        item.getId(),
                        item.getSku(),
                        item.getPrice(),
                        item.getQuantity()))
                .toList();
    }
}
