package hha.spring.data.dataapi.service;
import hha.spring.data.dataapi.domain.*;
import hha.spring.data.dataapi.domain.order.*;
import hha.spring.data.dataapi.email.EmailService;
import hha.spring.data.dataapi.repository.ItemRepository;
import hha.spring.data.dataapi.repository.OrderInfoRepository;
import hha.spring.data.dataapi.repository.OrderItemRepository;
import hha.spring.data.dataapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private OrderInfoRepository orderInfoRepo;

    @Autowired
    private EmailService email;

    public String checkOut(OrderDto order) {

        double sum = 0;

            if(!order.getEmail().equals(order.getConfirmEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is not matched");
            }

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date minPaidDate = null;
        try {
            minPaidDate = sdf.parse("1000-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            List<OrderItem> itemList = order.getOrderItem();
            Order newOrder = new Order(new Date(), minPaidDate, 0.0, "pending", order.getEmail(), order.getPhone(), order.getName());

            orderRepo.save(newOrder);

            for (int i = 0; i < itemList.size(); i++) {

                int itemId = itemList.get(i).getId();
                Item item = itemRepo.findByProductId(itemId);
                double total = ((item.getDiscount_price())*100*itemList.get(i).getQuantity())/100;
                orderItemRepo.save(new OrderItem(total, itemId, itemList.get(i).getQuantity(), newOrder.getId()));

                sum += (total*100);
            }

            newOrder.setPriceSum(sum/100);
            orderRepo.save(newOrder);

        }
        catch(NoSuchElementException se) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, se.getMessage());
        }

        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        try {

            String body = "<h1>This is an invoice</h1><br><p>total payment due $"+sum/100+" :)</p>";
            body += "<p>enjoy the test</p>";

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

    public Page<Order> getOrders(
            String status, String orderDate, String paidDate, String category, String product,
            String phone, String email, String customerName, String sort, String page) {

        String sortProp = "orders_id";
        String order = "desc";
        int pageNumber = 1;

        if ( page != null ) pageNumber = Integer.parseInt(page);

        if ( sort != null) {
            sortProp= sort.split(":")[0];
            order = sort.split(":")[1];
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.Direction.fromString(order), sortProp);

        String stat = "";
        String cate = "";
        String prod = "";
        String phon = "";
        String emai = "";
        String name = "";

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Date minOrderDate = null;
        Date maxOrderDate = null;
        Date minPaidDate = null;
        Date maxPaidDate = null;

             try {
            minOrderDate = sdf.parse("2021-01-01");
            maxOrderDate = sdf.parse("3021-01-01");
            minPaidDate = sdf.parse("0000-00-00");
            maxPaidDate = sdf.parse("3021-01-01");

                } catch (ParseException e) {
            e.printStackTrace();
        }

            if(orderDate != null) {
                String[] dates = orderDate.split(":");

                try {
                    minOrderDate = sdf.parse(dates[0]);
                    maxOrderDate = sdf.parse(dates[1]);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        if(paidDate != null) {
            String[] dates = orderDate.split(":");

            try {
                minPaidDate = sdf.parse(dates[0]);
                maxPaidDate = sdf.parse(dates[1]);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        if(status != null) {
            stat = status;
        }

        if(product != null) {
            prod = product;
        }

        if(category != null) {
            cate = category;
        }

        if(phone != null) {
            phon = phone;
        }

        if(email != null) {
            emai = email;
        }

        if(customerName != null) {
            name = customerName;
        }

        return orderRepo.getOrder(
                    stat,
                    minOrderDate, maxOrderDate, minPaidDate, maxPaidDate,
                    cate, prod, phon, emai, name, pageable
            );
    }

    public Order editOrder(Order order) {

        try {
            orderRepo.save(order);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return order;
    }

    public Order findById(int id) {
        return orderRepo.findById(id);
    }

    public List<Order> removeOrder(Order order) {

        orderRepo.delete(order);

        return orderRepo.findAll();
    }

    public OrderInfoDto getOrderItem(int id) {
        List<OrderItemDto> list = orderInfoRepo.listAllItem(id);
        Order order = orderRepo.findById(id);

        OrderInfoDto orderInfo = new OrderInfoDto(order.getEmail(), order.getPhone(), order.getOrderName(), order.getId());
        orderInfo.setItemList(list);

        return orderInfo;
    }

}
