package co.ecommerce.usecase.inventory;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.exception.InventoryNotFoundException;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import co.ecommerce.usecase.inventory.command.UpdateInventoryCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateInventoryUseCase {

    private final InventoryRepository inventoryRepository;

    public Inventory execute(Long id, UpdateInventoryCommand command){
        Inventory  inventoryExisting = inventoryRepository.findById(id);

        if(inventoryExisting == null){
            throw new InventoryNotFoundException("Inventory not found");
        }

        if(command.quantity() == null || command.quantity() < inventoryExisting.getQuantity()){
            throw new IllegalArgumentException("Invalid command parameters");
        }

        return inventoryRepository.save(Inventory.restore(inventoryExisting.getId(), command.sku(), command.quantity()));
    }
}
