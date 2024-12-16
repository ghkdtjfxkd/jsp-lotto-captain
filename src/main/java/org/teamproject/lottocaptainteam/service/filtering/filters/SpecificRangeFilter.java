package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.List;
import java.util.Map;

public class SpecificRangeFilter implements LottoFilter {
    private int startInclusive;
    private int endExclusive;

    @Override
    public List<Integer> apply(List<Integer> numbers, Map<String, String> paramMap) {
        this.startInclusive = Integer.parseInt(paramMap.get("startInclusive"));
        this.endExclusive = Integer.parseInt(paramMap.get("endExclusive"));
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
        return number < this.startInclusive;
    }

    private List<Integer> removeMoreThanStartInclusive(List<Integer> numbers) {
        return numbers.stream()
                .filter(this::moreThanStartInclusiveNumber)
                .toList();
    }

    private boolean moreThanStartInclusiveNumber(int number) {
        return number > this.endExclusive;
    }

}
