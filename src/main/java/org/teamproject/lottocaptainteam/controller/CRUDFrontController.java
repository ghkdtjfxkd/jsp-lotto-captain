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
import org.teamproject.lottocaptainteam.controller.member.MemberController;
import org.teamproject.lottocaptainteam.controller.member.login.MemberLoginController;
import org.teamproject.lottocaptainteam.controller.member.signup.MemberFormController;
import org.teamproject.lottocaptainteam.controller.member.signup.MemberSignupController;
import org.teamproject.lottocaptainteam.repository.MemberRepositoryImpl;

@WebServlet(name = "cRUDFrontController", urlPatterns = "/member/*")
public class CRUDFrontController extends HttpServlet {

    private Map<String, MemberController> controllerMap = new HashMap<>();
    private final DBConfig dbConfig;

    public CRUDFrontController() {
        this.dbConfig = new DBConfig();
        initDB();
        controllerMap.put("/member/signup/signup-form", new MemberFormController());
        controllerMap.put("/member/signup/save", new MemberSignupController(MemberRepositoryImpl.getInstance()));
        controllerMap.put("/member/login/login", new MemberLoginController(MemberRepositoryImpl.getInstance()));
    }

    private void initDB() {
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
        MemberController controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
