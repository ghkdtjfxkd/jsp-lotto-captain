package org.teamproject.lottocaptainteam.service.filtering;

import static org.teamproject.lottocaptainteam.service.constant.LottoRegulation.END_INCLUSIVE_NUMBER;
import static org.teamproject.lottocaptainteam.service.constant.LottoRegulation.START_INCLUSIVE_NUMBER;

import java.util.List;
import java.util.stream.IntStream;
import org.teamproject.lottocaptainteam.service.filtering.filters.LottoFilter;
import org.teamproject.lottocaptainteam.service.filtering.validator.LottoNumberValidator;


public abstract class LottoFilteringService {

    private final List<Integer> lottoNumbers;

    protected LottoFilteringService() {
        this.lottoNumbers = allLottoNumbers();
    }

    private List<Integer> allLottoNumbers() {
        return IntStream.rangeClosed(START_INCLUSIVE_NUMBER.getValue(), END_INCLUSIVE_NUMBER.getValue())
                .boxed()
                .toList();
    }

    protected abstract List<LottoFilter> getLottoFilters();
    protected abstract LottoNumberValidator getValidator(List<Integer> numbers);

    public List<Integer> getResult () {
        return List.copyOf(passedAllFilters());
    }

    private List<Integer> passedAllFilters() {
        List<Integer> numbers = lottoNumbers;
        for (LottoFilter filter : getLottoFilters()) {
            numbers = filter.apply(numbers);
            validate(numbers);
        }
        return numbers;
    }

    private void validate(List<Integer> filteredNumbers) {
        LottoNumberValidator validator = getValidator(filteredNumbers);
        validator.execute();
    }

}
