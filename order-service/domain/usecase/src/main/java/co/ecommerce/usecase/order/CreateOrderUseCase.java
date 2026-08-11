package co.ecommerce.usecase.order;

import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.OrderItem;
import co.ecommerce.model.order.gateways.OrderRepository;
import co.ecommerce.usecase.order.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;

    public Order execute(String userId, CreateOrderCommand command){
        Order order = Order.create(userId, command.items()
                .stream()
                .map(item -> {
                    inventoryRepository.reduceStock(item.sku(), Inventory.restore(null, item.sku(), item.quantity()));
                    return OrderItem
                            .create(item.sku(), item.price(), item.quantity());
                })
                .toList());
        return orderRepository.save(order);
    }
}
