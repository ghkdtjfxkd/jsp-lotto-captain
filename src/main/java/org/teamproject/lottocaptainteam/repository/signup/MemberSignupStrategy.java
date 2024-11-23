package org.teamproject.lottocaptainteam.repository.signup;

import org.teamproject.lottocaptainteam.domain.Member;

public interface MemberSignupStrategy {
    Member execute(Member member);
}
