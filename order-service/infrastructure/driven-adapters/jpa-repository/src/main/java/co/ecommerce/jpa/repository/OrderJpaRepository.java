package co.ecommerce.jpa.repository;

import co.ecommerce.jpa.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {

    @Query("SELECT o FROM OrderJpaEntity o WHERE :userId = '' OR o.userId = :userId")
    List<OrderJpaEntity> findAllByUserId(String userId);

    @Query("SELECT o FROM OrderJpaEntity o WHERE o.orderNumber = :orderNumber")
    OrderJpaEntity findByOrderNumber(String orderNumber);

    @Modifying
    @Query("UPDATE OrderJpaEntity o SET o.status = :status WHERE o.orderNumber = :orderNumber")
    int updateStatusByOrderNumber(String orderNumber, String status);
}
