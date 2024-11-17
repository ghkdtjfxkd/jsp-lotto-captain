package org.teamproject.lottocaptainteam.service.filtering.constant;

public enum ErrorMessage {

    EMPTY("비어 있습니다."),
    NOT_ENOUGH_NUMBERS("필터링 된 숫자가 6개 이하 입니다."),
    SAME_NUMBER_EXIST("동일한 숫자를 포함하고 있습니다.");

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
