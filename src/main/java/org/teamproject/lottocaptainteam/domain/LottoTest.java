package org.teamproject.lottocaptainteam.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.json.JSONArray;

public class LottoTest {

    private final long id;
    private final int draw;
    private final String userId;
    private final List<Integer> numbers;
    private final LocalDateTime createdAt;

    private LottoTest(long id, int draw, String userId, List<Integer> numbers) {
        this.id = id;
        this.draw = draw;
        this.userId = userId;
        this.numbers = numbers;
        createdAt = LocalDateTime.now();
    }

    private LottoTest(long id, int draw, String userId, List<Integer> numbers, LocalDateTime createdAt) {
        this.id = id;
        this.draw = draw;
        this.userId = userId;
        this.numbers = numbers;
        this.createdAt = createdAt;
    }

    public static LottoTest of(long id, int draw, String userId, List<Integer> numbers) {
        return new LottoTest(id, draw, userId ,numbers);
    }

    public LottoTest createdAt(LocalDateTime createdAt) {
        return new LottoTest(this.id, this.draw, this.userId , this.numbers, createdAt);
    }

    public boolean isCreatedBy(String userId) {
        return this.userId.equals(userId);
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
        return numbers.stream()
                .sorted()
                .toList();
    }

    public String getNumbersParsedAtJSON() {
        JSONArray jsonArray = new JSONArray(List.copyOf(numbers));
        return jsonArray.toString();
    }

    public int getDraw() {
        return draw;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Timestamp getCreatedAtAsTimestamp() {
        return Timestamp.valueOf(createdAt);
    }
}
