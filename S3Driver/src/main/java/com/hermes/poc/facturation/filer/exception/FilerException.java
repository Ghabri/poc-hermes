package com.hermes.poc.facturation.filer.exception;

import lombok.Getter;

@Getter
public class FilerException extends Exception {

    private FilerError filerError;

    public FilerException(FilerError filerError, String message, Throwable cause) {
        super(message, cause);
        this.filerError = filerError;
    }

    public FilerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilerException(FilerError filerError, Throwable cause) {
        super( cause);
        this.filerError = filerError;
    }
}
