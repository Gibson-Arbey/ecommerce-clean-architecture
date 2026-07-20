package co.ecommerce.model.order.gateways;

import co.ecommerce.model.order.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    List<Order> findAll();

    Order findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);
}
