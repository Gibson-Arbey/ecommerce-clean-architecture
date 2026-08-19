package co.ecommerce.model.outboxevent;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OutboxEvent {

    private Long id;

    private final String aggregateId; // Guardaremos el orderNumber
    private final String type;        // Identificador del evento (ORDER_PLACED)

    private final Object payload;     // El objeto del evento, que luego será serializado a JSON String

    private final LocalDateTime createdAt;
    private final Boolean processed;

    private OutboxEvent(Long id, String aggregateId, String type, Object payload, LocalDateTime createdAt, Boolean processed) {

        this.id = id;
        this.aggregateId = aggregateId;
        this.type = type;
        this.payload = payload;
        this.createdAt = createdAt;
        this.processed = processed;
    }

    public static OutboxEvent create(String aggregateId, String type, Object payload, LocalDateTime createdAt, Boolean processed) {
        return new OutboxEvent(null, aggregateId, type, payload, createdAt, processed);
    }

    public static OutboxEvent restore(Long id, String aggregateId, String type, Object payload, LocalDateTime createdAt, Boolean processed) {
        return new OutboxEvent(id, aggregateId, type, payload, createdAt, processed);
    }
}
