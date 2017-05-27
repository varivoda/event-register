package com.varivoda.event;

import java.time.LocalDateTime;

/**
 * Base event class in the system
 */
public class Event {
    
    protected final long id;
    protected final LocalDateTime initTime;
    
    public Event() {
        // Для простоты используем id 0.
        // Вообще говоря назначением уникальным id должна заниматься система, в которой они происходят
        // или же на крайний случай уникальности можно добиться сохраняя сразу в БД. (id генерятся)
        this.id = 0;
        this.initTime = LocalDateTime.now();
    }
    
    public long getId() {
        return id;
    }
    
    public LocalDateTime getInitTime() {
        return initTime;
    }
    
}

