package co.ecommerce.model.product.gateways;

import co.ecommerce.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    List<Product> findAll(String name, BigDecimal minPrice, BigDecimal maxPrice);

    Product findById(String id);

    void deleteById(String id);

    boolean existsById(String id);
}
