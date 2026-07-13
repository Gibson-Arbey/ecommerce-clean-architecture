package co.ecommerce.usecase.product;

import co.ecommerce.model.product.exception.ProductNotFoundException;
import co.ecommerce.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProductByIdUseCase {

    private final ProductRepository productRepository;

    public void execute(String id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
