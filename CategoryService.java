package com.sts.ProductList.Service;

import com.sts.ProductList.Repostitory.CategoryRepo;
import com.sts.ProductList.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void addCategory(Category category) {
        categoryRepo.save(category);
    }
    
    public void removeCategoryById(int id) {
    	categoryRepo.deleteById(id);
    }
    
    public Optional<Category> getCategoryById(int id) {
    	return categoryRepo.findById(id);
    }
}
