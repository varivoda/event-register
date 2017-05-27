package com.varivoda.event;

import java.time.LocalDateTime;

/**
 * Base event class in the system
 */
public class Event {
    
    private final long id;
    private final LocalDateTime startTime;
    
    public Event() {
        // Для простоты используем id 0.
        // Вообще говоря назначением уникальным id должна заниматься система, в которой они происходят
        // или же на крайний случай уникальности можно добиться сохраняя сразу в БД. (id генерятся)
        this.id = 0;
        this.startTime = LocalDateTime.now();
    }
    
    public long getId() {
        return id;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
}

