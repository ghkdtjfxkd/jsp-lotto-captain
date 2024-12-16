package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.List;
import java.util.Map;

public interface LottoFilter {
    List<Integer> apply(List<Integer> numbers, Map<String, String> paramMap);
}
