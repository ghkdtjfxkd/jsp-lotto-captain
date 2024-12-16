package org.teamproject.lottocaptainteam.controller.filter.constant;

public enum ErrorMessage {

    START_INCLUSIVE_BIGGER_THAN_END_INCLUSIVE("범위 시작 숫자가 범위 종료 숫자보다 큽니다."),
    END_INCLUSIVE_IS_TOO_BIG("범위 종료 숫자가 너무 큽니다."),
    TOO_LARGE_RANGE("범위 간격이 너무 넓습니다."),
    LESS_THAN_ZERO_COUNT("0 보다 작은 횟수를 입력하였습니다."),
    TOO_MANY_COUNT("너무 큰 횟수를 입력하였습니다.");

    private final String ERROR_HEADER = "[ERROR]";
    private final String ERROR_TAIL = System.lineSeparator();

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
    public String get() {
        return ERROR_HEADER + message + ERROR_TAIL;
    }
}
