package com.sts.ProductList.Repostitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.ProductList.model.Category;
import com.sts.ProductList.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	List<Product> findAllByCategory_Id(int id);
}
