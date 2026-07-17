package co.ecommerce.usecase.inventory;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.exception.InventoryNotFoundException;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import co.ecommerce.usecase.inventory.command.ReduceStockInventoryCommand;
import co.ecommerce.usecase.inventory.exception.InsufficientStockException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReduceStockInventoryUseCase {

    private final InventoryRepository inventoryRepository;

    public void execute(String sku, ReduceStockInventoryCommand command){
        Inventory inventory = inventoryRepository.findBysku(sku);

        if (inventory == null) {
            throw new InventoryNotFoundException("sku not found");
        }

        int newQuantity = inventory.getQuantity() - command.quantityToReduce();

        if (newQuantity <= 0) {
            throw new InsufficientStockException("Not enough stock to reduce");
        }

        inventoryRepository.save(Inventory.restore(inventory.getId(), inventory.getSku(), newQuantity));
    }
}
