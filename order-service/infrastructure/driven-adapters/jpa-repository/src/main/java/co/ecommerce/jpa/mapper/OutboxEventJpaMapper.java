package co.ecommerce.jpa.mapper;

import co.ecommerce.jpa.entity.OutboxEventJpaEntity;
import co.ecommerce.model.outboxevent.OutboxEvent;

public class OutboxEventJpaMapper {

    public static OutboxEventJpaEntity toEntity(OutboxEvent event, String payload) {
        if(event == null) {
            return null;
        }
        return OutboxEventJpaEntity.builder()
                .id(event.getId())
                .aggregateId(event.getAggregateId())
                .type(event.getType())
                .payload(payload)
                .createdAt(event.getCreatedAt())
                .processed(event.getProcessed())
                .build();
    }

    public static OutboxEvent toDomain(OutboxEventJpaEntity entity, Object eventPayload) {
        if(entity == null) {
            return null;
        }
        return OutboxEvent.restore(
                entity.getId(),
                entity.getAggregateId(),
                entity.getType(),
                eventPayload,
                entity.getCreatedAt(),
                entity.getProcessed()
        );
    }
}
