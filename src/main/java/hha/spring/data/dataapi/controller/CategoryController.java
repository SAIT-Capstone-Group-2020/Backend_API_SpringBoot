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

    @GetMapping("/api/customer/categories")
    public List<Category> list() {
        return service.listAllCategories();
    }

    @GetMapping("/api/admin/categories")
    public List<Category> listAdminCategory() {
        return service.listAllCategories();
    }

    @GetMapping("/api/customer/categories/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Category> get(@PathVariable int id) {
        try {
            Category category = service.getCategoryById(id);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/admin/categories")
    public void add(@RequestBody Category category) {
        service.saveCategory(category);
    }

    @PutMapping("/api/admin/categories/{id}")
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

    @DeleteMapping("/api/admin/categories/{id}")
    public void delete(@PathVariable int id) {
        service.deleteCategory(id);
    }
}
