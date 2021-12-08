package com.application.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseOperationsDAO<T> {

    Optional<T> create(T t);

    Optional<T> read(Serializable id);

    boolean update(T t);

    boolean delete(Serializable id);

    List<T> readAll();
}
