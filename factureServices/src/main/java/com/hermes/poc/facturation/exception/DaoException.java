package com.hermes.poc.facturation.exception;

import lombok.Getter;

@Getter
public class DaoException extends Exception {

    private DaoError daoError;

    public DaoException(DaoError daoError) {
        this(daoError, null);
    }

    public DaoException(DaoError daoError, Throwable throwable) {
        super(throwable);
        this.daoError = daoError;
    }

}
