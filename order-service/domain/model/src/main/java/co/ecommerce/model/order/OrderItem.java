package co.ecommerce.model.order;

import co.ecommerce.model.exception.InvalidFieldException;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class OrderItem {

    private final Long id;
    private final String sku;
    private final BigDecimal price;
    private final Integer quantity;

    private OrderItem(Long id, String sku, BigDecimal price, Integer quantity) {
        if (sku == null || sku.isEmpty()) throw new InvalidFieldException("sku cannot be null or empty");
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) throw new InvalidFieldException("price cannot be null or negative");
        if (quantity == null || quantity <= 0) throw new InvalidFieldException("quantity cannot be null or zero");

        this.id = id;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderItem create(String sku, BigDecimal price, Integer quantity) {
        return new OrderItem(null, sku, price, quantity);
    }

    public static OrderItem restore(Long id, String sku, BigDecimal price, Integer quantity) {
        return new OrderItem(id, sku, price, quantity);
    }
}
