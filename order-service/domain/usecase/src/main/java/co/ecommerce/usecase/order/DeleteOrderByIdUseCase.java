package co.ecommerce.usecase.order;

import co.ecommerce.model.order.exception.OrderNotFoundException;
import co.ecommerce.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteOrderByIdUseCase {

    private final OrderRepository orderRepository;

    public void execute(Long id){
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found");
        }

        orderRepository.deleteById(id);
    }
}
