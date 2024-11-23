package org.teamproject.lottocaptainteam.repository.signup;

import static org.teamproject.lottocaptainteam.connection.DBConnectionUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;
import org.teamproject.lottocaptainteam.domain.Member;

public class MemberSignupStrategyImpl implements MemberSignupStrategy {

    private final DBConnectionUtil connectionUtil;

    public MemberSignupStrategyImpl(DBConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public Member execute(Member member) {
        String sql = "insert into member(member_id, member_name, member_password, member_email) values(?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = connectionUtil.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getPassword());
            pstmt.setString(4, member.getEmail());

            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(con, pstmt, null);
        }
    }
}
