package org.teamproject.lottocaptainteam.connection;

import static org.teamproject.lottocaptainteam.connection.ConnectionConst.JDBC_DRIVER;
import static org.teamproject.lottocaptainteam.connection.ConnectionConst.PASSWORD;
import static org.teamproject.lottocaptainteam.connection.ConnectionConst.URL;
import static org.teamproject.lottocaptainteam.connection.ConnectionConst.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionUtil {

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBC 드라이버 로드 실패", e);
        }
    }


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void close(AutoCloseable... resources) {
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

