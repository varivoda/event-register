package com.varivoda.registrator;

import com.varivoda.event.Event;

/**
 * Basic interface
 */
public interface IEventRegister {
    
    void registerEvent(Event event) throws EventRegisterException;
    
    long getEventsCountInLastMinute();
    
    long getEventsCountInLastHour();
    
    long getEventsCountInLastDay();
}

