package co.ecommerce.usecase.product;

import co.ecommerce.model.product.Product;
import co.ecommerce.model.product.exception.ProductNotFoundException;
import co.ecommerce.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetProductByIdUseCase {

    private final ProductRepository productRepository;

    public Product execute(String id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        return product;
    }
}
