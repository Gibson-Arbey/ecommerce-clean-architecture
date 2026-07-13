package co.ecommerce.usecase.product;

import co.ecommerce.model.product.Product;
import co.ecommerce.model.product.gateways.ProductRepository;
import co.ecommerce.usecase.product.command.UpdateProductCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateProductUseCase {

    private final ProductRepository productRepository;

    public Product execute(String id, UpdateProductCommand command){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product not found");
        }
        Product product = Product.restore(id, command.name(), command.description(), command.price());
        return productRepository.save(product);
    }
}
