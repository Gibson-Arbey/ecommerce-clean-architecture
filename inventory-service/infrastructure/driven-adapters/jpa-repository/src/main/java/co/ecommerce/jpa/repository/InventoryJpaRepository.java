package co.ecommerce.jpa.repository;

import co.ecommerce.jpa.entity.InventoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepository extends JpaRepository<InventoryJpaEntity, Long> {
}
