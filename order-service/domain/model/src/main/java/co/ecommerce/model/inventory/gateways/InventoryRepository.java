package co.ecommerce.model.inventory.gateways;

import co.ecommerce.model.inventory.Inventory;

public interface InventoryRepository {

    void reduceStock(String sku, Inventory inventory);
}
