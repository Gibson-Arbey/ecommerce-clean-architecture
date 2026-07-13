package co.ecommerce.api.inventory.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegisterInventoryRequest(
        @NotBlank(message = "SKU is required")
        String sku,

        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be a positive number")
        Integer quantity) {
}
