package org.teamproject.lottocaptainteam.repository.update;

import static org.teamproject.lottocaptainteam.connection.DBConnectionUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;
import org.teamproject.lottocaptainteam.domain.Member;

public class MemberUpdateStrategyImpl implements MemberUpdateStrategy {

    private final DBConnectionUtil connection;

    public MemberUpdateStrategyImpl(DBConnectionUtil connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Member> execute(String id, Member updatedMember) {
        String sql = "update member set member_name = ?, member_password = ?, member_email = ? where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connection.getConnection();
            pstmt = conn.prepareStatement(sql);
            if (isMemberExists(id)) {
                pstmt.setString(1, updatedMember.getName());
                pstmt.setString(2, updatedMember.getPassword());
                pstmt.setString(3, updatedMember.getEmail());
                pstmt.setString(4, id);
                pstmt.executeUpdate();

                return Optional.of(updatedMember);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt);
        }

    }

    private boolean isMemberExists(String id) {
        String sql = "SELECT 1 FROM member WHERE member_id = ?";
        try (Connection conn = connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();  // 결과가 존재하면 true 반환
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
