package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.List;

public class SpecificSumFilter implements LottoFilter{

    private final int targetSum;

    public SpecificSumFilter(int targetSum) {
        this.targetSum = targetSum;
    }

    @Override
    public List<Integer> apply(List<Integer> numbers) {
        return List.of();
    }



}
