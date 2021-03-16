package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Category;
import hha.spring.data.dataapi.domain.WeightType;
import hha.spring.data.dataapi.service.CategoryService;
import hha.spring.data.dataapi.service.WeightTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class WeightTypeController {

    @Autowired
    private WeightTypeService service;

    @GetMapping("/api/weighttype")
    public List<WeightType> list() {
        return service.listAllWeightType();
    }

    @GetMapping("/api/weighttype/{id}")
    public ResponseEntity<WeightType> get(@PathVariable int id) {
        try {
            WeightType weightType = service.getWeightTypeById(id);
            return new ResponseEntity<WeightType>(weightType, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<WeightType>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/weighttype")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void add(@RequestBody WeightType weightType) {
        service.saveWeightType(weightType);
    }

    @PutMapping("/api/weighttype/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody WeightType weightType, @PathVariable int id) {
        try {
            WeightType existWeightType = service.getWeightTypeById(id);
            weightType.setId(id);
            service.saveWeightType(weightType);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/weighttype/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int id) {
        service.deleteWeightType(id);
    }
}
