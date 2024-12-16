package org.teamproject.lottocaptainteam.service.filtering;

import java.util.HashMap;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;
import org.teamproject.lottocaptainteam.service.filtering.filters.EvenFilter;
import org.teamproject.lottocaptainteam.service.filtering.filters.LottoFilter;
import org.teamproject.lottocaptainteam.service.filtering.filters.OddFilter;
import org.teamproject.lottocaptainteam.service.filtering.filters.SpecificRangeFilter;

public class FilteringMap {

    private final Map<String, LottoFilter> filterMap;

    public FilteringMap() {
        this.filterMap = new HashMap<>();

        filterMap.put("oddFilter" , new OddFilter());
        filterMap.put("evenFilter" , new EvenFilter());
        filterMap.put("specificRangeFilter" , new SpecificRangeFilter());
    }
}
