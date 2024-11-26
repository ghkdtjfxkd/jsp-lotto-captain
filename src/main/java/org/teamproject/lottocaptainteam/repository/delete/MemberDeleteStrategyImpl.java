package org.teamproject.lottocaptainteam.repository.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;

public class MemberDeleteStrategyImpl implements MemberDeleteStrategy {

    private final DBConnectionUtil dbConnectionUtil;

    public MemberDeleteStrategyImpl(DBConnectionUtil dbConnectionUtil) {
        this.dbConnectionUtil = dbConnectionUtil;
    }

    @Override
    public void execute(String memberId) throws SQLException {
        String sql = "delete from member where id=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dbConnectionUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
