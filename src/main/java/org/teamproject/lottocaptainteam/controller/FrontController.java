package org.teamproject.lottocaptainteam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.member.MemberController;

public class FrontController {
//
//    private Map<String, MemberController> controllerMap = new HashMap<>();
//
//    public FrontController() {
//        controllerMap.put("/viewsrm", new MemberFormControllerV4());
//    }
//
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String requestURI = request.getRequestURI();
//        ControllerV4 controller = controllerMap.get(requestURI);
//        if (controller == null) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        Map<String, String> paramMap = createParamMap(request);
//        Map<String, Object> model = new HashMap<>(); //추가
//        String viewName = controller.process(paramMap, model);
//        MyView view = viewResolver(viewName);
//        view.render(model, request, response);
//    }
//
//    private Map<String, String> createParamMap(HttpServletRequest request) {
//        Map<String, String> paramMap = new HashMap<>();
//        request.getParameterNames().asIterator()
//                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
//        return paramMap;
//    }
//
//    private MyView viewResolver(String viewName) {
//        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
//    }
}

