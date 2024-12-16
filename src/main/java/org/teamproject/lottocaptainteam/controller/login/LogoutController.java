package org.teamproject.lottocaptainteam.controller.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;

public class LogoutController implements LogController {
    @Override
    public ModelView process(Map<String, Object> paramMap) {
        HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return new ModelView("index"); // 로그아웃 후 이동할 뷰
    }
}

