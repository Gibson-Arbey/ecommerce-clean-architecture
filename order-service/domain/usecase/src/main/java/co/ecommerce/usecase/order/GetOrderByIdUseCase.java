package co.ecommerce.usecase.order;

import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetOrderByIdUseCase {

    private final OrderRepository orderRepository;

    public Order execute(Long id){
        return orderRepository.findById(id);
    }
}
