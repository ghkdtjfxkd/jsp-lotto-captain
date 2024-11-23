package org.teamproject.lottocaptainteam.repository;

import static org.teamproject.lottocaptainteam.connection.DBConnectionUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;
import org.teamproject.lottocaptainteam.domain.Member;

public class MemberRepositoryImpl implements MemberRepository {
    private static volatile MemberRepositoryImpl instance;  // volatile 필수

    private MemberRepositoryImpl() {
    }

    public static MemberRepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (MemberRepositoryImpl.class) {
                if (instance == null) {
                    instance = new MemberRepositoryImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Member signup(Member member) {
        String sql = "insert into member(member_id, member_name, member_password, member_email) values(?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
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

    @Override
    public Optional<Member> findById(String id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
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
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = mapToMemberIn(rs);
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
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

    @Override
    public Optional<Member> updateMemberById(String id, Member updatedMember) {
        String sql = "update member set member_name = ?, member_password = ?, member_email = ? where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            Optional<Member> findMember = findById(id);
            if (findMember.isPresent()) {
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

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}

