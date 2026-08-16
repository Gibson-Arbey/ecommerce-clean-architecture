package co.ecommerce.api.order.mapper;

import co.ecommerce.api.order.request.CreateOrderRequest;
import co.ecommerce.usecase.order.command.CreateOrderCommand;
import co.ecommerce.usecase.order.command.CreateOrderItemCommand;

public class CommandOrderRequestMapper {

    public static CreateOrderCommand toCreateOrderCommand(CreateOrderRequest request) {
        if(request == null || request.items() == null || request.items().isEmpty()) return null;
        var items = request.items().stream()
                .map(item -> new CreateOrderItemCommand(
                        item.sku(),
                        item.price(),
                        item.quantity()
                ))
                .toList();
        return new CreateOrderCommand(items, request.email());
    }
}
