package com.varivoda.registrator;

import com.varivoda.event.Event;

/**
 * Basic interface
 */
public interface IEventRegister {
    
    void registerEvent(Event event) throws EventRegisterException;
    
    long getEventsCountInLastMinute() throws EventRegisterException;
    
    long getEventsCountInLastHour() throws EventRegisterException;
    
    long getEventsCountInLastDay() throws EventRegisterException;
}

