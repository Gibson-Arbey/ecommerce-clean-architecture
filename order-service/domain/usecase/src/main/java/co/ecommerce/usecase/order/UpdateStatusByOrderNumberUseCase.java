package co.ecommerce.usecase.order;

import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.OrderStatus;
import co.ecommerce.model.order.exception.OrderNotFoundException;
import co.ecommerce.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateStatusByOrderNumberUseCase {

    private final OrderRepository orderRepository;

    public void execute(String orderNumber, String status) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if(order == null) {
            throw new OrderNotFoundException("Order not found with order number: " + orderNumber);
        }

        OrderStatus.from(status);

        orderRepository.updateStatusByOrderNumber(orderNumber, status);
    }

}
