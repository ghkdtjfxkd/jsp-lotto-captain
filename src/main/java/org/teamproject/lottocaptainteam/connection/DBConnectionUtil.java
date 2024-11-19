package org.teamproject.lottocaptainteam.connection;

import static org.teamproject.lottocaptainteam.connection.ConnectionConst.PASSWORD;
import static org.teamproject.lottocaptainteam.connection.ConnectionConst.URL;
import static org.teamproject.lottocaptainteam.connection.ConnectionConst.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

