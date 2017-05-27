package com.varivoda.registrator;

import com.varivoda.dao.AbstractEventDAO;
import com.varivoda.dao.EventDAOMySQL;
import com.varivoda.event.Event;

import java.sql.SQLException;

/**
 *
 */
public class EventRegister implements IEventRegister {
    
    public static final String USER = "user";
    public static final String PSW = "psw";
    public static final String HOST = "host";
    
    private AbstractEventDAO eventDAO;
    
    public EventRegister() {
        this.eventDAO = new EventDAOMySQL(USER, PSW, HOST, 100);
    }
    
    public void registerEvent(Event event) throws EventRegisterException {
        try {
            eventDAO.persistEvent(event);
        } catch (SQLException e) {
            throw new EventRegisterException("", e);
        }
    }
    
    public long getEventsCountInLastMinute() {
        return 0;
    }
    
    public long getEventsCountInLastHour() {
        return 0;
    }
    
    public long getEventsCountInLastDay() {
        return 0;
    }
}
