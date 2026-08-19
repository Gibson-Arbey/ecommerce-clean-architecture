package co.ecommerce.usecase.outboxevent;

import co.ecommerce.model.orderevent.OrderPlacedEvent;
import co.ecommerce.model.orderevent.gateways.OrderEventRepository;
import co.ecommerce.model.outboxevent.OutboxEvent;
import co.ecommerce.model.outboxevent.gateways.OutboxEventRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProcessOutboxEventsUseCase {

    private final OutboxEventRepository outboxEventRepository;
    private final OrderEventRepository orderEventRepository;

    public void execute() {
        List<OutboxEvent> pendingEvents =
                outboxEventRepository.getPendingEvents();

        for (OutboxEvent event : pendingEvents) {

            try {
                OrderPlacedEvent orderPlacedEvent =
                        (OrderPlacedEvent) event.getPayload();
                orderEventRepository.publishOrderPlaced(orderPlacedEvent);

                outboxEventRepository.markAsProcessed(
                        event.getId()
                );

            } catch (Exception e) {
                // queda pendiente para el próximo ciclo
            }
        }
    }
}
