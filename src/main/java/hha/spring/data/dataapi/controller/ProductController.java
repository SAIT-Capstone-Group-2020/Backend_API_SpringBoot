package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/api/products")
	public List<Product> list() {
		return service.listAllProducts();
	}

}
