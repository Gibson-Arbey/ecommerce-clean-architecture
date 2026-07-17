package co.ecommerce.jpa.repository;

import co.ecommerce.jpa.entity.InventoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryJpaRepository extends JpaRepository<InventoryJpaEntity, Long> {

    @Query("""
        SELECT i FROM InventoryJpaEntity i
        WHERE (:sku IS NULL OR i.sku = :sku)
        AND (:minQuantity IS NULL OR i.quantity >= :minQuantity)
        AND (:maxQuantity IS NULL OR i.quantity <= :maxQuantity)
    """)
    List<InventoryJpaEntity> findBySkuAndQuantityBetween(
            @Param("sku") String sku,
            @Param("minQuantity") Integer minQuantity,
            @Param("maxQuantity") Integer maxQuantity);

    InventoryJpaEntity findBysku(String sku);

    boolean existsBysku(String sku);

    @Modifying
    @Query("""
        DELETE FROM InventoryJpaEntity i
        WHERE i.sku = :sku
    """)
    void  deleteBysku(@Param("sku") String sku);
}
