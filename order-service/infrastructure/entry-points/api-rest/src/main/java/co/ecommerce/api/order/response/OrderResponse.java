package co.ecommerce.api.order.response;

import java.util.List;

public record OrderResponse(Long id, String orderNumber, List<OrderItemResponse> items) {
}
