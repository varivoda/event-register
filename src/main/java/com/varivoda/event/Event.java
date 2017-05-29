package com.varivoda.event;


import java.time.LocalDateTime;

/**
 * Базовый класс для представления событий в системе
 */
public class Event {
    
    // Эти свойства задаются один раз при инициализации. Никто не должен иметь доступа к изменению этих параметров
    private final long id;
    private final LocalDateTime startTime;
    
    public Event() {
        // Для простоты используем id 0.
        // Вообще говоря назначением уникальным id должна заниматься система, в которой они происходят
        // или же на крайний случай уникальности можно добиться сохраняя сразу в БД. (id генерятся)
        this.id = 0;
        startTime = LocalDateTime.now();
    }
    
    public Event(long id, LocalDateTime startTime) {
        this.id = id;
        this.startTime = startTime;
    }
    
    public long getId() {
        return id;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
}

