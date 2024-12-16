package org.teamproject.lottocaptainteam.repository.read;

import java.util.List;


@FunctionalInterface
public interface ReadListStrategy<T> {
    List<T> execute(String infoForSearch);
}
