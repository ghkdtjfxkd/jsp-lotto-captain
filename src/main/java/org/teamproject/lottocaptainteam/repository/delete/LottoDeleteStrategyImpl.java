package org.teamproject.lottocaptainteam.repository.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;

public class LottoDeleteStrategyImpl implements DeleteStrategy {

    private final DBConnectionUtil dbConnectionUtil;

    public LottoDeleteStrategyImpl(DBConnectionUtil dbConnectionUtil) {
        this.dbConnectionUtil = dbConnectionUtil;
    }

    @Override
    public void execute(String infoForDelete) throws SQLException {
        String sql = "delete from lotto where id=?";

        try (Connection con = dbConnectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setLong(1, Integer.parseInt(infoForDelete));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}

