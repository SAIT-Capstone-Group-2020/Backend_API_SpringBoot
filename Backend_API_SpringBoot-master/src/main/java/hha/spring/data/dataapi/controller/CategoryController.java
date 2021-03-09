package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Category;
import hha.spring.data.dataapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public void add(@RequestBody Category category) {
        service.saveCategory(category);
    }

    @PutMapping("/api/categories/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable int id) {
        try {
            Category existCategory = service.getCategoryById(id);
            category.setId(id);
            service.saveCategory(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/categories/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int id) {
        service.deleteCategory(id);
    }

    String someThing;
}
