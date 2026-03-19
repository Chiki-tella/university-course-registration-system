package com.university.dao;

import java.util.List;

public interface DAO<T> {
    T save(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(Long id);
    List<T> findAll();
}