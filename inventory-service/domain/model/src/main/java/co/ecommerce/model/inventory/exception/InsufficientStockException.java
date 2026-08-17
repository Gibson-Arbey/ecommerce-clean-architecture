package co.ecommerce.model.inventory.exception;

import co.ecommerce.model.exception.DomainException;
import co.ecommerce.model.exception.ErrorTypeEnum;

public class InsufficientStockException extends DomainException {
    public InsufficientStockException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INSUFFICIENT_STOCK_EXCEPTION";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }
}
