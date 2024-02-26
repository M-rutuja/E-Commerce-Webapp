package com.sts.ProductList.Repostitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.ProductList.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>  {

}
