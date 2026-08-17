package co.ecommerce.mq.listener;

import co.ecommerce.model.order.OrderStatus;
import co.ecommerce.model.orderevent.OrderPlacedEvent;
import co.ecommerce.usecase.order.UpdateStatusByOrderNumberUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final UpdateStatusByOrderNumberUseCase updateOrderStatusByOrderNumberUseCase;

    @RabbitListener(queues = "order-confirmed-queue")
    public void handleOrderConfirmed(OrderPlacedEvent event){
        updateOrderStatusByOrderNumberUseCase.execute(event.orderNumber(), OrderStatus.CONFIRMED.name());
    }

    @RabbitListener(queues = "order-cancelled-queue")
    public void handleOrderCancelled(OrderPlacedEvent event) {
        updateOrderStatusByOrderNumberUseCase.execute(event.orderNumber(), OrderStatus.CANCELLED.name());
    }
}