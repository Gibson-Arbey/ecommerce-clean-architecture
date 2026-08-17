package co.ecommerce.model.order.exception;

import co.ecommerce.model.exception.DomainException;
import co.ecommerce.model.exception.ErrorTypeEnum;

public class InvalidOrderStatusException extends DomainException {
    public InvalidOrderStatusException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_ORDER_STATUS";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.BAD_REQUEST;
    }
}
