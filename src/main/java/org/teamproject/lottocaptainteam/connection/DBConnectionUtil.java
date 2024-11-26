package org.teamproject.lottocaptainteam.connection;

import static org.teamproject.lottocaptainteam.connection.ConnectionConst.JDBC_DRIVER;
import static org.teamproject.lottocaptainteam.connection.ConnectionConst.PASSWORD;
import static org.teamproject.lottocaptainteam.connection.ConnectionConst.URL;
import static org.teamproject.lottocaptainteam.connection.ConnectionConst.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionUtil {
    
    public Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

