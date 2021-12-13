package com.application.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// TODO interface name
public interface BaseOperationsDAO<T> {

    // TODO why optional?
    Optional<T> create(T t);

    Optional<T> read(Serializable id);

    T update(T t);

    boolean delete(Serializable id);

    // TODO better to place it after the read method
    List<T> readAll();
}
