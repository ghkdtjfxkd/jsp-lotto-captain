package org.teamproject.lottocaptainteam.controller.recommend;

import jakarta.servlet.Servlet;
import jakarta.servlet.annotation.WebServlet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.controller.filter.DrawController;
import org.teamproject.lottocaptainteam.domain.LottoTest;
import org.teamproject.lottocaptainteam.domain.Member;
import org.teamproject.lottocaptainteam.parser.DrawParser;
import org.teamproject.lottocaptainteam.parser.JsonParser;
@WebServlet(name = "recommendNumberDrawController", urlPatterns = "/recommend/lottoListTest")
public class RecommendTest implements RecommendController {

        private static long lottoReleased = 0L;

        @Override
        public ModelView process(Map<String, Object> paramMap) {
            Member loggedMember = (Member) paramMap.get("loggedMember");
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

            saveToFile(pickedLotteries);

            ModelView mv = new ModelView("recommend/lottoListTest");
            mv.getModel().put("message", "Lottery recommendations saved to test.txt");
            return mv;
        }

        private void saveToFile(List<LottoTest> pickedLotteries) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"))) {
                for (LottoTest lotto : pickedLotteries) {
                    writer.write(lotto.toString()); // LottoTest 클래스의 toString()을 사용해 문자열 변환
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

