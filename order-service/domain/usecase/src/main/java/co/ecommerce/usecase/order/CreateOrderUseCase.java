package co.ecommerce.usecase.order;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.OrderItem;
import co.ecommerce.model.order.gateways.OrderRepository;
import co.ecommerce.model.orderevent.OrderPlacedEvent;
import co.ecommerce.model.orderevent.gateways.OrderEventRepository;
import co.ecommerce.model.outboxevent.OutboxEvent;
import co.ecommerce.model.outboxevent.gateways.OutboxEventRepository;
import co.ecommerce.usecase.order.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderEventRepository orderEventRepository;
    private final OutboxEventRepository outboxEventRepository;

    public Order execute(String userId, CreateOrderCommand command){
        Order order = Order.create(userId, command.items()
                .stream()
                .map(item -> {
                    // Se cambia la logica para que use amqp
                    // inventoryRepository.reduceStock(item.sku(), Inventory.restore(null, item.sku(), item.quantity()));
                    return OrderItem
                            .create(item.sku(), item.price(), item.quantity());
                })
                .toList());

        Order savedOrder = orderRepository.save(order);

        OrderPlacedEvent event =
                OrderPlacedEvent.from(savedOrder, command.email());

        boolean eventPublished = orderEventRepository.publishOrderPlaced(event);

        outboxEventRepository.saveOrderPlacedEvent(
                OutboxEvent.create(order.getOrderNumber(),"ORDER_PLACED", event, LocalDateTime.now(), eventPublished)
        );

        return savedOrder;
    }
}
