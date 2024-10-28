package org.projeto.api.exception;

public class InstituicaoNotFoundException extends RuntimeException {

    public static final String EVENT_NOT_FOUND = "Instituição não encontrada.";
    public InstituicaoNotFoundException() {
        super(EVENT_NOT_FOUND);
    }
}
