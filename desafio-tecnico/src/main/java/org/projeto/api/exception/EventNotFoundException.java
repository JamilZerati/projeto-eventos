package org.projeto.api.exception;

public class EventNotFoundException extends RuntimeException {

    public static final String EVENT_NOT_FOUND = "Evento não encontrado";
    public EventNotFoundException() {
        super(EVENT_NOT_FOUND);
    }
}
