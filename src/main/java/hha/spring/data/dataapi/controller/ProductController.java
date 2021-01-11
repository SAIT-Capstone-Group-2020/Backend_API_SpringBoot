package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public Page<Product> list(@Param("filter") String filter,@Param("page") String page) {
		return service.listAllProducts(page,"name",filter);
	}

}
