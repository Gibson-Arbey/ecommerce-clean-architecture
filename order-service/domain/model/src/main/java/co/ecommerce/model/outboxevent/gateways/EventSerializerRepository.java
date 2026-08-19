package co.ecommerce.model.outboxevent.gateways;

public interface EventSerializerRepository {

    String serialize(Object event);
}
