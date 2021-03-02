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

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is not matched");
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

        }
        catch(NoSuchElementException se) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, se.getMessage());
        }

        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        try {

            String body = "<h1>This is an invoice</h1><br><p>you should pay $"+sum/100+"</p>";

            email.sendHtmlMessage(order.getEmail(), "Invoice HHA", body);

        //make html code
        }   catch(MessagingException me) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, me.getMessage());
        }
        //need to check more exception handling

        return "Success";
    }

    public List<Item> checkCart(int[] id) {

        List<Item> list = new ArrayList<>();

        try {

        for(int i=0; i<id.length; i++) {

            Item item = itemRepo.findByProductId(id[i]);

            list.add(item);
        }} catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return list;
    }

}
