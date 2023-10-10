package com.springbootProject.Main.service;

import com.springbootProject.Main.model.Category;
import com.springbootProject.Main.model.Product;
import com.springbootProject.Main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getCat(){
        return categoryRepository.findAll();

    }

    public void addCategory(Category category){
        categoryRepository.save(category);

    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);

    }
    public Optional<Category> updateCategory(int id){
           return categoryRepository.findById(id);

     }






}
