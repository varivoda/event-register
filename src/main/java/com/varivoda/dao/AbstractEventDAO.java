package com.varivoda.dao;

import com.varivoda.event.Event;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Интерфейс для базовых операций с БД
 */
public abstract class AbstractEventDAO {
    
    protected BasicDataSource connectionPool;
    
    protected AbstractEventDAO() {
        this.connectionPool = new BasicDataSource();
    }
    
    /**
     * Сохраняет указанное событие
     * @param event Событие, которое регестрируем
     * @throws SQLException
     */
    public abstract void persistEvent(Event event) throws SQLException;
    
    /**
     * Возвращает количество записей в указанных временный пределах
     * @param startDateTime начальная граница
     * @param endDateTime конечная граница
     * @return количесвто записей
     * @throws SQLException
     */
    public abstract long getEventsCount(LocalDateTime startDateTime, LocalDateTime endDateTime) throws SQLException;
}
