package co.ecommerce.model.exception;

public abstract class InfrastructureException extends RuntimeException {
    public InfrastructureException(String message) {
        super(message);
    }
    public abstract String getCode();

    public abstract ErrorTypeEnum getErrorType();
}
