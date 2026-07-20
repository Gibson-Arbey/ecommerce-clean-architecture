package co.ecommerce.jpa.adapter;

import co.ecommerce.jpa.mapper.OrderJpaMapper;
import co.ecommerce.jpa.repository.OrderJpaRepository;
import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    @Transactional
    public Order save(Order order) {
        return OrderJpaMapper.toDomain(orderJpaRepository.save(OrderJpaMapper.toEntity(order)));
    }

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll()
                .stream()
                .map(OrderJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Order findById(Long id) {
        return orderJpaRepository.findById(id)
                .map(OrderJpaMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        orderJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return orderJpaRepository.existsById(id);
    }
}
