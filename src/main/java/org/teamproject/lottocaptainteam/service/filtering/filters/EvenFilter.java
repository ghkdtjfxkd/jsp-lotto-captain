package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.List;
import java.util.Map;

public class EvenFilter implements LottoFilter {

    @Override
    public List<Integer> apply(List<Integer> rawNumbers, Map<String, String> paramMap) {
        return List.copyOf(collectedEvenNumbersWith(rawNumbers));
    }

    private List<Integer> collectedEvenNumbersWith(List<Integer> rawNumbers) {
        return rawNumbers.stream()
                .filter(this::isEvenNumber)
                .toList();
    }

    private boolean isEvenNumber(int number) {
        return number % 2 == 0;
    }
}

