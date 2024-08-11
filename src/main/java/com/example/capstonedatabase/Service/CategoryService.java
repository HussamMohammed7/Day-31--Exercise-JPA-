package com.example.capstonedatabase.Service;


import com.example.capstonedatabase.Model.Category;
import com.example.capstonedatabase.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService {


    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {

        categoryRepository.save(category);
    }

    public boolean updateCategory(Integer id, Category category) {


        Category updatedCategory = categoryRepository.getById(id);
        if (updatedCategory.getName() == null) {
            return false;
        }

        updatedCategory.setName(category.getName());
        categoryRepository.save(updatedCategory);
        return true ;

    }

    public boolean removeCategory(Integer id) {

        Category category = categoryRepository.getById(id);
        if (category.getName() == null) {
            return false;
        }
        categoryRepository.delete(category);
        return true ;

    }



}