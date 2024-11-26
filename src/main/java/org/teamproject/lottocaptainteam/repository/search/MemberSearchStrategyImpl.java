package org.teamproject.lottocaptainteam.repository.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;
import org.teamproject.lottocaptainteam.domain.Member;

public class MemberSearchStrategyImpl implements MemberSearchStrategy {

    private final DBConnectionUtil connectionUtil;

    public MemberSearchStrategyImpl(DBConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public Optional<Member> execute(String id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connectionUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = mapToMemberIn(rs);
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            connectionUtil.close(conn, pstmt, rs);
        }
    }

    private Member mapToMemberIn(ResultSet rs) throws SQLException {
        return Member.of(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("email")
        );
    }
}
