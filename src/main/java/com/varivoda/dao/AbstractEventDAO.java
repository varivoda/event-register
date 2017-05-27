package com.varivoda.dao;

import com.varivoda.event.Event;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

/**
 * Interface for basic operation with database
 */
public abstract class AbstractEventDAO {
    
    protected BasicDataSource connectionPool;
    
    public abstract void persistEvent(Event event) throws SQLException;
    
    public abstract long getEventsCount(int sec);
}
