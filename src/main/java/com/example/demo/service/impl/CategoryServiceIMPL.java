package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Boolean existByName(String name) {
        return categoryRepository.existByName(name);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> findAllByNameContaining(String name) {
        return categoryRepository.findAllByNameContaining(name);
    }

    @Override
    public Page<Category> findAllByNameContaining(String name, Pageable pageable) {
        return categoryRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

}
