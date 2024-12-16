package org.teamproject.lottocaptainteam.repository.delete;

import java.sql.SQLException;

@FunctionalInterface
public interface DeleteStrategy {
    void execute(String infoForDelete) throws SQLException;
}
