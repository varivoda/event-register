package com.varivoda.dao;

import com.varivoda.event.Event;

import java.sql.SQLException;

/**
 *
 */
public class EventDAOMySQL extends AbstractEventDAO {
    
    public EventDAOMySQL(String user, String psw, String host, int poolSize) {
        String dbUrl = "jdbc:mysql://" + host;
        
        connectionPool.setUsername(user);
        connectionPool.setPassword(psw);
        connectionPool.setDriverClassName("com.mysql.jdbc.Driver");
        connectionPool.setUrl(dbUrl);
        connectionPool.setInitialSize(poolSize);
    }
    
    @Override
    public void persistEvent(Event event) throws SQLException {
    
//        Connection connection = connectionPool.getConnection();
//        Statement statement = connection.createStatement();
//        statement.execute("SET INTO EVENTS")
    
    }
    
    @Override
    public long getEventsCount(int sec) {
        return 0;
    }
}
