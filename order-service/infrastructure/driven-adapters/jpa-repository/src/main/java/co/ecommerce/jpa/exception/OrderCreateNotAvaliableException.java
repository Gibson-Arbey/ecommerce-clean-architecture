package co.ecommerce.jpa.exception;

import co.ecommerce.model.exception.ErrorTypeEnum;
import co.ecommerce.model.exception.InfrastructureException;

public class OrderCreateNotAvaliableException extends InfrastructureException {

    @Override
    public String getCode() {
        return "ORDER_CREATE_NOT_AVALIABLE";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.VALIDATION;
    }

    public OrderCreateNotAvaliableException(String message) {
        super(message);
    }
}
