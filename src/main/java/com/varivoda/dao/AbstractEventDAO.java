package com.varivoda.dao;

import com.varivoda.event.Event;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Interface for basic operation with database
 */
public abstract class AbstractEventDAO {
    
    protected BasicDataSource connectionPool;
    
    protected AbstractEventDAO() {
        this.connectionPool = new BasicDataSource();
    }
    
    public abstract void persistEvent(Event event) throws SQLException;
    
    public abstract long getEventsCount(LocalDateTime start, LocalDateTime end) throws SQLException;
}
