package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class SpecificSumFilter implements LottoFilter{
    private static final Random defaultRandom = ThreadLocalRandom.current();
    private List<Integer> lowest;
    private List<Integer> accumulatedSum; // 미리 정의된 누적합
    private final List<Integer> pickedNumbers = new ArrayList<>();

    @Override
    public List<Integer> apply(List<Integer> numbers, Map<String, String> paramMap) {
        lowest = getLowest(List.copyOf(numbers));
        accumulatedSum = accumulatedSumWith(lowest);
        int leftoverSum = Integer.parseInt(paramMap.get("leftoverSum"));

        pickLottoNumbers(leftoverSum);

        return List.of();
    }

    private void pickLottoNumbers(int leftoverSum) {
        pickedNumbers.clear(); // 초기화
        pick(leftoverSum,5);
        if(pickedNumbers.size() !=6) {
            pickLottoNumbers(leftoverSum);
        }
        pickedNumbers.sort(Integer::compareTo);
    }

    private List<Integer> getLowest(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        List<Integer> list = set.stream().sorted().toList();
        return list.subList(0, 6);
    }

    private List<Integer> accumulatedSumWith(List<Integer> lowest) {
        List<Integer> accumulatedSum = new ArrayList<>();
        int sum =0;
        int index = 0;

        while (accumulatedSum.size() < 6) {
            sum += lowest.get(index++);
            accumulatedSum.add(sum);
        }
       return accumulatedSum;
    }


    private void pick(int leftoverSum, int index) {
        if (index < 0) {
            return; // 종료 조건: 모든 자리를 채웠다면 종료
        }

        if(leftoverSum>= accumulatedSum.get(index)) {
            int rangeSize = lowest.get(index) + leftoverSum - accumulatedSum.get(index);
            if(rangeSize >= 45){
                rangeSize = 45;
            }
            int picked = pickNumberInRange(lowest.get(index), rangeSize);
            if(pickedNumbers.contains(picked)) {
                pick(leftoverSum, index);
            } else {
                pickedNumbers.add(picked);
                pick(leftoverSum- picked, index-1);
            }
        }

    }

    private int pickNumberInRange(int startInclusive, int endInclusive) {
        return startInclusive + defaultRandom.nextInt(endInclusive - startInclusive + 1);
    }
}
