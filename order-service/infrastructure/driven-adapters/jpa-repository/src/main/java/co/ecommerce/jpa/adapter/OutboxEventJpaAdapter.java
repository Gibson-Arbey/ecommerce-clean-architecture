package co.ecommerce.jpa.adapter;

import co.ecommerce.jpa.mapper.OutboxEventJpaMapper;
import co.ecommerce.jpa.repository.OutboxEventJpaRepository;
import co.ecommerce.model.outboxevent.OutboxEvent;
import co.ecommerce.model.outboxevent.gateways.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OutboxEventJpaAdapter implements OutboxEventRepository {

    private final OutboxEventJpaRepository outboxEventJpaRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void saveOrderPlacedEvent(OutboxEvent event) {
        String payload =
                objectMapper.convertValue(event.getPayload(), String.class);
        outboxEventJpaRepository.save(OutboxEventJpaMapper.toEntity(event, payload));
    }

    @Override
    public List<OutboxEvent> getPendingEvents() {
        return outboxEventJpaRepository.findByProcessedFalse()
            .stream()
            .map(entity -> OutboxEventJpaMapper.toDomain(entity, objectMapper.convertValue(entity.getPayload(), Object.class)))
            .toList();
    }

    @Override
    @Transactional
    public void markAsProcessed(Long id) {
        outboxEventJpaRepository.findById(id).ifPresent(entity -> {
            entity.setProcessed(true);
            outboxEventJpaRepository.save(entity);
        });
    }
}
