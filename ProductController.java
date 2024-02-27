package com.sts.ProductList.controller;

import com.sts.ProductList.model.Category;
import com.sts.ProductList.model.Product;
import com.sts.ProductList.DTO.ProductDto;
import com.sts.ProductList.Service.CategoryService;
import com.sts.ProductList.Service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
	
	public static String uploadDir = System.clearProperty("user.dir") + "/src/main/resources/static/productimages";
//Category section
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

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
    
//product Section
    @GetMapping("/admin/products")
    public String getProduct(Model model) {
    	model.addAttribute("products",productService.getAllProduct());
    	return "products";
    }
    
    @GetMapping("/admin/products/add")
    public String getProductAdd(Model model) {
    	model.addAttribute("productDTO",new ProductDto());
    	model.addAttribute("categories", categoryService.getAllCategories());
    	return "productsAdd";
    }
    
    @PostMapping("/admin/products/add")
    public String postProductAdd(@ModelAttribute("ProductDto") ProductDto productDto,
    		@RequestParam("productImage")MultipartFile file,
    		@RequestParam("imgName")String imgName ) throws IOException {
    	Product product = new Product();
    	product.setId(productDto.getId());
    	product.setName(productDto.getName());
    	product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
    	product.setPrice(productDto.getPrice());
    	product.setWeight(productDto.getWeight());
    	product.setDescription(productDto.getDescription());
    	
    	String imageUUID;
    	if(!file.isEmpty()) {
    		imageUUID = file.getOriginalFilename();
    		Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
    		Files.write(fileNameAndPath, file.getBytes());
    	}else {
    		imageUUID = imgName;
    	}
    	product.setImageName(imageUUID);
    	productService.addProduct(product);
        
        return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
    	productService.removeProductById(id);
    	return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/products/update/{id}")
    public String updateProduct(@PathVariable int id , Model model) {
    	Optional<Product> product = productService.getProductById(id);
    	if(product.isPresent()) {
    		model.addAttribute("product", product.get());
    		return "productsAdd";
    	}
    	else {
    		return "404";
    	}
    }

}