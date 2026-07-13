package co.ecommerce.api.product.mapper;

import co.ecommerce.api.product.request.RegisterProductRequest;
import co.ecommerce.api.product.request.UpdateProductRequest;
import co.ecommerce.usecase.product.command.RegisterProductCommand;
import co.ecommerce.usecase.product.command.UpdateProductCommand;

public class CommandProductRequestMapper {

    public static RegisterProductCommand toRegisterCommand(RegisterProductRequest request) {
        return new RegisterProductCommand(request.name(), request.description(), request.price());
    }

    public static UpdateProductCommand toUpdateCommand(UpdateProductRequest request) {
        return new UpdateProductCommand(request.name(), request.description(), request.price());
    }
}