package co.ecommerce.jpa.adapter;

import co.ecommerce.jpa.mapper.InventoryJpaMapper;
import co.ecommerce.jpa.repository.InventoryJpaRepository;
import co.ecommerce.model.inventory.Inventory;
import co.ecommerce.model.inventory.gateways.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryJpaAdapter implements InventoryRepository {

    private final InventoryJpaRepository  inventoryJpaRepository;

    @Override
    @Transactional
    public Inventory save(Inventory inventory) {
        return InventoryJpaMapper.toDomain(
                inventoryJpaRepository.save(InventoryJpaMapper.toEntity(inventory)));
    }

    @Override
    public List<Inventory> findAllByFilters(String sku, Integer minQuantity, Integer maxQuantity) {
        return inventoryJpaRepository.findBySkuAndQuantityBetween(sku, minQuantity, maxQuantity)
                .stream()
                .map(InventoryJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Inventory findBysku(String sku) {
        return InventoryJpaMapper.toDomain(inventoryJpaRepository.findBysku(sku));
    }

    @Override
    public Inventory findById(Long id) {
        return InventoryJpaMapper.toDomain(inventoryJpaRepository.findById(id).orElse(null));
    }

    @Override
    public boolean existsBysku(String sku) {
        return inventoryJpaRepository.existsBysku(sku);
    }

    @Override
    @Transactional
    public void deleteBySku(String sku) {
        inventoryJpaRepository.deleteBysku(sku);
    }
}
