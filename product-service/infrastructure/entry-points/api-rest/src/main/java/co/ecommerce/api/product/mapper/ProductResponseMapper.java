package co.ecommerce.api.product.mapper;

import co.ecommerce.api.product.response.ProductResponse;
import co.ecommerce.model.product.Product;

import java.util.List;

public class ProductResponseMapper {

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public static List<ProductResponse> toResponseList(java.util.List<Product> products) {
        return products.stream()
                .map(ProductResponseMapper::toResponse)
                .toList();
    }
}
