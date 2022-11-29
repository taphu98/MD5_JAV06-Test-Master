package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface IGeneric<T> {
    List<T> findAll();
    Optional<Category> findById(Long id);
    void deleteById(Long id);
    void save(T t);
}
