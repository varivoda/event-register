package com.varivoda;

import java.sql.*;

/**
 * Util for working with data base
 */
public class DateBaseUtil {
    
    private Connection connection;
    
    public DateBaseUtil() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Event_Register?serverTimezone=GMT", "root", ",ekmrekm");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
    }
    
    public long getRowCount() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM EVENTS");
        resultSet.next();
        return resultSet.getLong(1);
    }
}
