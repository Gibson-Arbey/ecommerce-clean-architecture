package co.ecommerce.model.product.exception;

import co.ecommerce.model.exception.DomainException;
import co.ecommerce.model.exception.ErrorTypeEnum;

public class ProductNotFoundException extends DomainException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "PRODUCT_NOT_FOUND";
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.NOT_FOUND;
    }
}
