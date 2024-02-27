package com.sts.ProductList.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.sts.ProductList.Repostitory.ProductRepo;
import com.sts.ProductList.model.Category;
import com.sts.ProductList.model.Product;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public void removeProductById(long id) {
        productRepo.deleteById(id);
    }

    public Optional<Product> getProductById(long id) {
        return productRepo.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(int id) {
        return productRepo.findAllByCategory_Id(id);
    }

    // New method to retrieve category by name
    public Category getCategoryByName(String categoryName) {
        // Implement the logic to retrieve category by name from repository
        // For example:
        // return categoryRepo.findByName(categoryName);
        return null; // Placeholder, replace with actual logic
    }
}
