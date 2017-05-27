package com.varivoda;

import com.varivoda.dao.AbstractEventDAO;
import com.varivoda.dao.EventDAOMySQL;
import com.varivoda.event.Event;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Выполняет регистрацию и получение данный о количестве записей
 */
public class EventRegister implements IEventRegister {
    
    public static final String SQL_ERROR_MSG = "SQL exception was occurred";
    
    private AbstractEventDAO eventDAO;
    
    public EventRegister() {
        this.eventDAO = new EventDAOMySQL();
    }
    
    public void registerEvent(Event event) throws EventRegisterException {
        try {
            eventDAO.persistEvent(event);
        } catch (SQLException e) {
            throw new EventRegisterException(SQL_ERROR_MSG, e);
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
    
    /**
     * @param secCount количество секунд
     * @return Возвращает количество записей за указанное количество секунд
     * @throws EventRegisterException Возникает при некорректной работе с БД
     */
    private long getEventsCount(long secCount) throws EventRegisterException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before = now.minusSeconds(secCount);
        try {
            return eventDAO.getEventsCount(before, now);
        } catch (SQLException e) {
            throw new EventRegisterException(SQL_ERROR_MSG, e);
        }
    }
}
