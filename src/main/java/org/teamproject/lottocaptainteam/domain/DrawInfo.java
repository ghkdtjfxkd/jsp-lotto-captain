package org.teamproject.lottocaptainteam.domain;

import java.util.List;

public class DrawInfo {

    private final int draw;
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public DrawInfo(int draw, List<Integer> winningNumbers, int bonusNumber) {
        this.draw = draw;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int getDraw() {
        return draw;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
