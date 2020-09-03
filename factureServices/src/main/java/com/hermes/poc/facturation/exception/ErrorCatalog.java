package com.hermes.poc.facturation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCatalog {

    APPLICATION_ERROR(1, "Une erreur non geree est survenue", ErrorLevel.TECHNICAL ),
    DB_ERROR(2, "Erreur base de donnees", ErrorLevel.TECHNICAL),
    FILER_ERROR(3, "Erreur systeme de gestion de fichiers", ErrorLevel.TECHNICAL),
    RESOURCE_INTROUVABLE(4, "La resource recherchee est introuvable", ErrorLevel.FUNCTIONAL),
    ILLEGAL_STATE(5, "Etat inattendu", ErrorLevel.FUNCTIONAL);

    private int code;
    private String message;
    private ErrorLevel errorLevel;
}
