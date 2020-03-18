package org.sterzhen.programmers_notes.core.exceptions;

public class EntityNotFoundException extends IllegalArgumentException {

    public EntityNotFoundException(String s) {
        super(s);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
