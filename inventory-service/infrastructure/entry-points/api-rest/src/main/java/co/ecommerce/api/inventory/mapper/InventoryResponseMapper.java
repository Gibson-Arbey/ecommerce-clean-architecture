package co.ecommerce.api.inventory.mapper;

import co.ecommerce.api.inventory.response.InventoryResponse;
import co.ecommerce.model.inventory.Inventory;

public class InventoryResponseMapper {

    public static InventoryResponse toInventoryResponse(Inventory inventory) {
        return new co.ecommerce.api.inventory.response.InventoryResponse(
                inventory.getId(),
                inventory.getSku(),
                inventory.getQuantity()
        );
    }
}
