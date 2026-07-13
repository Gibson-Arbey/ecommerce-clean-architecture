package co.ecommerce.model.inventory;

import co.ecommerce.model.exception.InvalidFieldException;
import lombok.Getter;

@Getter
public class Inventory {

    private final Long id;

    private final String sku;

    private final Integer quantity;

    private Inventory(Long id, String sku, Integer quantity) {

        if(sku == null) throw new InvalidFieldException("sku cannot be null");
        if(quantity == null || quantity < 0) throw new InvalidFieldException("quantity cannot be negative");

        this.id = id;
        this.sku = sku;
        this.quantity = quantity;
    }

    public static Inventory create(String sku, Integer quantity) {
        return new Inventory(null, sku, quantity);
    }

    public static Inventory restore(Long id, String sku, Integer quantity) {
        return new Inventory(id, sku, quantity);
    }
}
