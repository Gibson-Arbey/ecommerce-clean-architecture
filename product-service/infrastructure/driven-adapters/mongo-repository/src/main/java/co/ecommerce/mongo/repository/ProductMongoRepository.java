package co.ecommerce.mongo.repository;

import co.ecommerce.mongo.entity.ProductMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductMongoRepository extends MongoRepository<ProductMongoEntity, String> {

    @Query("""
    {
        '$and': [
            { 'name': { '$regex': ?0, '$options': 'i' } },
            { 'price': { '$gte': ?1, '$lte': ?2 } }
        ]
    }
    """)
    List<ProductMongoEntity> searchProducts(
            String name,
            BigDecimal minPrice,
            BigDecimal maxPrice
    );
}
