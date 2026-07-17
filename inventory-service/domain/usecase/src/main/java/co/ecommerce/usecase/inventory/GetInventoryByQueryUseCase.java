package co.ecommerce.usecase.inventory;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import co.ecommerce.usecase.inventory.query.SearchInventoriesQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetInventoryByQueryUseCase {

    private final InventoryRepository inventoryRepository;

    public List<Inventory> execute(SearchInventoriesQuery query) {
        return inventoryRepository.findAllByFilters(query.sku(), query.minQuantity(), query.maxQuantity());
    }
}
