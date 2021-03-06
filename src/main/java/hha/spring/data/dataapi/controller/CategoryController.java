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

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/api/categories")
    public List<Category> list() {
        return service.listAllCategories();
    }

    @GetMapping("/api/categories/{id}")
    public ResponseEntity<Category> get(@PathVariable int id) {
        try {
            Category category = service.getCategoryById(id);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/categories")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Category> add(@RequestBody Category category) {

        return service.saveCategory(category);
    }

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

    @DeleteMapping("/api/categories/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Category> delete(@PathVariable int id) {

        return service.deleteCategory(id);
    }
}
