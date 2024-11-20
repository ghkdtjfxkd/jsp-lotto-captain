package org.teamproject.lottocaptainteam.service.entry;

import java.util.List;

@FunctionalInterface
public interface CreateEntryService<T> {
    List<T> create();
}
