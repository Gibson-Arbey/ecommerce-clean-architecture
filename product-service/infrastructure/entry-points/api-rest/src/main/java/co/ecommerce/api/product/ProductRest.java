package co.ecommerce.api.product;

import co.ecommerce.api.config.ApiPath;
import co.ecommerce.api.product.mapper.ProductResponseMapper;
import co.ecommerce.api.product.mapper.CommandProductRequestMapper;
import co.ecommerce.api.product.mapper.QueryProductRequestMapper;
import co.ecommerce.api.product.request.RegisterProductRequest;
import co.ecommerce.api.product.request.UpdateProductRequest;
import co.ecommerce.api.product.response.ProductResponse;
import co.ecommerce.usecase.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiPath.ROUTE_PRODUCT, version = ApiPath.V1)
public class ProductRest {

    private final RegisterProductUseCase registerProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;

    @PostMapping
    public ResponseEntity<ProductResponse> registerProduct(@RequestBody RegisterProductRequest request) {
        return ResponseEntity
            .status(201)
            .body(ProductResponseMapper
                    .toResponse(registerProductUseCase
                            .execute(CommandProductRequestMapper
                                    .toRegisterCommand(request))));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice
            ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProductResponseMapper
                        .toResponseList(getAllProductsUseCase
                                .execute(QueryProductRequestMapper
                                        .toSearchQuery(name, minPrice, maxPrice))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProductResponseMapper.toResponse(getProductByIdUseCase.execute(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") String id, @RequestBody UpdateProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ProductResponseMapper.toResponse(updateProductUseCase.execute(id, CommandProductRequestMapper.toUpdateCommand(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") String id) {
        deleteProductByIdUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
