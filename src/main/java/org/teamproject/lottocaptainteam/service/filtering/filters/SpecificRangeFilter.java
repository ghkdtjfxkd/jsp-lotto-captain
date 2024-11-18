package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.List;

public class SpecificRangeFilter implements LottoFilter {

    private final int startInclusive;
    private final int endExclusive;

    public SpecificRangeFilter(int startInclusive, int endExclusive) {
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    @Override
    public List<Integer> apply(List<Integer> numbers) {
        return List.copyOf(removeOutRange(numbers));
    }

    private List<Integer> removeOutRange(List<Integer> numbers) {
        List<Integer> removeSmallNumbers = removeLessThanStartInclusive(numbers);
        return removeMoreThanStartInclusive(removeSmallNumbers);
    }

    private List<Integer> removeLessThanStartInclusive(List<Integer> numbers) {
        return numbers.stream()
                .filter(this::lessThanStartInclusiveNumber)
                .toList();
    }

    private boolean lessThanStartInclusiveNumber(int number) {
        return number < startInclusive;
    }

    private List<Integer> removeMoreThanStartInclusive(List<Integer> numbers) {
        return numbers.stream()
                .filter(this::moreThanStartInclusiveNumber)
                .toList();
    }

    private boolean moreThanStartInclusiveNumber(int number) {
        return number > endExclusive;
    }

}
