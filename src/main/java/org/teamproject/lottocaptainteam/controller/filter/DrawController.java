package org.teamproject.lottocaptainteam.controller.filter;

import static org.teamproject.lottocaptainteam.controller.filter.constant.ErrorMessage.END_INCLUSIVE_IS_TOO_BIG;
import static org.teamproject.lottocaptainteam.controller.filter.constant.ErrorMessage.LESS_THAN_ZERO_COUNT;
import static org.teamproject.lottocaptainteam.controller.filter.constant.ErrorMessage.START_INCLUSIVE_BIGGER_THAN_END_INCLUSIVE;
import static org.teamproject.lottocaptainteam.controller.filter.constant.ErrorMessage.TOO_LARGE_RANGE;
import static org.teamproject.lottocaptainteam.controller.filter.constant.ErrorMessage.TOO_MANY_COUNT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.teamproject.lottocaptainteam.controller.filter.constant.LottoRules;

public class DrawController {


    private static final Random defaultRandom = ThreadLocalRandom.current();

    public static List<Integer> draw() {
        return pickUniqueNumbersInRange(LottoRules.START_INCLUSIVE.get(), LottoRules.END_INCLUSIVE.get(),
                LottoRules.TOTAL_NUMBER_COUNT.get());
    }

    public static int pickNumberInList(List<Integer> numbers) {
        validateNumbers(numbers);
        return (Integer) numbers.get(pickNumberInRange(0, numbers.size() - 1));
    }

    public static int pickNumberInRange(int startInclusive, int endInclusive) {
        validateRange(startInclusive, endInclusive);
        return startInclusive + defaultRandom.nextInt(endInclusive - startInclusive + 1);
    }

    private static List<Integer> pickUniqueNumbersInRange(int startInclusive, int endInclusive, int count) {
        validateRange(startInclusive, endInclusive);
        validateCount(startInclusive, endInclusive, count);
        List<Integer> numbers = new ArrayList<>();

        for (int i = startInclusive; i <= endInclusive; ++i) {
            numbers.add(i);
        }
        return shuffle(numbers).subList(0, count);
    }

    private static <T> List<T> shuffle(List<T> list) {
        List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }


    private static void validateNumbers(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("numbers cannot be empty.");
        }
    }


    private static void validateRange(int startInclusive, int endInclusive) throws IllegalArgumentException {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException(START_INCLUSIVE_BIGGER_THAN_END_INCLUSIVE.get());
        } else if (endInclusive == Integer.MAX_VALUE) {
            throw new IllegalArgumentException(END_INCLUSIVE_IS_TOO_BIG.get());
        } else if (endInclusive - startInclusive >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException(TOO_LARGE_RANGE.get());
        }
    }

    private static void validateCount(int startInclusive, int endInclusive, int count) throws IllegalArgumentException {
        if (count < 0) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_COUNT.get());
        } else if (endInclusive - startInclusive + 1 < count) {
            throw new IllegalArgumentException(TOO_MANY_COUNT.get());
        }
    }
}

