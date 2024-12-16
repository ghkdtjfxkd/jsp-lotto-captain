package org.teamproject.lottocaptainteam.controller.member.logout;

import jakarta.servlet.annotation.WebServlet;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.member.MemberController;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.repository.MemberRepository;

@WebServlet(name = "memberLogoutController" , urlPatterns = "/member/logout")
public class MemberLogoutController implements MemberController {

    private final Member loggedMember;

    public MemberLogoutController(Member loggedMember) {
        this.loggedMember = loggedMember;
    }

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return null;
    }
}
