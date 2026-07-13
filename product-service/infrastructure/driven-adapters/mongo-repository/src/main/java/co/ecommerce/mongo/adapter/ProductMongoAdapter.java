package co.ecommerce.mongo.adapter;

import co.ecommerce.model.product.Product;
import co.ecommerce.model.product.gateways.ProductRepository;
import co.ecommerce.mongo.mapper.ProductMapper;
import co.ecommerce.mongo.repository.ProductMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductMongoAdapter implements ProductRepository {

    private final ProductMongoRepository productMongoRepository;

    @Override
    @Transactional
    public Product save(Product product) {
        return ProductMapper
                .toDomain(
                        productMongoRepository.save(ProductMapper.toDocument(product)));
    }

    @Override
    public List<Product> findAll(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        return productMongoRepository.searchProducts(name, minPrice, maxPrice)
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public Product findById(String id) {
        return ProductMapper
                .toDomain(productMongoRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        productMongoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return productMongoRepository.existsById(id);
    }

}
