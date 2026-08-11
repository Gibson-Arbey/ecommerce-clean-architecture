package co.ecommerce.api.order;

import co.ecommerce.api.config.ApiPath;
import co.ecommerce.api.order.mapper.CommandOrderRequestMapper;
import co.ecommerce.api.order.mapper.OrderResponseMapper;
import co.ecommerce.api.order.request.CreateOrderRequest;
import co.ecommerce.api.order.response.OrderResponse;
import co.ecommerce.api.util.JwtUtil;
import co.ecommerce.usecase.order.CreateOrderUseCase;
import co.ecommerce.usecase.order.DeleteOrderByIdUseCase;
import co.ecommerce.usecase.order.GetAllOrdersByUserIdUseCase;
import co.ecommerce.usecase.order.GetOrderByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiPath.ROUTE_ORDER, version = ApiPath.V1)
public class OrderRest {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetAllOrdersByUserIdUseCase getAllOrdersByUserIdUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final DeleteOrderByIdUseCase deleteOrderByIdUseCase;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request, @AuthenticationPrincipal Jwt jwt) {
        String userId = JwtUtil.getUserId(jwt);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(OrderResponseMapper
                        .toOrderResponse(createOrderUseCase
                                .execute(userId, CommandOrderRequestMapper
                                        .toCreateOrderCommand(request))));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(@AuthenticationPrincipal Jwt jwt) {
        String userId = JwtUtil.getUserId(jwt);
        boolean isAdmin = JwtUtil.hasRole(jwt, "ADMIN");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(OrderResponseMapper
                        .toOrderResponseList(getAllOrdersByUserIdUseCase.execute(userId, isAdmin)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(OrderResponseMapper
                        .toOrderResponse(getOrderByIdUseCase.execute(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        deleteOrderByIdUseCase.execute(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
