package co.ecommerce.api.inventory.mapper;

import co.ecommerce.api.inventory.request.ReduceStockInventoryRequest;
import co.ecommerce.api.inventory.request.RegisterInventoryRequest;
import co.ecommerce.api.inventory.request.UpdateInventoryRequest;
import co.ecommerce.usecase.inventory.command.ReduceStockInventoryCommand;
import co.ecommerce.usecase.inventory.command.RegisterInventoryCommand;
import co.ecommerce.usecase.inventory.command.UpdateInventoryCommand;

public class CommandInventoryRequestMapper {

    public static RegisterInventoryCommand toRegisterInventoryCommand(RegisterInventoryRequest request) {
        if (request == null) return null;
        return new RegisterInventoryCommand(
                request.sku(),
                request.quantity()
        );
    }

    public static UpdateInventoryCommand toUpdateInventoryCommand(UpdateInventoryRequest request) {
        if (request == null) return null;
        return new UpdateInventoryCommand(
                request.sku(),
                request.quantity()
        );
    }

    public static ReduceStockInventoryCommand toReduceInventoryCommand(ReduceStockInventoryRequest request) {
        if (request == null) return null;
        return new ReduceStockInventoryCommand(request.quantityToReduce());
    }
}
