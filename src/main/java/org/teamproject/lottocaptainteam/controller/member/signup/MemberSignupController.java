package org.teamproject.lottocaptainteam.controller.member.signup;

import jakarta.ws.rs.BadRequestException;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.member.MemberController;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.dto.MemberSignupDTO;
import org.teamproject.lottocaptainteam.repository.MemberRepository;

public class MemberSignupController implements MemberController {

    private final MemberRepository memberRepository;

    public MemberSignupController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String id = paramMap.get("id");
        String username = paramMap.get("name");
        String password = paramMap.get("password");
        String passwordConfirm = paramMap.get("passwordConfirm");
        String email = paramMap.get("email");

        MemberSignupDTO inputInfo = MemberSignupDTO.create(id, username, password, passwordConfirm, email);

        validateBeforeSignup(inputInfo);
        Member member = Member.of(id, username, password, email);
        memberRepository.signup(member);

        ModelView mv = new ModelView("member/signup/save");
        mv.getModel().put("member", member);
        return mv;
    }

    private void validateBeforeSignup(MemberSignupDTO inputInfo) {
        validateInputId(inputInfo.getInputId());
        validatePasswordIsEqualToConfirm(inputInfo.getInputPassword(), inputInfo.getInputPasswordConfirm());
    }

    private void validateInputId(String inputId) {
        if (memberRepository.findById(inputId).isPresent()) {
            throw new BadRequestException("Member already exists");
        }
    }

    private void validatePasswordIsEqualToConfirm(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new BadRequestException("Passwords do not match");
        }
    }
}
