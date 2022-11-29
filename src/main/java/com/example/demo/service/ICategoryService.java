package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.service.IGeneric;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService extends IGeneric<Category> {
    Boolean existByName(String name);
    Page<Category> findAll(Pageable pageable);
    List<Category> findAllByNameContaining(String name);
    Page<Category> findAllByNameContaining(String name, Pageable pageable);
}
