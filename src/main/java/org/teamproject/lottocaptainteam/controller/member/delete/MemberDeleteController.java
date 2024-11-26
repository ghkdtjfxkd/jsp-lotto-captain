package org.teamproject.lottocaptainteam.controller.member.delete;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.member.MemberController;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.repository.MemberRepository;

public class MemberDeleteController implements MemberController {

    private final MemberRepository memberRepository;

    public MemberDeleteController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ModelView process(Map<String, String> paramMap) {
        ModelView mv = new ModelView("/member/admin/delete");

        List<String> deleteMemberIds = new ArrayList<>();

        for (String value : paramMap.values()) {
            try {
                remove(value);
                deleteMemberIds.add(value);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        mv.getModel().put("deleteMemberIds", deleteMemberIds);
        return mv;
    }

    private void remove(String targetId) throws SQLException {
        memberRepository.deleteMemberById(targetId);
    }
}
