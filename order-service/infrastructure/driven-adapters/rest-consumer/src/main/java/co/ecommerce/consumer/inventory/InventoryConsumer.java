package co.ecommerce.consumer.inventory;

import co.ecommerce.consumer.inventory.exception.InventoryConsumerException;
import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class InventoryConsumer implements InventoryRepository {

    private final WebClient inventoryWebClient;

    public InventoryConsumer(
            @Qualifier("inventoryWebClient") WebClient inventoryWebClient
    ) {
        this.inventoryWebClient = inventoryWebClient;
    }

    @Override
    @Retry(name = "inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "reduceStockFallback")
    public void reduceStock(String sku, Inventory inventory) {

        inventoryWebClient.patch()
                .uri("/api/inventory/reduce/{sku}", sku)
                .bodyValue(inventory)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void reduceStockFallback(String sku, Inventory inventory, Throwable throwable) {
        log.error("Error reducing stock for SKU: {}. Inventory: {}, message: {}", sku, inventory, throwable.getMessage());
        throw new InventoryConsumerException("Error reducing stock for SKU: " + sku + ". Inventory: " + inventory + ". Message: " + throwable.getMessage());
    }
}
