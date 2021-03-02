package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.CartItem;
import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.OrderDto;
import hha.spring.data.dataapi.domain.OrderItem;
import hha.spring.data.dataapi.security.LoginDto;
import hha.spring.data.dataapi.service.OrderService;
import hha.spring.data.dataapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/api/customer/order")
    public String checkOut(@RequestBody OrderDto order) {

        return service.checkOut(order);
    }

    @GetMapping("/api/customer/order")
    public List<CartItem> checkCart(@RequestBody List<OrderItem> cart) {

        return service.checkCart(cart);
    }

}
