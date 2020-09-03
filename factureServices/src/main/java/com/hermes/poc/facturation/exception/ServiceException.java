package com.hermes.poc.facturation.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private ErrorOrigin errorOrigin;
    private ErrorCatalog errorCatalog;

    public ServiceException(ErrorCatalog errorCatalog, ErrorOrigin errorOrigin) {
        this(errorCatalog, errorOrigin, errorCatalog.getMessage(), null);
    }

    public ServiceException(ErrorCatalog errorCatalog, ErrorOrigin errorOrigin, Throwable throwable) {
        this(errorCatalog, errorOrigin, errorCatalog.getMessage(), throwable);
    }

    public ServiceException(ErrorCatalog errorCatalog, ErrorOrigin errorOrigin, String message) {
        this(errorCatalog, errorOrigin, message, null);
    }

    public ServiceException(ErrorCatalog errorCatalog, ErrorOrigin errorOrigin, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCatalog = errorCatalog;
        this.errorOrigin = errorOrigin;
    }
}
