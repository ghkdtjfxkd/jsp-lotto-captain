package org.teamproject.lottocaptainteam.repository.update;

import java.util.Optional;
import org.teamproject.lottocaptainteam.domain.Member;

@FunctionalInterface
public interface MemberUpdateStrategy {
    Optional<Member> execute(String id, Member updatedMember);
}
