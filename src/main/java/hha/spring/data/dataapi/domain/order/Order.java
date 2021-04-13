package hha.spring.data.dataapi.domain.order;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 *
 * orders table
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private int id;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "paid_date")
    @Temporal(TemporalType.DATE)
    private Date paidDate;
    @Column(name = "price_sum")
    private double priceSum;
    @Column(name = "status")
    private String status;
    @Column(name = "order_email")
    private String email;
    @Column(name = "order_phone")
    private String phone;
    @Column(name = "order_name")
    private String orderName;

    public Order(Date orderDate, Date paidDate, double sum, String status, String email, String phone, String name) {
        this.orderDate = orderDate;
        this.paidDate = paidDate;
        this.priceSum = sum;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.orderName = name;
    }

    public Order(){
    }

    public int getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(double priceSum) {
        this.priceSum = priceSum;
    }

    public String getOrderName() {
        return orderName;
    }
}


