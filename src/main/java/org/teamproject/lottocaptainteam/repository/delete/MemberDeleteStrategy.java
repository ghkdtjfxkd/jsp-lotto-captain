package org.teamproject.lottocaptainteam.repository.delete;

import java.sql.SQLException;

@FunctionalInterface
public interface MemberDeleteStrategy {
    void execute(String infoForDelete) throws SQLException;
}
