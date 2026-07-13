package co.ecommerce.usecase.product.query;

import java.math.BigDecimal;

public record SearchProductsQuery(String name, BigDecimal minPrice, BigDecimal maxPrice) {

    public SearchProductsQuery {
        if (name == null) {
            name = "";
        }
        if (minPrice == null) {
            minPrice = BigDecimal.ZERO;
        }
        if (maxPrice == null) {
            maxPrice = BigDecimal.valueOf(Double.MAX_VALUE);
        }
    }
}
