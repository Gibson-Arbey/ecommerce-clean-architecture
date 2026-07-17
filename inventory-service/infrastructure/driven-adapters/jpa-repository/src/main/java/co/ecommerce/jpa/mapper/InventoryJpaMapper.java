package co.ecommerce.jpa.mapper;

import co.ecommerce.jpa.entity.InventoryJpaEntity;
import co.ecommerce.model.inventory.Inventory;

public class InventoryJpaMapper {

    public static Inventory toDomain(InventoryJpaEntity entity) {
        if(entity == null) return null;
        return Inventory.restore(
                entity.getId(),
                entity.getSku(),
                entity.getQuantity()
        );
    }

    public static InventoryJpaEntity toEntity(Inventory inventory) {
        if(inventory == null) return null;
        return InventoryJpaEntity
                .builder()
                .id(inventory.getId())
                .sku(inventory.getSku())
                .quantity(inventory.getQuantity())
                .build();
    }
}
