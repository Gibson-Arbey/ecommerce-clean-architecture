package co.ecommerce.usecase.inventory;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetInventoryBySkuUseCase {

    private final InventoryRepository inventoryRepository;

    public Inventory execute(String sku) {
        return inventoryRepository.findBySku(sku);
    }
}
