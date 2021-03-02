package hha.spring.data.dataapi.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private int id;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "paid_date")
    private Date paidDate;
    @Column(name = "price_sum")
    private double sum;
    @Column(name = "status")
    private String status;
    @Column(name = "order_email")
    private String email;
    @Column(name = "order_phone")
    private String phone;
    @Column(name = "order_name")
    private String name;

    public Order(Date orderDate, Date paidDate, double sum, String status, String email, String phone, String name) {
        this.orderDate = orderDate;
        this.paidDate = paidDate;
        this.sum = sum;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


