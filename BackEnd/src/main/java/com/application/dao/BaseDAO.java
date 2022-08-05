package com.application.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDAO<T> {

    T create(T t);

    Optional<T> read(Serializable id);

    Page<T> readAll(Pageable pageable);

    T update(T t);

    boolean delete(Serializable id);
}
