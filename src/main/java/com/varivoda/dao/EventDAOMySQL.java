package com.varivoda.dao;

import com.varivoda.event.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Реализация для базы Mysql
 */
public class EventDAOMySQL extends AbstractEventDAO {
    
    // Для простоты параметры подключения к БД и пула описаны константами
    public static final String USER = "root";
    public static final String PSW = ",ekmrekm";
    public static final String URL = "jdbc:mysql://localhost:3306/Event_Register?serverTimezone=GMT";
    public static final int INITIAL_SIZE = 10;
    public static final int MAX_IDLE = 1000;
    
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    public EventDAOMySQL() {
        super();
        // Настройки подключения
        connectionPool.setUsername(USER);
        connectionPool.setPassword(PSW);
        connectionPool.setUrl(URL);
        connectionPool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        // Настройки пула
        connectionPool.setInitialSize(INITIAL_SIZE);
        connectionPool.setMaxIdle(MAX_IDLE);
    }
    
    @Override
    public void persistEvent(Event event) throws SQLException {
    
        LocalDateTime startDateTime = event.getStartTime();
        String startDateTimeStr = startDateTime.format(dateTimeFormatter);
    
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(String.format("INSERT INTO EVENTS VALUES ( %d, '%s')", event.getId(), startDateTimeStr));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        
    }
    
    @Override
    public long getEventsCount(LocalDateTime startDateTime, LocalDateTime endDateTime) throws SQLException {
        
        String starDateTimeStr = startDateTime.format(dateTimeFormatter);
        String endDateTimeStr = endDateTime.format(dateTimeFormatter);
    
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT COUNT(*) FROM EVENTS WHERE DATE BETWEEN '%s' and '%s'", starDateTimeStr, endDateTimeStr));
            resultSet.next();
            return resultSet.getLong(1);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}
