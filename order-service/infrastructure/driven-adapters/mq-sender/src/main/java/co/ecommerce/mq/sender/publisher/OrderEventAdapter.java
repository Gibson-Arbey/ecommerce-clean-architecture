package co.ecommerce.mq.sender.publisher;

import co.ecommerce.model.orderevent.OrderPlacedEvent;
import co.ecommerce.model.orderevent.gateways.OrderEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventAdapter implements OrderEventRepository {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public boolean publishOrderPlaced(OrderPlacedEvent event) {
        try {
            rabbitTemplate.convertAndSend("order-events", "order.placed", event);
            return true;
        } catch (
        AmqpException e) {
            log.error("⚠️ RabbitMQ caído. El Outbox asegurará el envío posterior para la orden: {}", event.orderNumber(), e);
            return false;
        }
    }
}
