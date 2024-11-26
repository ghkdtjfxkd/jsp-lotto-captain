package org.teamproject.lottocaptainteam.controller.member.update;

import jakarta.servlet.annotation.WebServlet;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.member.MemberController;


@WebServlet(name = "memberFormController", urlPatterns = "/member/update-form")
public class MemberUpdateFormController implements MemberController {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("/member/mypage/mypage-form");
    }
}
