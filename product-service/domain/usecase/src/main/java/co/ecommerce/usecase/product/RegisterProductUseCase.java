package co.ecommerce.usecase.product;

import co.ecommerce.model.product.Product;
import co.ecommerce.model.product.gateways.ProductRepository;
import co.ecommerce.usecase.product.command.RegisterProductCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterProductUseCase {

    private final ProductRepository productRepository;

    public Product execute(RegisterProductCommand command) {
        Product product = Product.create(command.name(), command.description(), command.price());
        return productRepository.save(product);
    }
}
