package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.*;
import hha.spring.data.dataapi.domain.order.Order;
import hha.spring.data.dataapi.domain.order.OrderDto;
import hha.spring.data.dataapi.domain.order.OrderInfoDto;
import hha.spring.data.dataapi.domain.order.OrderItem;
import hha.spring.data.dataapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/api/customer/order")
    public Message checkOut(@RequestBody OrderDto order) {
        //need to do other error test
        Message message = new Message("ok", service.checkOut(order));

        return message;
    }

    @GetMapping("/api/customer/order")
    public List<Item> checkCart(@RequestParam int[] id) {

        return service.checkCart(id);
    }

    @GetMapping("/api/admin/order")
    public Page<Order> getOrders(
            @RequestParam(name="stat", required = false) String status,
            @RequestParam(name="orde", required = false) String orderDate,
            @RequestParam(name="paid", required = false) String paidDate,
            @RequestParam(name="cate", required = false) String category,
            @RequestParam(name="prod", required = false) String product,
            @RequestParam(name="phon", required = false) String phone,
            @RequestParam(name="emai", required = false) String email,
            @RequestParam(name="name", required = false) String name,
            @RequestParam(name="sort", required = false) String sort,
            @RequestParam(name="page", required = false) String page,
            @RequestParam(name="pageSize", required = false) String pageSize,
            @RequestParam(name="all", required = false) String all

            ) {
        return service.getOrders(status, orderDate, paidDate, category, product, phone, email, name, sort, page, pageSize, all);
    }

    @GetMapping("/api/admin/order/{id}")
    public OrderInfoDto getOrderItems(@PathVariable int id){
        return service.getOrderItem(id);
    }

    @DeleteMapping("/api/admin/order")
    public List<Order> removeOrder(@RequestParam int id) {
        Order check = null;
        check = service.findById(id);

        if(check == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not exists");
        }
        return service.removeOrder(check);
    }

    @PutMapping("/api/admin/order")
    public Order editOrder(@RequestBody Order order) {
        Order check = null;
        check = service.findById(order.getId());

        if(check == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not exists");
        }
        return service.editOrder(order);
    }


}
