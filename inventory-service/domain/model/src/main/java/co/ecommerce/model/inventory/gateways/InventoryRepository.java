package co.ecommerce.model.inventory.gateways;

import co.ecommerce.model.inventory.Inventory;

import java.util.List;

public interface InventoryRepository {

    Inventory save(Inventory inventory);

    List<Inventory> findAll();

    Inventory findById(Long id);

    void deleteById(Long id);
}
