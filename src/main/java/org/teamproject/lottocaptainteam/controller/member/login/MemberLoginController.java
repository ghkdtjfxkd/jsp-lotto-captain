package org.teamproject.lottocaptainteam.controller.member.login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.ws.rs.BadRequestException;
import java.util.Map;
import java.util.Optional;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.member.MemberController;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.repository.MemberRepository;

@WebServlet(name = "memberLoginController" , urlPatterns = "/member/login")
public class MemberLoginController implements MemberController {

    private final MemberRepository memberRepository;
    private Member loggedMember;

    public MemberLoginController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String id = paramMap.get("id");
        String password = paramMap.get("password");

        try {
            login(id, password);
            ModelView mv = new ModelView("member/login/after-login");
            mv.getModel().put("loggedMember", loggedMember);
            return mv;
        } catch (Exception e) {
            ModelView mv = new ModelView("member/login/login-fail");
            mv.getModel().put("errorMessage", e.getMessage());
            return mv;
        }
    }

    private boolean logged () {
        return loggedMember != null;
    }

    private void login(String id, String password) {
        validate(id, password);
        if(findById(id).isPresent()) {
            loggedMember = findById(id).get();
        }
    }

    private Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    private boolean passwordMatches(Member member, String password) {
        return member.getPassword().equals(password);
    }

    private void validate(String id, String password) {
        Member member = findById(id).orElse(null);
        validateMatchedMemberExist(id);
        validateWrongPassword(member, password);
    }

    private void validateMatchedMemberExist(String id) {
        if(findById(id).isEmpty()) {
            throw new BadRequestException("cannot find member with : " + id);
        }
    }

    private void validateWrongPassword(Member member, String password) {
        if(!passwordMatches(member, password)) {
            throw new BadRequestException("wrong password");
        }
    }
}
