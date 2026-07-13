package co.ecommerce.usecase.product.command;

import java.math.BigDecimal;

public record UpdateProductCommand(String name, String description, BigDecimal price) {
}
