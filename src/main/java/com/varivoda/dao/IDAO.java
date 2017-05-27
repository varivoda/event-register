package com.varivoda.dao;

import com.varivoda.event.Event;

/**
 * Interface for basic operation with database
 */
public interface IDAO {
    
    void addEvent(Event event);
    
    long getEventsCount(int sec);
}
