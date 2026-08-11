package co.ecommerce.model.order;

import co.ecommerce.model.exception.InvalidFieldException;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Order {

    private final Long id;

    private final String orderNumber;

    private final String userId;

    private final List<OrderItem> items;

    private Order(Long id, String orderNumber, String userId, List<OrderItem> items) {
        if (orderNumber == null) throw new InvalidFieldException("orderNumber cannot be null");
        if (items == null || items.isEmpty()) throw new InvalidFieldException("items cannot be null or empty");
        if(userId == null || userId.isEmpty()) throw new InvalidFieldException("userId cannot be null or empty");
        this.id = id;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.items = items;
    }

    public static Order create(String userId, List<OrderItem> items) {
        return new Order(null, UUID.randomUUID().toString(), userId, items);
    }

    public static Order restore(Long id, String orderNumber, String userId, List<OrderItem> items) {
        return new Order(id, orderNumber, userId, items);
    }
}
