package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.service.ItemService;
import hha.spring.data.dataapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is a Spring controller which serializes
 * every 'product' table related request handling methods.
 * This controller uses ProductService and ItemService.
 * This allows the cross origin request from all host
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private ItemService itemService;

    /**
     * get all item list
     *
     * @return List of the Item object(Data entity which has all product related information)
     */
    @GetMapping("/api/customer/product")
	public List<Item> list() {
		return itemService.listAllItem();
	}

    /**
     * get product list
     * Return data can be sorted with the parameter value
     * All parameters are optional
     *
     * @param keyword  - any keyword of product_title/brand/category
     * @param price    - price filter
     * @param category - category name
     * @param sort     - sorting option(attribute_name:asc/desc)
     * @param page     - the number of page
     * @param prom     - parameter(y) to get the promotion items only
     * @return List of the Item object(Data entity which has all product related information)
     */
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

    /**
     * get a product description
     *
     * @param id - the id of product
     * @return Object of Item
     */
    @GetMapping("/api/customer/product/{id}")
	public ResponseEntity<Item> get(@PathVariable int id) {
		try {
			Item item = itemService.listSingleItem(id);
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
	}

    /**
     * get all item list
     * Authorization header needed(JWT token)
     *
     * @return List of the Item object(Data entity which has all product related information)
     */
    @GetMapping("/api/admin/product")
	public List<Product> listAdminSide() {
		return service.listAllProducts();
	}

    /**
     * add new product
     * Authorization header needed(JWT token)
     * All parameters are optional
     *
     * @param name        - any keyword of product_title/brand/category
     * @param description - product description
     * @param brand       - brand name
     * @param price       - price filter
     * @param category    - the id of category
     * @param quantity    - the quantity(integer)
     * @param weightValue - weight(double)
     * @param weightType  - the id of weight type
     * @param image_file  - image file(multipart file)
     * @return Updated list of all product
     */
    @PostMapping(value = "/api/admin/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<Product> addProduct(
			@RequestParam(name="name") String name,
			@RequestParam(name="description") String description,
			@RequestParam(name="brand") String brand,
			@RequestParam(name="price") double price,
			@RequestParam(name="category") int category,
			@RequestParam(name="quantity") int quantity,
			@RequestParam(name="weightValue") double weightValue,
			@RequestParam(name="weightType") int weightType,
			@RequestParam(name="image_file") MultipartFile image_file
	) {

		Product check = null;
		check = service.findByName(name);

		if(check != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
		}

		Product prod = new Product(name,description,brand,price,category,quantity,weightValue,weightType);

		return service.addProduct(prod, image_file);
	}

    /**
     * add new product with bulk data
     * if the id of product is 0, then it will be recognized as new product.
     * Otherwise, it will replace the product data which has same id.
     * Authorization header needed(JWT token)
     *
     * @param prodList - List of Product object
     * @return Updated list of all product
     */
    @PostMapping("/api/admin/product/bulk")
	public List<Product> addProductBulk(@RequestBody List<Product> prodList) {

		return service.addProductBulk(prodList);
	}

    /**
     * delete a product(it is a cascading delete)
     *
     * @param id - List of integer value(id of product)
     * @return Updated list of order
     */
    @DeleteMapping("/api/admin/product")
	public List<Product> removeProduct(@RequestParam("id") int id) {

		Product check = null;
		check = service.findById(id);

		if(check == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not exists");
		}

		return service.removeProduct(check);
	}

    /**
     * edit new product
     * Authorization header needed(JWT token)
     * All parameters are optional
     *
     * @param id          - the id of the product
     * @param name        - any keyword of product_title/brand/category
     * @param description - product description
     * @param brand       - brand name
     * @param price       - price filter
     * @param active      - true/false
     * @param category    - the id of category
     * @param quantity    - the quantity(integer)
     * @param weightValue - weight(double)
     * @param weightType  - the id of weight type
     * @param image_file  - image file(multipart file)
     * @return Updated list of all product
     */
    @PutMapping(value="/api/admin/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<Product> editProduct(
			@RequestParam(name="id") int id,
			@RequestParam(name="name") String name,
			@RequestParam(name="description") String description,
			@RequestParam(name="brand") String brand,
			@RequestParam(name="price") double price,
			@RequestParam(name="active") Boolean active,
			@RequestParam(name="category") int category,
			@RequestParam(name="quantity") int quantity,
			@RequestParam(name="weightValue") double weightValue,
			@RequestParam(name="weightType") int weightType,
			@RequestParam(name="image_file") MultipartFile image_file
		)
	{
		Product check = null;
		check = service.findById(id);

		if(check == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not exists");
		}

		return service.editProduct(id, name, description, brand, price, active, category, quantity, weightValue, weightType, image_file);

	}

    /**
     * get each row(data) of product table
     * Authorization header needed(JWT token)
     * All parameters are optional
     *
     * @param page - the number of page
     * @param name - any keyword of product_title
     * @param bran - any keyword of product_brand
     * @param cate - the id of category
     * @param sort - sorting option(attribute_name:asc/desc)
     * @return List of the Item object(Data entity which has all product related information)
     */
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
