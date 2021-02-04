package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/api/products")
	public List<Product> list() {
		return service.listAllProducts();
	}

	//api/search?term=something
	@GetMapping("/api/search")
	public List<Item> searchResult(@RequestParam("term") String term) {
		String keyword = term.toLowerCase(Locale.ROOT);
		return service.listAllSearch(keyword);
		//need to be updated with the specific code
	}

}
