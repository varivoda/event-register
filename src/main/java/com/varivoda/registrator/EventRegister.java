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
    
    public static final String USER = "root";
    public static final String PSW = "psw";
    public static final String URL = "jdbc:mysql://localhost:3306/Event_Register?serverTimezone=GMT";
    
    private AbstractEventDAO eventDAO;
    
    public EventRegister() {
        this.eventDAO = new EventDAOMySQL(USER, PSW, URL, 100);
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
        LocalDateTime beforeOneSec = now.minusSeconds(secCount);
        try {
            return eventDAO.getEventsCount(beforeOneSec, now);
        } catch (SQLException e) {
            throw new EventRegisterException(e);
        }
    }
}
