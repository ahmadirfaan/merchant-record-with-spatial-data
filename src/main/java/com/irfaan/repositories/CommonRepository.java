package com.irfaan.repositories;


import java.util.List;

public interface CommonRepository<T, ID> {
    List<T> findAll();

    T save(T t);

    T findById(ID id);

    T update(T t, ID id);

    T removeByID(ID id);

}
