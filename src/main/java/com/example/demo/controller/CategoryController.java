package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Category;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        if (category.getName().trim().equals("")||category.getName().length()<3||category.getName().length()>10){
            return new ResponseEntity<>(new ResponMessage("The category's name is invalid"), HttpStatus.OK);
        }
        if (categoryService.existByName(category.getName())){
            return new ResponseEntity<>(new ResponMessage("The category's name is existed"), HttpStatus.OK);
        }
        categoryService.save(category);
        return new ResponseEntity<>(new ResponMessage("Create success"), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> showListCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<?> pageCategory(@PageableDefault(sort = "name", size = 3)Pageable pageable){
        return new ResponseEntity<>(categoryService.findAll(pageable),HttpStatus.OK);
    }
    @GetMapping("/id")
    public ResponseEntity<?> detailCategory(@PathVariable Long id){
        if (!categoryService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.findById(id).get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category){
        Optional<Category> category1 = categoryService.findById(id);
        if (!category1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (category.getName().trim().equals("")||category.getName().length()<3||category.getName().length()>10){
            return new ResponseEntity<>(new ResponMessage("The category's name is invalid"), HttpStatus.OK);
        }
        if (categoryService.existByName(category.getName())){
            return new ResponseEntity<>(new ResponMessage("The category's name is existed"), HttpStatus.OK);
        }
        category1.get().setName(category.getName());
        categoryService.save(category1.get());
        return new ResponseEntity<>(new ResponMessage("Update Success"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("Delete success!"), HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name){
        if (name.trim().equals("")){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.findAllByNameContaining(name),HttpStatus.OK);
    }
}
