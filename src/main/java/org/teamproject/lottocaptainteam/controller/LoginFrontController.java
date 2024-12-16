package org.teamproject.lottocaptainteam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.teamproject.lottocaptainteam.config.DBConfig;
import org.teamproject.lottocaptainteam.controller.login.LogController;
import org.teamproject.lottocaptainteam.controller.login.LoginController;
import org.teamproject.lottocaptainteam.controller.login.LogoutController;
import org.teamproject.lottocaptainteam.repository.MemberRepository;
import org.teamproject.lottocaptainteam.repository.MemberRepositoryImpl;

@WebServlet(name = "loginFrontController", urlPatterns = "/login/*")
public class LoginFrontController extends HttpServlet {

    private final Map<String, LogController> controllerMap = new HashMap<>();
    private final DBConfig dbConfig;

    public LoginFrontController() throws IOException {
        this.dbConfig = new DBConfig();
        initDB();
        MemberRepository memberRepository = MemberRepositoryImpl.getInstance();
        controllerMap.put("/login/login", new LoginController(memberRepository));
        controllerMap.put("/login/logout", new LogoutController());
    }

    private void initDB() throws IOException {
        MemberRepositoryImpl.initialize(
                dbConfig.memberSignupStrategy(),
                dbConfig.memberSearchStrategy(),
                dbConfig.memberUpdateStrategy(),
                dbConfig.memberDeleteStrategy()
        );
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 요청 URI에 따른 컨트롤러 검색
        LogController controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 컨트롤러 호출
        Map<String, Object> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        response.sendRedirect(request.getContextPath() + "/");
    }

    private Map<String, Object> createParamMap(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        paramMap.put("request", request); // HttpServletRequest 추가
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/index.jsp");
    }
}


