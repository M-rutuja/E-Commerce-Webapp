package com.sts.ProductList.controller;

import com.sts.ProductList.model.Category;
import com.sts.ProductList.Service.CategoryService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategory(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCategoryAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategoryAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    
    @GetMapping("/admin/categories/delete/{id}")
    public String deletecategory(@PathVariable int id) {
    	categoryService.removeCategoryById(id);
    	return "redirect:/admin/categories";
    }
    
    @GetMapping("/admin/categories/update/{id}")
    public String updatecategory(@PathVariable int id , Model model) {
    	Optional<Category> category = categoryService.getCategoryById(id);
    	if(category.isPresent()) {
    		model.addAttribute("category", category.get());
    		return "categoriesAdd";
    	}
    	else {
    		return "404";
    	}
    }

}
