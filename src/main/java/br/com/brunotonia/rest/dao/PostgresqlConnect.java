package br.com.brunotonia.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnect {

    private static PostgresqlConnect instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/soap";
    private String username = "bruno";
    private String password = "angra1";

    private PostgresqlConnect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static PostgresqlConnect getInstance() throws SQLException {
        if (instance == null) {
            instance = new PostgresqlConnect();
        } else if (instance.getConnection().isClosed()) {
            instance = new PostgresqlConnect();
        }

        return instance;
    }
    
}
