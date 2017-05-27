package com.varivoda.registrator;

import com.varivoda.event.Event;

/**
 * Базовый интерфейс регистратора событий
 */
public interface IEventRegister {
    
    void registerEvent(Event event) throws EventRegisterException;
    
    long getEventCountInLastMinute();
    
    long getEventCountInLastHour();
    
    long getEventCountInLastDay();
}

