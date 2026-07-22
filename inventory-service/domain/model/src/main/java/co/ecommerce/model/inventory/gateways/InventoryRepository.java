package co.ecommerce.model.inventory.gateways;

import co.ecommerce.model.inventory.Inventory;

import java.util.List;

public interface InventoryRepository {

    Inventory save(Inventory inventory);

    List<Inventory> findAllByFilters(String sku, Integer minQuantity, Integer maxQuantity);

    Inventory findBysku(String sku);

    Inventory findById(Long id);

    boolean existsBysku(String sku);

    void deleteBySku(String sku);
}
