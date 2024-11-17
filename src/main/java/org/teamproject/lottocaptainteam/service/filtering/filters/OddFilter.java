package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.List;

public class OddFilter implements LottoFilter {

    @Override
    public List<Integer> apply(List<Integer> rawNumbers) {
        return List.copyOf(collectedOddNumbersWith(rawNumbers));
    }

    private List<Integer> collectedOddNumbersWith(List<Integer> rawNumbers) {
        return rawNumbers.stream()
                .filter(this::isOddNumber)
                .toList();
    }

    private boolean isOddNumber(int number) {
        return number % 2 != 0;
    }
}
