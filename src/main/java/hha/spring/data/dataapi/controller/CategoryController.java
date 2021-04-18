package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Category;
import hha.spring.data.dataapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is a Spring controller which serializes
 * every 'category' table related request handling methods.
 * This controller uses CategoryService.
 * This allows the cross origin request from all host
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    /**
     * list all categories(id, name)
     *
     * @return list of category
     */
    @GetMapping("/api/categories")
    public List<Category> list() {
        return service.listAllCategories();
    }

    /**
     * get a data of the specified category
     *
     * @param id of category - integer value
     * @return Category object
     */
    @GetMapping("/api/categories/{id}")
    public ResponseEntity<Category> get(@PathVariable int id) {
        try {
            Category category = service.getCategoryById(id);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * add new category
     * Authorization header needed(JWT token)
     *
     * @param category - object(JSON) of Category class
     * @return Updated category list
     */
    @PostMapping("/api/categories")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Category> add(@RequestBody Category category) {

        return service.saveCategory(category);
    }

    /**
     * edit category information
     * Authorization header needed(JWT token)
     *
     * @param category - object(JSON) of Category class
     * @param id       - integer value(id of the category)
     * @return Updated category list
     */
    @PutMapping("/api/categories/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Category> update(@RequestBody Category category, @PathVariable int id) {
        try {
            Category existCategory = service.getCategoryById(id);
            category.setId(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not exists");
        }

        return service.saveCategory(category);

    }

    /**
     * delete category
     * Authorization header needed(JWT token)
     *
     * @param id - integer value(id of the category)
     * @return Updated category list
     */
    @DeleteMapping("/api/categories/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Category> delete(@PathVariable int id) {

        return service.deleteCategory(id);
    }
}
