package co.ecommerce.usecase.inventory;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import co.ecommerce.usecase.inventory.command.RegisterInventoryCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterInventoryUseCase {

    private final InventoryRepository inventoryRepository;

    public Inventory execute(RegisterInventoryCommand command) {
        Inventory inventory = Inventory.create(command.sku(), command.quantity());
        return inventoryRepository.save(inventory);
    }}
}
