package org.teamproject.lottocaptainteam.controller.member.list;

import java.util.List;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.member.MemberController;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.repository.MemberRepository;

public class MemberListController implements MemberController {

    private final MemberRepository memberRepository;

    public MemberListController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("/member/admin/members");
        mv.getModel().put("members", members);

        return mv;
    }
}
