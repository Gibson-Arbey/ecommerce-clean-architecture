package co.ecommerce.model.order;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PLACED,
    CONFIRMED,
    CANCELLED;
}