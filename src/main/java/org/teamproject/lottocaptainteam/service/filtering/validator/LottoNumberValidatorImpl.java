package org.teamproject.lottocaptainteam.service.filtering.validator;

import java.util.HashSet;
import java.util.List;
import org.teamproject.lottocaptainteam.service.filtering.constant.ErrorMessage;
import org.teamproject.lottocaptainteam.service.constant.LottoRegulation;


public class LottoNumberValidatorImpl implements LottoNumberValidator {

    private final List<Integer> filteredNumbers;

    public LottoNumberValidatorImpl(List<Integer> filteredNumbers) {
        this.filteredNumbers = filteredNumbers;
    }

    @Override
    public void execute() {
        emptyCheck();
        enoughNumberCheck();
        sameNumberExistCheck();
    }

    private void emptyCheck() {
        if(filteredNumbers == null || filteredNumbers.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY.get());
        }
    }

    private void enoughNumberCheck() {
        if(filteredNumbers.size() < LottoRegulation.BALLS_TO_DRAW.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ENOUGH_NUMBERS.get());
        }
    }

    private void sameNumberExistCheck() {
        if(sameNumberExist()) {
            throw new IllegalArgumentException(ErrorMessage.SAME_NUMBER_EXIST.get());
        }
    }

    private boolean sameNumberExist() {
        return filteredNumbers.size() == new HashSet<>(List.copyOf(filteredNumbers)).size();
    }
}
