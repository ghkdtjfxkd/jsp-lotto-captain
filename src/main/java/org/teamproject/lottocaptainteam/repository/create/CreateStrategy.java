package org.teamproject.lottocaptainteam.repository.create;

@FunctionalInterface
public interface CreateStrategy<T> {
    T execute(T t);
}
