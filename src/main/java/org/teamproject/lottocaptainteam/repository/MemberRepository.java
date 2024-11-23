package org.teamproject.lottocaptainteam.repository;

import java.util.List;
import java.util.Optional;
import org.teamproject.lottocaptainteam.domain.Member;

public interface MemberRepository {
    Member signup(Member member);
    Optional<Member> findById(String id);
    List<Member> findAll();
    Optional<Member> updateMemberById(String id, Member updatedMember);
}
