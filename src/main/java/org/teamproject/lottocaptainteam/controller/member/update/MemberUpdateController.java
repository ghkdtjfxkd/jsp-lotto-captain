package org.teamproject.lottocaptainteam.controller.member.update;

import jakarta.ws.rs.BadRequestException;
import java.util.Map;
import java.util.Optional;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.member.MemberController;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.repository.MemberRepository;

public class MemberUpdateController implements MemberController {

    private final MemberRepository memberRepository;

    public MemberUpdateController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String id = paramMap.get("id");
        validateMatchedMemberExist(id);
        Optional<Member> findMember = findById(id);
        Member updatedMember = null;

        if (findMember.isPresent()) {
            String inputName = paramMap.get("name");
            String inputPassword = paramMap.get("password");
            String email = paramMap.get("email");

            updatedMember = findMember.get();
            updatedMember = updatedMember.withName(inputName);
            updatedMember = updatedMember.withPassword(inputPassword);
            updatedMember = updatedMember.withEmail(email);

            memberRepository.updateMemberById(id, updatedMember);
        }

        ModelView mv = new ModelView("member/mypage/after-update");
        mv.getModel().put("member", updatedMember);
        return mv;
    }

    private Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    private void validateMatchedMemberExist(String id) {
        if (findById(id).isEmpty()) {
            throw new BadRequestException("cannot find member with : " + id);
        }
    }
}
