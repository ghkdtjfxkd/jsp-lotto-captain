package org.teamproject.lottocaptainteam.controller.login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.repository.MemberRepository;

@WebServlet(name = "loginController", urlPatterns = "/login")
public class LoginController implements LogController {

    private final MemberRepository memberRepository;

    public LoginController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public ModelView process(Map<String, Object> paramMap) {
        String id = (String) paramMap.get("id");
        String password = (String) paramMap.get("password");
        HttpServletRequest request = (HttpServletRequest) paramMap.get("request");

        try {
            Member member = login(id, password);
            request.getSession().setAttribute("loggedMember", member); // 세션에 사용자 저장
            return new ModelView("index"); // 성공 시 이동할 뷰
        } catch (Exception e) {
            ModelView mv = new ModelView("member/login/login-fail");
            mv.getModel().put("errorMessage", e.getMessage());
            return mv;
        }
    }

    private Member login(String id, String password) {
        return memberRepository.findById(id)
                .filter(member -> member.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디 또는 비밀번호입니다."));
    }
}
