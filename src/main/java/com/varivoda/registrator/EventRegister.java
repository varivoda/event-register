package com.varivoda.registrator;

import com.varivoda.dao.AbstractEventDAO;
import com.varivoda.dao.EventDAOMySQL;
import com.varivoda.event.Event;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 */
public class EventRegister implements IEventRegister {
    
    private AbstractEventDAO eventDAO;
    
    public EventRegister() {
        this.eventDAO = new EventDAOMySQL();
    }
    
    public void registerEvent(Event event) throws EventRegisterException {
        try {
            eventDAO.persistEvent(event);
        } catch (SQLException e) {
            throw new EventRegisterException("", e);
        }
    }
    
    public long getEventsCountInLastMinute() throws EventRegisterException {
        return getEventsCount(60);
    }
    
    public long getEventsCountInLastHour() throws EventRegisterException {
        return getEventsCount(60 * 60);
    }
    
    public long getEventsCountInLastDay() throws EventRegisterException {
        return getEventsCount(24 * 60 * 60);
    }
    
    private long getEventsCount(long secCount) throws EventRegisterException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before = now.minusSeconds(secCount);
        try {
            return eventDAO.getEventsCount(before, now);
        } catch (SQLException e) {
            throw new EventRegisterException(e);
        }
    }
}
