package co.ecommerce.api.product.mapper;

import co.ecommerce.usecase.product.query.SearchProductsQuery;

import java.math.BigDecimal;

public class QueryProductRequestMapper {

    public static SearchProductsQuery toSearchQuery(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        return new SearchProductsQuery(name, minPrice, maxPrice);
    }
}
