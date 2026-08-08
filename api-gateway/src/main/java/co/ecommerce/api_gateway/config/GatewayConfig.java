package co.ecommerce.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r
                        .path("/api/product/**")
                        .uri("lb://PRODUCTSERVICE"))
                .route("order-service", r -> r
                        .path("/api/order/**")
                        .uri("lb://ORDERSERVICE"))
                .route("inventory-service", r -> r
                        .path("/api/inventory/**")
                        .uri("lb://INVENTORYSERVICE"))
                .build();
    }
}
