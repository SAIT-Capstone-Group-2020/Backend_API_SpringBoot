package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.WeightType;
import hha.spring.data.dataapi.service.WeightTypeService;
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
 * every 'weight_type' table related request handling methods.
 * This controller uses WeightTypeService.
 * This allows the cross origin request from all host
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@CrossOrigin
@RestController
public class WeightTypeController {

    @Autowired
    private WeightTypeService service;

    /**
     * list all weight type(id, name)
     *
     * @return list of weight type
     */
    @GetMapping("/api/weighttype")
    public List<WeightType> list() {
        return service.listAllWeightType();
    }

    /**
     * get a data of the specified weight type
     *
     * @param id of weight type - integer value
     * @return WeightType object
     */
    @GetMapping("/api/weighttype/{id}")
    public ResponseEntity<WeightType> get(@PathVariable int id) {
        try {
            WeightType weightType = service.getWeightTypeById(id);
            return new ResponseEntity<WeightType>(weightType, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<WeightType>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * add new weight type
     * Authorization header needed(JWT token)
     *
     * @param weightType - object(JSON) of WeightType class
     * @return Updated weight type list
     */
    @PostMapping("/api/weighttype")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<WeightType> add(@RequestBody WeightType weightType) {

        return service.saveWeightType(weightType);
    }

    /**
     * edit weight type information
     * Authorization header needed(JWT token)
     *
     * @param weightType - object(JSON) of WeightType class
     * @param id         - integer value(id of the weight type)
     * @return Updated weight type list
     */
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

    /**
     * delete weight type
     * Authorization header needed(JWT token)
     *
     * @param id - integer value(id of the weight type)
     * @return Updated weight type list
     */
    @DeleteMapping("/api/weighttype/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<WeightType> delete(@PathVariable int id) {

        return service.deleteWeightType(id);
    }
}
