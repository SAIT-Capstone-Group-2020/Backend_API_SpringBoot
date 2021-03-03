package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.OrderDto;
import hha.spring.data.dataapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/api/customer/order")
    public String checkOut(@RequestBody OrderDto order) {

        return service.checkOut(order);
    }

    @GetMapping("/api/customer/order")
    public List<Item> checkCart(@RequestParam int[] id) {

        return service.checkCart(id);
    }


}
