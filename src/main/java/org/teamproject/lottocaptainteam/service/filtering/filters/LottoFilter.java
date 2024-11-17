package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.List;

public interface LottoFilter {
    List<Integer> apply(List<Integer> numbers);
}
