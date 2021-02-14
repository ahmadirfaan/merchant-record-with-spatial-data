package com.irfaan.services;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CommonService<T, ID> {
    List<T> findAll();

    T save(T t);

    T findById(ID id);

    T update(T t, ID id);

    T removeByID(ID id);

}
