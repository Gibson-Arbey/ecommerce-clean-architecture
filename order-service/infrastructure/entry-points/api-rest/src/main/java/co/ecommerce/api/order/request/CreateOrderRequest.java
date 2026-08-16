package co.ecommerce.api.order.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
    @NotEmpty(message = "La orden debe contener al menos un item")
    @Valid
    List<CreateOrderItemRequest> items,

    @NotNull(message = "El email es requerido")
    @Email(message = "El formato del email no es válido")
    String email
) {
}
