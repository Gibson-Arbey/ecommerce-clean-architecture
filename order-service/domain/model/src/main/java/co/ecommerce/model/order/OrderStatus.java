package co.ecommerce.model.order;

import co.ecommerce.model.order.exception.InvalidOrderStatusException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OrderStatus {
    PLACED,
    CONFIRMED,
    CANCELLED;

    public static OrderStatus from(String status) {
        if (status == null || status.isBlank()) {
            throw new InvalidOrderStatusException(status);
        }

        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(status.trim()))
                .findFirst()
                .orElseThrow(() -> new InvalidOrderStatusException(status));
    }
}