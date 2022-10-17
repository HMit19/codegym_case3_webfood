package com.codegym.connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToMySQL {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/webfood";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "1905";

    public ConnectToMySQL() {
    }

    public static Connection getConnection() throws RuntimeException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    // test connect to mysql
    public static void main(String[] args) {
        if(getConnection()!= null){
            System.out.println("connected");
        }
    }
}
