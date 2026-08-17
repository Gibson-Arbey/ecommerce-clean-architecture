package co.ecommerce.usecase.processorderinventory;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import co.ecommerce.model.inventoryconfiguration.InventoryConfiguration;
import co.ecommerce.model.inventoryconfiguration.gateways.InventoryConfigurationRepository;
import co.ecommerce.model.inventoryevent.OrderCancelledEvent;
import co.ecommerce.model.inventoryevent.OrderConfirmedEvent;
import co.ecommerce.model.inventoryevent.OrderPlacedEvent;
import co.ecommerce.model.inventoryevent.gateways.InventoryEventRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessOrderInventoryUseCase {

    private final InventoryRepository inventoryRepository;
    private final InventoryConfigurationRepository inventoryConfigurationRepository;
    private final InventoryEventRepository inventoryEventRepository;

    public void execute(OrderPlacedEvent event) {

        InventoryConfiguration configuration =
                inventoryConfigurationRepository.findInventoryConfiguration();

        boolean allProductsInStock;

        if(configuration.getAllowBackorders()) {

            allProductsInStock = true;

        } else {

            allProductsInStock =
                    event.items().stream()
                            .allMatch(item -> {

                                Inventory inventory =
                                        inventoryRepository.findBySku(item.sku());

                                return inventory.getQuantity() >= item.quantity();
                            });
        }

        if(!allProductsInStock) {

            inventoryEventRepository.publishOrderCancelled(
                    new OrderCancelledEvent(
                            event.orderNumber(),
                            event.email(),
                            "Stock insuficiente en uno o más productos"
                    )
            );

            return;
        }

        event.items().forEach(item ->
                inventoryRepository.reduceStock(
                        item.sku(),
                        item.quantity()
                )
        );

        inventoryEventRepository.publishOrderConfirmed(
                new OrderConfirmedEvent(
                        event.orderNumber(),
                        event.email()
                )
        );
    }
}
