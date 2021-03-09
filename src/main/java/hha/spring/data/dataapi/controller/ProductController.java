package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.service.ItemService;
import hha.spring.data.dataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@CrossOrigin
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
	public Page<Item> searchResult(
					@RequestParam(name="term", required = false) String keyword,
					@RequestParam(name="pric", required = false) String price,
					@RequestParam(name="cate", required = false) String category,
					@RequestParam(name="sort", required = false) String sort,
					@RequestParam(name="page", required = false) String page,
					@RequestParam(name="prom", required = false) String prom
	) {

		if(prom != null && prom.equals("y")) {
			Page<Item> result = itemService.listKeywordSearchProm(keyword, price, category, sort, page);
			return result;
		}

		Page<Item> result = itemService.listKeywordSearch(keyword, price, category, sort, page);
		return result;
	}

	@GetMapping("/api/customer/product/{id}")
	public ResponseEntity<Item> get(@PathVariable int id) {
		try {
			Item item = itemService.listSingleItem(id);
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/api/admin/product")
	public List<Product> listAdminSide() {
		return service.listAllProducts();
	}

	@PostMapping("/api/admin/product")
	public List<Product> addProduct(@RequestBody Product prod) {

		Product check = null;
		check = service.findByName(prod.getName());

		if(check != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
		}

		return service.addProduct(prod);
	}

	@PutMapping("/api/admin/product/bulk")
	public List<Product> addProductBulkOw(@RequestBody List<Product> prodList) {

		return service.addProductBulkOw(prodList);
	}

	@PostMapping("/api/admin/product/bulk")
	public List<Product> addProductBulk(@RequestBody List<Product> prodList) {

		return service.addProductBulk(prodList);
	}

	@DeleteMapping("/api/admin/product")
	public List<Product> removeProduct(@RequestParam("id") int id) {

		Product check = null;
		check = service.findById(id);

		if(check == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not exists");
		}

		return service.removeProduct(check);
	}

	@PutMapping("/api/admin/product")
	public List<Product> removeProduct(@RequestBody Product prod){

		Product check = null;
		check = service.findById(prod.getId());

		if(check == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not exists");
		}
		return service.editProduct(prod);

	}

	//api/admin/product/search?term=something
	@GetMapping("/api/admin/product/search")
	public Page<Product> searchResultAdminSide(
			@RequestParam(name="page", required = false) String page,
			@RequestParam(name="name", required = false) String name,
			@RequestParam(name="bran", required = false) String bran,
			@RequestParam(name="cate", required = false) String cate,
			@RequestParam(name="sort", required = false) String sort) {

		return service.searchProductsAdmin(page, name,bran,cate, sort);
	}
}
