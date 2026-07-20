package co.ecommerce.api.order.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateOrderRequest(
    @NotEmpty(message = "La orden debe contener al menos un item")
    @Valid
    List<CreateOrderItemRequest> items
) {
}
