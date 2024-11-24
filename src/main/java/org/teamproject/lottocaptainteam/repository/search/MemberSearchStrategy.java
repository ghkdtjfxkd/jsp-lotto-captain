package org.teamproject.lottocaptainteam.repository.search;

import java.util.Optional;
import org.teamproject.lottocaptainteam.domain.Member;

@FunctionalInterface
public interface MemberSearchStrategy {
    Optional<Member> execute(String infoForSearch);
}
