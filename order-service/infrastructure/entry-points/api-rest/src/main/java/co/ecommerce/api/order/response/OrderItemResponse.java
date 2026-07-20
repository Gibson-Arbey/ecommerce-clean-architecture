package co.ecommerce.api.order.response;

import java.math.BigDecimal;

public record OrderItemResponse(Long id, String sku, BigDecimal price, Integer quantity) {
}
