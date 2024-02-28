package com.sts.ProductList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sts.ProductList.Service.CategoryService;
import com.sts.ProductList.Service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/","/home"})
	public String home (Model model) {
		return "index";
	}
	
	@GetMapping("/shop")
	

}
