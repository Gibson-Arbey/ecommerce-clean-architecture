package co.ecommerce.consumer.inventory;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class InventoryConsumer implements InventoryRepository {

    private final WebClient inventoryWebClient;

    public InventoryConsumer(
            @Qualifier("inventoryWebClient") WebClient inventoryWebClient
    ) {
        this.inventoryWebClient = inventoryWebClient;
    }

    @Override
    public void reduceStock(String sku, Inventory inventory) {

        inventoryWebClient.patch()
                .uri("/inventory/{sku}", sku)
                .bodyValue(inventory)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
