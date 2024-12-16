package org.teamproject.lottocaptainteam.controller.filter.constant;

public enum LottoRules {
    START_INCLUSIVE(1),
    END_INCLUSIVE(45),
    TOTAL_NUMBER_COUNT(6),

    MAXIMUM_PURCHASE_MONEY(1_000_000),
    ONE_GAME_MONEY(1_000);

    private final int number;

    LottoRules(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }
}
