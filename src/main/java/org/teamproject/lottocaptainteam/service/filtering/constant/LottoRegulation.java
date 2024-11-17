package org.teamproject.lottocaptainteam.service.filtering.constant;

public enum LottoRegulation {

    START_INCLUSIVE_NUMBER(1),
    END_INCLUSIVE_NUMBER(45),
    BALLS_TO_DRAW(6);

    private final int value;

    LottoRegulation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
