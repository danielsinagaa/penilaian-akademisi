package com.penilaianakademisi.service;

import java.util.List;

public interface ServiceInterface<T> {
    T findById(String id);
    List<T> findAll();
    T deleteById(String id);
    T save(T entity);
}
