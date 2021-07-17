package com.penilaianakademisi.service;

import com.penilaianakademisi.exception.EntityNotFoundEx;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.DecimalFormat;
import java.util.List;

public class AbstractService<T> implements ServiceInterface<T>{

    protected final JpaRepository<T, String> repository;

    public AbstractService(JpaRepository<T, String> repository) {
        this.repository = repository;
    }

    @Override
    public T findById(String id) {
        return repository.findById(id).orElseThrow(EntityNotFoundEx::new);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T deleteById(String id) {
        T entity = findById(id);
        repository.deleteById(id);
        return entity;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    public Double roundUp(Double number){
        return Math.round(number * 1000.0) / 1000.0;
    }
}
