package co.ecommerce.mongo.mapper;

import co.ecommerce.model.product.Product;
import co.ecommerce.mongo.entity.ProductMongoEntity;

public class ProductMongoMapper {

    public static Product toDomain(ProductMongoEntity document) {
        if(document == null) return null;
        return Product.restore(
                document.getId(),
                document.getName(),
                document.getDescription(),
                document.getPrice()
        );
    }

    public static ProductMongoEntity toDocument(Product product) {
        if(product == null) return null;
        return ProductMongoEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
