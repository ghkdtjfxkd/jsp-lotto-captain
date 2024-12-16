package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsecutiveNumbersFilter implements LottoFilter{

    @Override
    public List<Integer> apply(List<Integer> numbers, Map<String, String> paramMap) {
        return List.of();
    }

    List<Integer> test = new ArrayList<Integer>();

    private void nextNumberCheck(int index) {
        int consecutiveCount = 0;

        if(index > 5) {
            return;
        }

        if(index < 0) {
            return;
        }

        if(test.get(index) + 1 == test.get(index + 1)) {
            consecutiveCount++;
        }

        LocalDateTime now = LocalDateTime.now();

    }
}
