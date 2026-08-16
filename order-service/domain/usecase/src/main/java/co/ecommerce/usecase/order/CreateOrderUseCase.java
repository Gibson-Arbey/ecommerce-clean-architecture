package co.ecommerce.usecase.order;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.OrderItem;
import co.ecommerce.model.order.gateways.OrderRepository;
import co.ecommerce.model.orderevent.OrderPlacedEvent;
import co.ecommerce.model.orderevent.gateways.OrderEventRepository;
import co.ecommerce.usecase.order.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderEventRepository orderEventRepository;

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

        orderEventRepository.publishOrderPlaced(event);

        return savedOrder;
    }
}
