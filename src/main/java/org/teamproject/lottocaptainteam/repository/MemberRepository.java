package org.teamproject.lottocaptainteam.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.teamproject.lottocaptainteam.domain.Member;

public interface MemberRepository {
    Member signup(Member member);
    Optional<Member> findById(String id);
    Optional<Member> updateMemberById(String id, Member updatedMember);
    void deleteMemberById(String id) throws SQLException;
    List<Member> findAll();
}
