package co.ecommerce.mq.sender.publisher;

import co.ecommerce.model.inventoryevent.OrderCancelledEvent;
import co.ecommerce.model.inventoryevent.OrderConfirmedEvent;
import co.ecommerce.model.inventoryevent.gateways.InventoryEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventAdapter implements InventoryEventRepository {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishOrderCancelled(OrderCancelledEvent event) {
        rabbitTemplate.convertAndSend("order-events", "order.cancelled", event);
    }

    @Override
    public void publishOrderConfirmed(OrderConfirmedEvent event) {
        rabbitTemplate.convertAndSend("order-events", "order.confirmed", event);
    }
}
