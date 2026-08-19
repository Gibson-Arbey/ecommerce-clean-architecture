package co.ecommerce.model.outboxevent.gateways;

import co.ecommerce.model.orderevent.OrderPlacedEvent;
import co.ecommerce.model.outboxevent.OutboxEvent;

import java.util.List;

public interface OutboxEventRepository {

    void saveOrderPlacedEvent(OutboxEvent event);

    List<OutboxEvent> getPendingEvents();

    void markAsProcessed(Long id);
}
