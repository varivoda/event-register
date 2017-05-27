package com.varivoda.registrator;

/**
 * Исключение, возникающие при работе регистратора событий
 */
public class EventRegisterException extends Exception {
    
    public EventRegisterException() {
    }
    
    public EventRegisterException(String message) {
        super(message);
    }
    
    public EventRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public EventRegisterException(Throwable cause) {
        super(cause);
    }
    
}
