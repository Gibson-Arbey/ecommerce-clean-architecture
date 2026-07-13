package co.ecommerce.usecase.product;

import co.ecommerce.model.product.Product;
import co.ecommerce.model.product.gateways.ProductRepository;
import co.ecommerce.usecase.product.query.SearchProductsQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public List<Product> execute(SearchProductsQuery query) {
        return productRepository.findAll(
                query.name(),
                query.minPrice(),
                query.maxPrice()
        );
    }
}
