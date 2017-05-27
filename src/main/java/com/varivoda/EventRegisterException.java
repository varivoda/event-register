package com.varivoda;

/**
 * Ошибки, возникающие при работе с БД
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
