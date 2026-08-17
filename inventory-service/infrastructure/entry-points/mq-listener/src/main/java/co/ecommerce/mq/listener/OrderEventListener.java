package co.ecommerce.mq.listener;

import co.ecommerce.mq.event.OrderPlacedEvent;
import co.ecommerce.usecase.inventory.ReduceStockInventoryUseCase;
import co.ecommerce.usecase.inventory.command.ReduceStockInventoryCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final ReduceStockInventoryUseCase reduceStockInventoryUseCase;

    @RabbitListener(queues = "inventory-queue")
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        log.info("Evento recibido en Inventario para Orden: {}", event.orderNumber());
        event.items().forEach(item -> {
            try {
                reduceStockInventoryUseCase.execute(item.sku(), new ReduceStockInventoryCommand(item.quantity()));
                log.info("Stock descontado para SKU: {} - cantidad: {}", item.sku(), item.quantity());
            } catch (Exception e) {
                log.error("Error reduciendo {} stock: {}", item.sku(), e.getMessage());
            }
        });
    }
}
