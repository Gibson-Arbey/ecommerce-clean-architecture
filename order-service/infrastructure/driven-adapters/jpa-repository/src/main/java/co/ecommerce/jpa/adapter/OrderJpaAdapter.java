package co.ecommerce.jpa.adapter;

import co.ecommerce.jpa.exception.OrderCreateNotAvaliableException;
import co.ecommerce.jpa.mapper.OrderJpaMapper;
import co.ecommerce.jpa.repository.OrderJpaRepository;
import co.ecommerce.model.order.Order;
import co.ecommerce.model.order.gateways.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RefreshScope
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Value("${order.enable:true}")
    private boolean ordersEnabled;
    @Override
    @Transactional
    public Order save(Order order) {
        if(!ordersEnabled) {
            log.warn("OrderJpaAdapter save not enabled");
            throw new OrderCreateNotAvaliableException("Save order not enabled");
        }
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
