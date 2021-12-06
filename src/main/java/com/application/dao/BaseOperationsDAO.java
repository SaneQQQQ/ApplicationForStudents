package com.application.dao;

import java.util.List;
import java.util.Optional;

public interface BaseOperationsDAO<T> {

    void create(T t);

    Optional<T> read(Long id);

    void update(T t);

    void delete(Long id);

    List<T> getAll();
}
