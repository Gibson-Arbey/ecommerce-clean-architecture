package co.ecommerce.usecase.order;

import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllOrdersByUserIdUseCase {

    private final OrderRepository orderRepository;

    public List<Order> execute(String userId, boolean isAdmin) {
        return orderRepository.findAllByUserId(isAdmin ? "" : userId);
    }
}
