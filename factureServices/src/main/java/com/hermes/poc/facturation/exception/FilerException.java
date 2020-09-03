package com.hermes.poc.facturation.exception;


import lombok.Getter;

@Getter
public class FilerException extends Exception {

    private FilerError filerError;

    public FilerException(FilerError filerError) {
        this(filerError, null);
    }

    public FilerException(FilerError filerError, Throwable throwable) {
        super(throwable);
        this.filerError = filerError;
    }
}
