package org.teamproject.lottocaptainteam.controller.member.update;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.MyView;
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

        if (findMember.isPresent()) {
            String inputName = paramMap.get("inputName");
            String inputPassword = paramMap.get("inputPassword");
            String email = paramMap.get("inputEmail");

            Member updatedMember = findMember.get();
            updatedMember.withName(inputName);
            updatedMember.withPassword(inputPassword);
            updatedMember.withEmail(email);

            memberRepository.updateMemberById(id, updatedMember);
        }
        return new ModelView("updated_member");
    }

    private Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    private void validateMatchedMemberExist(String id) {
        if(findById(id).isEmpty()) {
            throw new BadRequestException("cannot find member with : " + id);
        }
    }
}
