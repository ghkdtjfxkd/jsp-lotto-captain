package org.teamproject.lottocaptainteam.parser;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DrawParser {
    private final static LocalDateTime firstDrawDateTime = LocalDateTime.of(2002, 12, 7, 20, 45);
    private final static int drawInterval = 7;

   public static int result() {
       LocalDateTime now = LocalDateTime.now();
       long weeksBetween = ChronoUnit.WEEKS.between(firstDrawDateTime, now);

       // 오늘 날짜에 해당하는 회차 계산
       int currentDrawNumber = (int) (weeksBetween + 1);

       // 추첨 시간이 아직 지나지 않았다면, 이전 회차로 조정
       LocalDateTime lastDrawDateTime = firstDrawDateTime.plusWeeks(currentDrawNumber - 1);
       if (now.isBefore(lastDrawDateTime)) {
           currentDrawNumber--;
       }
       return currentDrawNumber;
   }

    public static void main(String[] args) {
        System.out.println("오늘의 회차는 : " + DrawParser.result());

    }

}
