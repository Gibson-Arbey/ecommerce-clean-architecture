package co.ecommerce.jpa.repository;

import co.ecommerce.jpa.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {

    @Query("SELECT o FROM OrderJpaEntity o WHERE :userId = '' OR o.userId = :userId")
    List<OrderJpaEntity> findAllByUserId(String userId);
}
