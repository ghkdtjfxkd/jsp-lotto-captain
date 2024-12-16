package org.teamproject.lottocaptainteam.domain;

import java.util.List;

public class Lotto {

    private final long id;
    private final List<Integer> numbers;

    private Lotto(long id, List<Integer> numbers) {
        this.id = id;
        this.numbers = numbers;
    }

    private static Lotto of(long id , List<Integer> numbers) {
        return new Lotto(id,numbers);
    }

    public int matchedAt(final List<Integer> winningNumbers) {
        return (int) winningNumbers.stream()
                .filter(this::includes)
                .count();
    }

    public boolean includes(final int selectedNumber) {
        return numbers.contains(selectedNumber);
    }

    public int numberSum() {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public long getId() {
        return id;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
