package co.ecommerce.usecase.product.command;

import java.math.BigDecimal;

public record RegisterProductCommand(String name, String description, BigDecimal price) {
}
