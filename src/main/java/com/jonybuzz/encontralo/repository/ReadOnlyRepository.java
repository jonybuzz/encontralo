package com.jonybuzz.encontralo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, I> extends Repository<T, I> {

    Optional<T> findById(I var1);

    boolean existsById(I var1);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<I> var1);

    long count();

}
