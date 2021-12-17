package com.application.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {

    T create(T t);

    Optional<T> read(Serializable id);

    List<T> readAll();

    T update(T t);

    boolean delete(Serializable id);
}
