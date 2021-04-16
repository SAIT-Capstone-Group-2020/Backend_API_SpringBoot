package hha.spring.data.dataapi.controller;

import hha.spring.data.dataapi.domain.*;
import hha.spring.data.dataapi.domain.order.Order;
import hha.spring.data.dataapi.domain.order.OrderDto;
import hha.spring.data.dataapi.domain.order.OrderInfoDto;
import hha.spring.data.dataapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * This class is a Spring controller which serializes
 * every 'orders' & 'order_items' table related request handling methods.
 * This controller uses OrderService.
 * This allows the cross origin request from all host
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    /**
     * checkout request from the customer side
     *
     * @param order - object(JSON) of OrderDto class
     * @return Message(success or error occurred) message
     */
    @PostMapping("/api/customer/order")
    public Message checkOut(@RequestBody OrderDto order) {

        Message message = new Message("ok", service.checkOut(order));
        return message;
    }

    /**
     * get list of items in the cart
     *
     * @param id - List of integer value(id of product)
     * @return List of the Item class object
     */
    @GetMapping("/api/customer/order")
    public List<Item> checkCart(@RequestParam int[] id) {

        return service.checkCart(id);
    }

    /**
     * get each row(data) of orders table
     * Authorization header needed(JWT token)
     * All parameters are optional
     *
     * @param status    - the status of the order
     * @param orderDate - ordered date
     * @param paidDate  - paid date
     * @param category  - the id of category
     * @param product   - the id of product
     * @param phone     - phone number of the customer
     * @param email     - email address of the customer
     * @param name      - name of the customer
     * @param sort      - sorting option(attribute_name:asc/desc)
     * @param page      - the number of page
     * @param pageSize  - the size of the one page
     * @param all       - option when admin wants to get all information without pagination
     * @return List of the Order class object
     */
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

    /**
     * get the detail information of the specified order
     *
     * @param id - integer value(the id of the order)
     * @return the object of OrderInfoDto(detail information of the order)
     */
    @GetMapping("/api/admin/order/{id}")
    public OrderInfoDto getOrderItems(@PathVariable int id){
        return service.getOrderItem(id);
    }

    /**
     * delete a order(it is a cascading delete)
     *
     * @param id - List of integer value(id of product)
     * @return Updated list of order
     */
    @DeleteMapping("/api/admin/order")
    public List<Order> removeOrder(@RequestParam int id) {
        Order check = null;
        check = service.findById(id);

        if(check == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not exists");
        }
        return service.removeOrder(check);
    }

    /**
     * edit a order(it is a cascading update for its order_items)
     *
     * @param order - the object(JSON) of the order data
     * @return Updated order object
     */
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
