package org.projeto.api.exception;

public class EventNotChangedException extends RuntimeException {
    public EventNotChangedException(String message) {
        super(message);
    }
    public EventNotChangedException() {
        super("Event not changed");
    }
}
