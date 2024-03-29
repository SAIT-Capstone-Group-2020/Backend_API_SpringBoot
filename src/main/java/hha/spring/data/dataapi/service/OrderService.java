package hha.spring.data.dataapi.service;
import hha.spring.data.dataapi.domain.*;
import hha.spring.data.dataapi.domain.order.*;
import hha.spring.data.dataapi.email.EmailService;
import hha.spring.data.dataapi.repository.ItemRepository;
import hha.spring.data.dataapi.repository.order.OrderInfoRepository;
import hha.spring.data.dataapi.repository.order.OrderItemRepository;
import hha.spring.data.dataapi.repository.order.OrderRepository;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is a business logic to manage order data
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
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

    /**
     * Check out string.
     *
     * @param order the order
     * @return the string
     */
    public String checkOut(OrderDto order) {

        double sum = 0;
        double gst = 0;

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

        String body = "<h3>Hiep Hoa Asian Food Market</h3>";
        String da = sdf.format(new Date());
        body += "<p>"+da+"</p><br>";
        body += "<table style='border:none; width:350px;'><tr><th>item</th><th>qty</th><th>price</th></tr>";

        try {

            List<OrderItem> itemList = order.getOrderItem();
            Order newOrder = new Order(new Date(), minPaidDate, 0.0, "pending", order.getEmail(), order.getPhone(), order.getName());

            orderRepo.save(newOrder);

            for (int i = 0; i < itemList.size(); i++) {

                int itemId = itemList.get(i).getId();
                Item item = itemRepo.findByProductId(itemId);
                double total = ((item.getDiscount_price())*100*itemList.get(i).getQuantity())/100;
                orderItemRepo.save(new OrderItem(total, itemId, itemList.get(i).getQuantity(), newOrder.getId()));
                body += "<tr><td>"+item.getProduct_name()+"</td><td>"+ itemList.get(i).getQuantity()+"</td><td>"+total+"</td></tr>";
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
            double subT = sum/100;
            gst = Math.floor(sum*0.05)/100;
            double total = Math.floor((gst + subT)*100)/100;
            body += "</table style='border:none; width:300px;'><br><table><tr><td>SUBTOTAL</td><td>"+subT+"</td></tr>";
            body += "<tr><td>TAX</td><td>"+gst+"</td></tr>";
            body += "<tr><td>TOTAL</td><td>"+total+"</td></tr></table><br>";
            body += "<p>HHA will contact you to confirm this order</p>";
            body += "<p>Thank you!</p>";

            email.sendHtmlMessage(order.getEmail(), "Invoice HHA", body);

        //make html code
        }   catch(MessagingException me) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, me.getMessage());
        }
        //need to check more exception handling

        return "Success";
    }

    /**
     * Check cart list.
     *
     * @param id the id
     * @return the list
     */
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

    /**
     * Gets orders.
     *
     * @param status       the status
     * @param orderDate    the order date
     * @param paidDate     the paid date
     * @param category     the category
     * @param product      the product
     * @param phone        the phone
     * @param email        the email
     * @param customerName the customer name
     * @param sort         the sort
     * @param page         the page
     * @param pageSize     the page size
     * @param all          the all
     * @return the orders
     */
    public Page<Order> getOrders(
            String status, String orderDate, String paidDate, String category, String product,
            String phone, String email, String customerName, String sort, String page, String pageSize,String all) {

        String sortProp = "orders_id";
        String order = "desc";
        int pageNumber = 1;
        int pageS=10;

        if ( page != null ) pageNumber = Integer.parseInt(page);
        if( pageSize != null) pageS = Integer.parseInt(pageSize);
        if( all != null) pageS = 9999;

        if ( sort != null) {
            sortProp= sort.split(":")[0];
            order = sort.split(":")[1];
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, pageS, Sort.Direction.fromString(order), sortProp);

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
            minOrderDate = sdf.parse("2020-01-01");
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

    /**
     * Edit order order.
     *
     * @param order the order
     * @return the order
     */
    public Order editOrder(Order order) {

        try {
            orderRepo.save(order);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return order;
    }

    /**
     * Find by id order.
     *
     * @param id the id
     * @return the order
     */
    public Order findById(int id) {
        return orderRepo.findById(id);
    }

    /**
     * Remove order list.
     *
     * @param order the order
     * @return the list
     */
    public List<Order> removeOrder(Order order) {

        orderRepo.delete(order);

        return orderRepo.findAll();
    }

    /**
     * Gets order item.
     *
     * @param id the id
     * @return the order item
     */
    public OrderInfoDto getOrderItem(int id) {
        List<OrderItemDto> list = orderInfoRepo.listAllItem(id);
        Order order = orderRepo.findById(id);

        OrderInfoDto orderInfo = new OrderInfoDto(order.getEmail(), order.getPhone(), order.getOrderName(), order.getId(), order.getOrderDate(), order.getPaidDate(), order.getStatus());
        orderInfo.setItemList(list);

        return orderInfo;
    }

}
