package org.teamproject.lottocaptainteam.repository;

import java.awt.MenuBar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.teamproject.lottocaptainteam.connection.ConnectionConst;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.repository.delete.MemberDeleteStrategy;
import org.teamproject.lottocaptainteam.repository.search.MemberSearchStrategy;
import org.teamproject.lottocaptainteam.repository.signup.MemberSignupStrategy;
import org.teamproject.lottocaptainteam.repository.update.MemberUpdateStrategy;

public class MemberRepositoryImpl implements MemberRepository {

    private static volatile MemberRepositoryImpl instance;  // volatile 필수

    private final MemberSignupStrategy memberSignupStrategy;
    private final MemberSearchStrategy memberSearchStrategy;
    private final MemberUpdateStrategy memberUpdateStrategy;
    private final MemberDeleteStrategy memberDeleteStrategy;

    private MemberRepositoryImpl(MemberSignupStrategy memberSignupStrategy, MemberSearchStrategy memberSearchStrategy,
                                 MemberUpdateStrategy memberUpdateStrategy, MemberDeleteStrategy memberDeleteStrategy) {
        this.memberSignupStrategy = memberSignupStrategy;
        this.memberSearchStrategy = memberSearchStrategy;
        this.memberUpdateStrategy = memberUpdateStrategy;
        this.memberDeleteStrategy = memberDeleteStrategy;
    }

    public static MemberRepositoryImpl getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Repository is not initialized");
        }
        return instance;
    }

    public static void initialize(MemberSignupStrategy signupStrategy,
                                  MemberSearchStrategy searchStrategy,
                                  MemberUpdateStrategy updateStrategy,
                                  MemberDeleteStrategy deleteStrategy
    ) {
        if (instance == null) {
            synchronized (MemberRepositoryImpl.class) {
                if (instance == null) {
                    instance = new MemberRepositoryImpl(
                            signupStrategy,
                            searchStrategy,
                            updateStrategy,
                            deleteStrategy
                    );
                }
            }
        } else {
            throw new IllegalStateException("Repository is already initialized");
        }
    }

    @Override
    public Member signup(Member member) {
        synchronized (memberSignupStrategy) {
            return memberSignupStrategy.execute(member);
        }
    }

    @Override
    public Optional<Member> findById(String id) {
        synchronized (memberSearchStrategy) {
            return memberSearchStrategy.execute(id);
        }
    }

    @Override
    public Optional<Member> updateMemberById(String id, Member updatedMember) {
        synchronized (memberUpdateStrategy) {
            return memberUpdateStrategy.execute(id, updatedMember);
        }
    }

    @Override
    public void deleteMemberById(String id) throws SQLException {
        synchronized (memberDeleteStrategy) {
            memberDeleteStrategy.execute(id);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        List<Member> members = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(
                    ConnectionConst.URL,
                    ConnectionConst.USERNAME,
                    ConnectionConst.PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                members.add(mapToMember(rs));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to fetch all members", e);
        }
        return members;

    }

    private Member mapToMember(ResultSet rs) throws SQLException {
        return Member.of(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("email")
        );
    }
}
