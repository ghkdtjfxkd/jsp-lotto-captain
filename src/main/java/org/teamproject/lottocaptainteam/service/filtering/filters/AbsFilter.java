package org.teamproject.lottocaptainteam.service.filtering.filters;

import java.util.List;

public abstract class AbsFilter {

    private final String name;
    private final String type;

    protected AbsFilter(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public abstract boolean nameIsMatched();
    public abstract boolean typeIsMatched();

    public abstract List<Integer> apply(List<Integer> numbers);


}
