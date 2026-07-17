package co.ecommerce.model.inventory.exception;

import co.ecommerce.model.exception.DomainException;
import co.ecommerce.model.exception.ErrorTypeEnum;

public class InventoryNotFoundException extends DomainException {
    public InventoryNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVENTORY_NOT_FOUND";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
