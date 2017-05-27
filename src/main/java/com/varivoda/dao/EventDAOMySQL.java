package com.varivoda.dao;

import com.varivoda.event.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class EventDAOMySQL extends AbstractEventDAO {
    
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    public EventDAOMySQL(String user, String psw, String url, int poolSize) {
        super();
        connectionPool.setUsername(user);
        connectionPool.setPassword(psw);
        connectionPool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        connectionPool.setUrl(url);
        connectionPool.setInitialSize(poolSize);
    }
    
    @Override
    public void persistEvent(Event event) throws SQLException {
    
        LocalDateTime initTime = event.getInitTime();
        String timeForMysqlDB = initTime.format(dateTimeFormatter);
    
        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(String.format("INSERT INTO EVENTS VALUES ( %d, '%s')", event.getId(), timeForMysqlDB));
        
    }
    
    @Override
    public long getEventsCount(LocalDateTime start, LocalDateTime end) throws SQLException {
        String starDateTimeStr = start.format(dateTimeFormatter);
        String endDateTimeStr = end.format(dateTimeFormatter);
    
        Connection connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
    
        ResultSet resultSet = statement.executeQuery(String.format("SELECT COUNT(*) FROM EVENTS WHERE DATE BETWEEN '%s' and '%s'", starDateTimeStr, endDateTimeStr));
        resultSet.next();
        return resultSet.getLong(1);
    }
    
}
