package co.ecommerce.model.inventory;

import lombok.Getter;

@Getter
public class Inventory {

    private final Long id;
    private final String sku;
    private final Integer quantity;

    private Inventory(Long id, String sku, Integer quantity) {
        this.id = id;
        this.sku = sku;
        this.quantity = quantity;
    }

    public static Inventory restore(Long id, String sku, Integer quantity) {
        return new Inventory(id, sku, quantity);
    }
}
