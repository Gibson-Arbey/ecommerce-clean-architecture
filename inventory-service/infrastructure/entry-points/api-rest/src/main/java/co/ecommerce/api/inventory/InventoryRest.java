package co.ecommerce.api.inventory;

import co.ecommerce.api.config.ApiPath;
import co.ecommerce.api.inventory.mapper.CommandInventoryRequestMapper;
import co.ecommerce.api.inventory.mapper.InventoryResponseMapper;
import co.ecommerce.api.inventory.request.ReduceStockInventoryRequest;
import co.ecommerce.api.inventory.request.RegisterInventoryRequest;
import co.ecommerce.api.inventory.request.UpdateInventoryRequest;
import co.ecommerce.api.inventory.response.InventoryResponse;
import co.ecommerce.usecase.inventory.*;
import co.ecommerce.usecase.inventory.query.SearchInventoriesQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiPath.ROUTE_INVENTORY, version = ApiPath.V1)
public class InventoryRest {

    private final RegisterInventoryUseCase registerInventoryUseCase;
    private final GetInventoryByQueryUseCase getInventoryByQueryUseCase;
    private final GetInventoryBySkuUseCase getInventoryBySkuUseCase;
    private final UpdateInventoryUseCase updateInventoryUseCase;
    private final ReduceStockInventoryUseCase reduceStockInventoryUseCase;
    private final RemoveInventoryUseCase removeInventoryUseCase;

    @PostMapping
    public ResponseEntity<InventoryResponse> registerInventory(@RequestBody RegisterInventoryRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(InventoryResponseMapper
                        .toResponse(registerInventoryUseCase
                                .execute(CommandInventoryRequestMapper
                                        .toRegisterInventoryCommand(request))));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getInventoryByQuery(
            @RequestParam(name = "sku", required = false) String sku,
            @RequestParam(name = "minQuantity", required = false) Integer minQuantity,
            @RequestParam(name = "maxQuantity", required = false) Integer maxQuantity
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(InventoryResponseMapper
                        .toResponseList(getInventoryByQueryUseCase
                                .execute(new SearchInventoriesQuery(sku, minQuantity, maxQuantity))));
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<InventoryResponse> getInventoryBySku(@PathVariable("sku") String sku) {
        return ResponseEntity.status(HttpStatus.OK).body(InventoryResponseMapper.toResponse(getInventoryBySkuUseCase.execute(sku)));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(@PathVariable("id") String id, @RequestBody UpdateInventoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(InventoryResponseMapper.toResponse(updateInventoryUseCase.execute(id, CommandInventoryRequestMapper.toUpdateInventoryCommand(request))));
    }

    @PatchMapping("/sku/{sku}")
    public ResponseEntity<Void> reduceStockInventory(@PathVariable("sku") String sku, @RequestBody ReduceStockInventoryRequest request) {
        reduceStockInventoryUseCase.execute(sku, CommandInventoryRequestMapper.toReduceInventoryCommand(request));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/sku/{sku}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("sku") String sku) {
        removeInventoryUseCase.execute(sku);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
