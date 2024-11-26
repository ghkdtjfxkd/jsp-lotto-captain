package org.teamproject.lottocaptainteam.controller.member.signup;

import jakarta.servlet.annotation.WebServlet;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.member.MemberController;

@WebServlet(name = "memberFormController", urlPatterns = "/member/signup-form")
public class MemberFormController implements MemberController {

    @Override
    public ModelView process(Map<String, String> paramMap){
        return new ModelView("/member/signup/signup-form");
    }
}
