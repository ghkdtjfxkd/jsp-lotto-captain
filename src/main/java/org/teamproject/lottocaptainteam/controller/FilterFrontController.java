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
import org.teamproject.lottocaptainteam.controller.member.delete.MemberDeleteController;
import org.teamproject.lottocaptainteam.controller.member.list.MemberListController;
import org.teamproject.lottocaptainteam.controller.member.login.MemberLoginController;
import org.teamproject.lottocaptainteam.controller.member.signup.MemberFormController;
import org.teamproject.lottocaptainteam.controller.member.signup.MemberSignupController;
import org.teamproject.lottocaptainteam.controller.member.update.MemberUpdateController;
import org.teamproject.lottocaptainteam.controller.member.update.MemberUpdateFormController;
import org.teamproject.lottocaptainteam.repository.MemberRepositoryImpl;

@WebServlet(name = "filterFrontController", urlPatterns = "/filter/*")
public class FilterFrontController extends HttpServlet {

    private Map<String, MemberController> controllerMap = new HashMap<>();

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
