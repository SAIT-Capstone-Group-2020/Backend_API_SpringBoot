package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.*;
import hha.spring.data.dataapi.email.EmailService;
import hha.spring.data.dataapi.repository.ItemRepository;
import hha.spring.data.dataapi.repository.OrderItemRepository;
import hha.spring.data.dataapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private EmailService email;

    public String checkOut(OrderDto order) {

        double sum = 0;

            if(!order.getEmail().equals(order.getConfirmEmail())) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Email is not matched");
            }

        try {

            List<OrderItem> itemList = order.getOrderItem();
            Order newOrder = new Order(new Date(), null, 0.0, "pending", order.getEmail(), order.getPhone(), order.getName());

            orderRepo.save(newOrder);

            for (int i = 0; i < itemList.size(); i++) {

                int itemId = itemList.get(i).getId();
                Item item = itemRepo.findByProductId(itemId);
                double total = ((item.getDiscount_price())*100*itemList.get(i).getQuantity())/100;
                orderItemRepo.save(new OrderItem(total, itemId, itemList.get(i).getQuantity(), newOrder.getId()));

                sum += (total*100);
            }

            newOrder.setSum(sum/100);
            orderRepo.save(newOrder);


        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        try {
        email.sendSimpleMessage(order.getEmail(), "Invoice HHA",
                "This is an invoice/n" +
                        "you should pay $"+sum);
        //make html code
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
            return "Successfully sent";
    }

    public List<CartItem> checkCart(List<OrderItem> cart) {

        List<CartItem> list = new ArrayList<>();

        try {

        for(int i=0; i<cart.size(); i++) {

            int itemId = cart.get(i).getId();
            Item item = itemRepo.findByProductId(itemId);

            int quantity = cart.get(i).getQuantity();

            double total = ((item.getDiscount_price())*100*quantity)/100;
            double orignalTotal = ((item.getOriginal_price())*100*quantity)/100;
            double weightTotal = ((item.getWeight_value()*100)*quantity)/100;

            CartItem cartItem = new CartItem(
                itemId, total, orignalTotal, item.getIs_discount(), quantity, item.getProduct_name(),
                    item.getCategory_name(),item.getBrand_name(), weightTotal,
                    item.getWeight_type_name(), item.getImage_url()
            );

            list.add(cartItem);
        } } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return list;
    }

}
