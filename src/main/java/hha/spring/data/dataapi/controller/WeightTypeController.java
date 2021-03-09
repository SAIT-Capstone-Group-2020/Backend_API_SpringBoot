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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
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
    public List<WeightType> add(@RequestBody WeightType weightType) {

        return service.saveWeightType(weightType);
    }

    @PutMapping("/api/weighttype/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<WeightType> update(@RequestBody WeightType weightType, @PathVariable int id) {
        try {
            WeightType existWeightType = service.getWeightTypeById(id);
            weightType.setId(id);
            return service.saveWeightType(weightType);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weight Type not exists");
        }
    }

    @DeleteMapping("/api/weighttype/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<WeightType> delete(@PathVariable int id) {

        return service.deleteWeightType(id);
    }
}
