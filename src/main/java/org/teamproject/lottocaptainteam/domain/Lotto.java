package org.teamproject.lottocaptainteam.domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int matchedAt(final List<Integer> winningNumbers) {
        return (int) winningNumbers.stream()
                .filter(this::includes)
                .count();
    }

    public boolean includes(final int selectedNumber) {
        return numbers.contains(selectedNumber);
    }
}
