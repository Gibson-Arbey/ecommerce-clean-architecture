package co.ecommerce.mq.sender.publisher;

import co.ecommerce.model.orderevent.OrderPlacedEvent;
import co.ecommerce.model.orderevent.gateways.OrderEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventAdapter implements OrderEventRepository {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishOrderPlaced(OrderPlacedEvent event) {
        rabbitTemplate.convertAndSend("order-events", "order.placed", event);
    }
}
