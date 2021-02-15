package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.service.ItemService;
import hha.spring.data.dataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private ItemService itemService;

	@GetMapping("/api/customer/product")
	public List<Item> list() {
		return itemService.listAllItem();
	}

	//api/search?term=something
	@GetMapping("/api/customer/search")
	public List<Item> searchResult(@RequestParam("term") String term) {

		String keyword = term.toLowerCase(Locale.ROOT);
		List<Item> result = itemService.listAllSearch(keyword);
		return result;
		//need to be updated with the specific code
	}

	@GetMapping("/api/admin/product")
	public List<Product> listAdminSide() {
		return service.listAllProducts();
	}

	@PutMapping("/api/admin/product/add")
	public String addProduct(@RequestBody Product prod) {

		Product check = null;
		check = service.findByName(prod.getName());

		if(check != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
		}

		String result = service.addProduct(prod);

		return result;
	}

	@DeleteMapping("/api/admin/product/remove")
	public String removeProduct(@RequestParam("id") int id) {

		Product check = null;
		check = service.findById(id);

		if(check == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not exists");
		}

		String result = service.removeProduct(check);

		return result;
	}

	@PutMapping("/api/admin/product/edit")
	public String removeProduct(@RequestBody Product prod){

		Product check = null;
		check = service.findById(prod.getId());

		if(check == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not exists");
		}

		String result = service.editProduct(prod);

		return result;
	}

	//api/admin/product/search?term=something
	@GetMapping("/api/admin/product/search")
	public List<Product> searchResultAdminSide(@RequestParam("term") String term) {

		String keyword = term.toLowerCase(Locale.ROOT);
		List<Product> result = service.listAllSearch(keyword);
		return result;
	}
}
