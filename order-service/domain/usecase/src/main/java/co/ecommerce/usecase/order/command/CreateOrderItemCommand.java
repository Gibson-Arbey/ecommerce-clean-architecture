package co.ecommerce.usecase.order.command;

import java.math.BigDecimal;

public record CreateOrderItemCommand(String sku, BigDecimal price, Integer quantity) {
}
