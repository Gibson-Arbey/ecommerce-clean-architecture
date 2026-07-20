package co.ecommerce.usecase.order.command;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderCommand(List<CreateOrderItemCommand> items) {
}
