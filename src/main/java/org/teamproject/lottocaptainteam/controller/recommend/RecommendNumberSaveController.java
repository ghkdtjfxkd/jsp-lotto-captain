package org.teamproject.lottocaptainteam.controller.recommend;

import java.util.List;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.domain.LottoTest;
import org.teamproject.lottocaptainteam.domain.Member;
public class RecommendNumberSaveController implements RecommendController {

    @Override
    public ModelView process(Map<String, Object> paramMap) {
        Member loggedMember =(Member) paramMap.get("loggedMember");

        return null;
    }
}
