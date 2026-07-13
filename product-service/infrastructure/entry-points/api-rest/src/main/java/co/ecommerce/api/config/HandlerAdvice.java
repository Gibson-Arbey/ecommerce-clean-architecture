package co.ecommerce.api.config;

import co.ecommerce.model.exception.DomainException;
import co.ecommerce.model.exception.ErrorTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HandlerAdvice {

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomainException(
            DomainException ex,
            HttpServletRequest request
    ) {

        HttpStatus status = getHttpStatus(ex.getErrorType());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                status,
                ex.getMessage()
        );

        problemDetail.setTitle(ex.getErrorType().name());
        problemDetail.setProperty("code", ex.getCode());
        problemDetail.setProperty("path", request.getRequestURI());

        log.error(problemDetail.toString(), ex);

        return problemDetail;
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected internal server error"
        );

        problemDetail.setTitle("INTERNAL_SERVER_ERROR");
        problemDetail.setProperty("path", request.getRequestURI());

        log.error("Internal error", ex);

        return problemDetail;
    }


    private HttpStatus getHttpStatus(ErrorTypeEnum errorType) {
        return switch (errorType) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case BAD_REQUEST, VALIDATION -> HttpStatus.BAD_REQUEST;
            case CONFLICT -> HttpStatus.CONFLICT;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case INTERNAL_SERVER_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}