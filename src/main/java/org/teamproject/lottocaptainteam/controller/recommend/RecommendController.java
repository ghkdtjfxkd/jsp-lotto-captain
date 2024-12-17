package org.teamproject.lottocaptainteam.controller.recommend;

import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;

@FunctionalInterface
public interface RecommendController {
    ModelView process(Map<String, Object> paramMap);
}
