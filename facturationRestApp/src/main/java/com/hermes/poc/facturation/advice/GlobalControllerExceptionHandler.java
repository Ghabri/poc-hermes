package com.hermes.poc.facturation.advice;

import com.hermes.poc.facturation.exception.ErrorCatalog;
import com.hermes.poc.facturation.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorDto> handleServiceException(ServiceException serviceException) {
        log.error("Exception lors de l'appel du service", serviceException);
        return ResponseEntity.status(getStatus(serviceException))
                .body(getBuildErrorDto(serviceException));
    }


    private ErrorDto getBuildErrorDto(ServiceException serviceException) {
        ErrorCatalog errorCatalog = serviceException.getErrorCatalog();
        String serviceExceptionMessage = serviceException.getMessage();
        String message = getMessage(errorCatalog, serviceExceptionMessage);
        return new ErrorDto(errorCatalog.getCode(), message);
    }

    private int getStatus(ServiceException e) {
        switch (e.getErrorCatalog()) {
            case RESOURCE_INTROUVABLE:
                return HttpStatus.NOT_FOUND.value();
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    private String getMessage(ErrorCatalog errorCatalog, String message) {
        return Optional.ofNullable(message).orElse(errorCatalog.getMessage());
    }
}
