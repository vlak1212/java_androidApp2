package com.example.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerConnection {
    private static final String DB_URL = "jdbc:sqlserver://LAPTOP-L9BKK0OP\\SQLEXPRESS;databaseName=javaApp;integratedSecurity=true";
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(DB_URL + ";integratedSecurity=true");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found", e);
        }
    }
}
