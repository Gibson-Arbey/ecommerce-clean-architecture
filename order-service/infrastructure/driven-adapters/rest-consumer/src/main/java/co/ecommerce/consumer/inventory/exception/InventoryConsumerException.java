package co.ecommerce.consumer.inventory.exception;

import co.ecommerce.model.exception.ErrorTypeEnum;
import co.ecommerce.model.exception.InfrastructureException;

public class InventoryConsumerException extends InfrastructureException {
    public InventoryConsumerException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVENTORY_CONSUMER_EXCEPTION";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.INTERNAL_SERVER_ERROR;
    }
}
