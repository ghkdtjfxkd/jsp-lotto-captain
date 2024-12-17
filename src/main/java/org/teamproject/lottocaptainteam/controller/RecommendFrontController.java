package org.teamproject.lottocaptainteam.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.teamproject.lottocaptainteam.config.LottoDBConfig;
import org.teamproject.lottocaptainteam.config.MemberDBConfig;
import org.teamproject.lottocaptainteam.config.NewDBConfig;
import org.teamproject.lottocaptainteam.controller.recommend.RecommendController;
import org.teamproject.lottocaptainteam.controller.recommend.RecommendControllerImpl;
import org.teamproject.lottocaptainteam.controller.recommend.RecommendNumberDrawController;
import org.teamproject.lottocaptainteam.repository.LottoNumberRepositoryImpl;
import org.teamproject.lottocaptainteam.repository.MemberRepository;
import org.teamproject.lottocaptainteam.repository.MemberRepositoryImpl;

@WebServlet(name = "recommendFrontController", urlPatterns = "/recommend/*")
public class RecommendFrontController extends HttpServlet {

    private final Map<String, RecommendController> controllerMap = new HashMap<>();
    private final MemberDBConfig memberDBConfig;
    private final LottoDBConfig lottoDBConfig;

    public RecommendFrontController() throws IOException {
        NewDBConfig dbConfig = new NewDBConfig();

        memberDBConfig = dbConfig.loadMemberDBConfig();
        lottoDBConfig = dbConfig.loadLottoDBConfig();
        initDB();

        MemberRepository memberRepository = MemberRepositoryImpl.getInstance();
        LottoNumberRepositoryImpl lottoNumberRepository = LottoNumberRepositoryImpl.getInstance();

        controllerMap.put("/recommend/number-pool", new RecommendNumberDrawController());

    }

    private void initDB() throws IOException {
        MemberRepositoryImpl.initialize(
                memberDBConfig.memberSignupStrategy(),
                memberDBConfig.memberSearchStrategy(),
                memberDBConfig.memberUpdateStrategy(),
                memberDBConfig.memberDeleteStrategy()
        );

        LottoNumberRepositoryImpl.initialize(
                lottoDBConfig.lotttoCreateStrategy(),
                lottoDBConfig.readLottoListByUserIdStrategy(),
                lottoDBConfig.readLottoListByDraw()
        );
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 요청 URI에 따른 컨트롤러 검색
        RecommendController controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 컨트롤러 호출
        Map<String, Object> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private Map<String, Object> createParamMap(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        paramMap.put("loggedMember", request.getSession().getAttribute("loggedMember"));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
