package org.teamproject.lottocaptainteam.controller.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RangeFilterTest {
    private static final Random defaultRandom = ThreadLocalRandom.current();
    private List<Integer> lowest = getLowest(List.of(1,2,3,4,5,6,7,8,9,10,12));
    private List<Integer> accumulatedSum = new ArrayList<>(); // 미리 정의된 누적합
    private final List<Integer> pickedNumbers = new ArrayList<>();

    public void pickLottoNumbers(int leftoverSum) {
        stackAccumulatedSum(0, 0);
        pickedNumbers.clear(); // 초기화
        pick2(leftoverSum,5);
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

    private void stackAccumulatedSum(int index, int sum) {
        if (accumulatedSum.size() >= 6) {
            return;
        }
        sum += lowest.get(index);
        accumulatedSum.add(sum);

        stackAccumulatedSum(index + 1, sum);
    }


    private void pick2(int leftoverSum, int index) {
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
                pick2(leftoverSum, index);
            } else {
                pickedNumbers.add(picked);
                pick2(leftoverSum- picked, index-1);
            }
        }

    }

    public static int pickNumberInRange(int startInclusive, int endInclusive) {
        return startInclusive + defaultRandom.nextInt(endInclusive - startInclusive + 1);
    }

    public List<Integer> getPickedNumbers() {
        return pickedNumbers;
    }

    public static void main(String[] args) {
        RangeFilterTest rangeFilterTest = new RangeFilterTest();
        for (int i = 0; i < 50; i++) {
            rangeFilterTest.pickLottoNumbers(100);
            System.out.println("Picked Numbers (" + i +  ") :"+ rangeFilterTest.getPickedNumbers());
        }
        System.out.println(rangeFilterTest.accumulatedSum.toString());
    }
}
