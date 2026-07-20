package co.ecommerce.usecase.order;

import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.gateways.OrderRepository;
import co.ecommerce.usecase.order.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public Order execute(CreateOrderCommand command){
        // Implementation for creating an order
        return null;
    }
}
