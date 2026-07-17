package co.ecommerce.api.inventory.mapper;

import co.ecommerce.api.inventory.response.InventoryResponse;
import co.ecommerce.model.inventory.Inventory;

import java.util.List;

public class InventoryResponseMapper {

    public static InventoryResponse toResponse(Inventory inventory) {
        return new co.ecommerce.api.inventory.response.InventoryResponse(
                inventory.getId(),
                inventory.getSku(),
                inventory.getQuantity()
        );
    }

    public static List<InventoryResponse> toResponseList(List<Inventory> inventories) {
        return inventories.stream()
                .map(InventoryResponseMapper::toResponse)
                .toList();
    }
}
