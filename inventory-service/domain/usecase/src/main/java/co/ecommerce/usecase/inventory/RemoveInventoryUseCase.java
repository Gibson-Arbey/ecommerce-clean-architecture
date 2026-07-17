package co.ecommerce.usecase.inventory;

import co.ecommerce.model.exception.InvalidFieldException;
import co.ecommerce.model.inventory.exception.InventoryNotFoundException;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoveInventoryUseCase {

    private final InventoryRepository inventoryRepository;

    public void execute(String sku) {
        if(sku == null) throw new InvalidFieldException("sku cannot be null");
        if(!inventoryRepository.existsBysku(sku)){
            throw new InventoryNotFoundException("Inventory with sku " + sku + " does not exist");
        }
        inventoryRepository.deleteBySku(sku);
    }
}
