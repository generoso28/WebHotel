package com.infotecheduca.hotel.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static ConnectionFactory instace;
    private ConnectionFactory() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver", e);
        }
    }
    public static ConnectionFactory getInstance() {
        if (instace == null) {
            instace = new ConnectionFactory();
        }
        return instace;
    }
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

    }
    public PreparedStatement getPreparedStatement(String sql) throws SQLException{
       Connection con = getConnection();
       return con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
}
