package org.teamproject.lottocaptainteam.controller.recommend;

import jakarta.servlet.annotation.WebServlet;
import java.util.List;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.parser.JsonParser;

@WebServlet(name = "recommendControllerImpl", urlPatterns = "/recommend/numberListTest")
public class RecommendControllerImpl implements RecommendController {

    @Override
    public ModelView process(Map<String, Object> paramMap) {
        String numberJson = (String) paramMap.get("visibleCards");
        List<Integer> integers = JsonParser.jsonToIntegerList(numberJson);

        ModelView mv = new ModelView("recommend/numberListTest");
        mv.getModel().put("numberList", integers);

        return mv;
    }
}
