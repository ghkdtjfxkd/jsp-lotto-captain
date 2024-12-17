package org.teamproject.lottocaptainteam.controller.recommend;

import jakarta.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.filter.DrawController;
import org.teamproject.lottocaptainteam.domain.LottoTest;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.parser.DrawParser;
import org.teamproject.lottocaptainteam.parser.JsonParser;

//@WebServlet(name = "recommendNumberDrawController", urlPatterns = "/recommend/lottoListTest")
public class RecommendNumberDrawController implements RecommendController {

    private static long lottoReleased =0L;

    @Override
    public ModelView process(Map<String, Object> paramMap) {
        Member loggedMember =(Member) paramMap.get("loggedMember");
        String numberJson = (String) paramMap.get("visibleCards");
        List<Integer> integers = JsonParser.jsonToIntegerList(numberJson);

        List<LottoTest> pickedLotteries = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            List<Integer> recommendSet = DrawController.draw(integers);

            LottoTest recommend = LottoTest.of(
                    lottoReleased++,
                    DrawParser.result(),
                    loggedMember.getId(),
                    recommendSet);

            pickedLotteries.add(recommend);
        }

        ModelView mv = new ModelView("recommend/lottoListTest");
        mv.getModel().put("lotto", integers);
        mv.getModel().put("lottoList", pickedLotteries);

        return mv;
    }
}
