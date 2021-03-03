package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Order;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.TemporalType;

import java.util.Date;
import java.util.List;

import static org.hibernate.type.descriptor.java.JdbcDateTypeDescriptor.DATE_FORMAT;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value= "SELECT o.orders_id AS orders_id, o.order_date AS order_date, o.paid_date AS paid_date, " +
            "o.price_sum AS price_sum, o.status AS status, o.order_email AS order_email, " +
            "o.order_phone AS order_phone, o.order_name AS order_name "
            +"FROM orders AS o JOIN order_items AS oi ON(o.orders_id = oi.orders_id) " +
            "JOIN product AS p ON(oi.product_id = p.product_id) " +
            "JOIN category AS c ON(p.category_id = c.category_id) " +
            "WHERE " +
            "(o.status LIKE CONCAT('%',?1,'%')) " +
            "AND (o.order_date >= ?2 AND o.order_date < ?3 + INTERVAL 1 DAY) " +
            "AND (o.paid_date >= ?4 AND o.paid_date < ?5 + INTERVAL 1 DAY) " +
            "AND (c.category_name LIKE LOWER(CONCAT('%',?6,'%'))) " +
            "AND (p.product_name LIKE LOWER(CONCAT('%',?7,'%'))) " +
            "AND (o.order_phone LIKE LOWER(CONCAT('%',?8,'%')))" +
            "AND (o.order_email LIKE LOWER(CONCAT('%',?9,'%')))" +
            "AND (o.order_name LIKE LOWER(CONCAT('%',?10,'%')))" +
            "GROUP BY o.orders_id"
            ,countQuery =
            "SELECT o.orders_id AS orders_id "
                    +"FROM orders AS o JOIN order_items AS oi ON(o.orders_id = oi.orders_id) " +
                    "JOIN product AS p ON(oi.product_id = p.product_id) " +
                    "JOIN category AS c ON(p.category_id = c.category_id) " +
                    "WHERE " +
                    "(o.status LIKE CONCAT('%',?1,'%')) " +
                    "AND (o.order_date >= ?2 AND o.order_date < ?3 + INTERVAL 1 DAY) " +
                    "AND (o.paid_date >= ?4 AND o.paid_date < ?5 + INTERVAL 1 DAY) " +
                    "AND (c.category_name LIKE LOWER(CONCAT('%',?6,'%'))) " +
                    "AND (p.product_name LIKE LOWER(CONCAT('%',?7,'%'))) " +
                    "AND (o.order_phone LIKE LOWER(CONCAT('%',?8,'%')))" +
                    "AND (o.order_email LIKE LOWER(CONCAT('%',?9,'%')))" +
                    "AND (o.order_name LIKE LOWER(CONCAT('%',?10,'%')))" +
                    "GROUP BY o.orders_id"
            , nativeQuery = true)
    Page<Order> getOrder(String status, Date minOrderDate, Date maxOrderDate,
                         Date minPaidDate, Date maxPaidDate, String category, String product,
                         String phone, String email, String name, Pageable pageable);


    Order findById(int id);

}
