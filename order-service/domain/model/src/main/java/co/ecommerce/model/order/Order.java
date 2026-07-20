package co.ecommerce.model.order;

import co.ecommerce.model.exception.InvalidFieldException;
import lombok.Getter;

import java.util.List;

@Getter
public class Order {

    private final Long id;

    private final String orderNumber;

    private final List<OrderItem> items;

    private Order(Long id, String orderNumber, List<OrderItem> items) {
        if (orderNumber == null) throw new InvalidFieldException("orderNumber cannot be null");
        if (items == null || items.isEmpty()) throw new InvalidFieldException("items cannot be null or empty");

        this.id = id;
        this.orderNumber = orderNumber;
        this.items = items;
    }

    public static Order create(String orderNumber, List<OrderItem> items) {
        return new Order(null, orderNumber, items);
    }

    public static Order restore(Long id, String orderNumber, List<OrderItem> items) {
        return new Order(id, orderNumber, items);
    }
}
