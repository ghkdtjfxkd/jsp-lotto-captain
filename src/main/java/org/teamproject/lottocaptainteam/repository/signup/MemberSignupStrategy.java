package org.teamproject.lottocaptainteam.repository.signup;

import java.io.IOException;
import org.teamproject.lottocaptainteam.domain.Member;

@FunctionalInterface
public interface MemberSignupStrategy {
    Member execute(Member member);
}
