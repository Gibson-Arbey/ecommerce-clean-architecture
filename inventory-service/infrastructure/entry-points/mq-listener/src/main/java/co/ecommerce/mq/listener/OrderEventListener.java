package co.ecommerce.mq.listener;

import co.ecommerce.model.inventoryevent.OrderPlacedEvent;
import co.ecommerce.usecase.processorderinventory.ProcessOrderInventoryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final ProcessOrderInventoryUseCase processOrderInventoryUseCase;

    @RabbitListener(queues = "inventory-queue")
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        log.info("Evento recibido en Inventario para Orden: {}", event.orderNumber());
        processOrderInventoryUseCase.execute(event);
    }
}
