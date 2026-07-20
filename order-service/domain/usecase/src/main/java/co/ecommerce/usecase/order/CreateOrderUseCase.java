package co.ecommerce.usecase.order;

import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.OrderItem;
import co.ecommerce.model.order.gateways.OrderRepository;
import co.ecommerce.usecase.order.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public Order execute(CreateOrderCommand command){
        Order order = Order.create(command.items()
                .stream()
                .map(item -> OrderItem
                        .create(item.sku(), item.price(), item.quantity()))
                .toList());
        return orderRepository.save(order);
    }
}
