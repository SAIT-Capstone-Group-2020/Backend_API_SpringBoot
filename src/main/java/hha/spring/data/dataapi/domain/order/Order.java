package hha.spring.data.dataapi.domain.order;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 * <p>
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

    /**
     * Instantiates a new Order.
     *
     * @param orderDate the order date
     * @param paidDate  the paid date
     * @param sum       the sum
     * @param status    the status
     * @param email     the email
     * @param phone     the phone
     * @param name      the name
     */
    public Order(Date orderDate, Date paidDate, double sum, String status, String email, String phone, String name) {
        this.orderDate = orderDate;
        this.paidDate = paidDate;
        this.priceSum = sum;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.orderName = name;
    }

    /**
     * Instantiates a new Order.
     */
    public Order(){
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets order date.
     *
     * @return the order date
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Gets paid date.
     *
     * @return the paid date
     */
    public Date getPaidDate() {
        return paidDate;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets price sum.
     *
     * @return the price sum
     */
    public double getPriceSum() {
        return priceSum;
    }

    /**
     * Sets price sum.
     *
     * @param priceSum the price sum
     */
    public void setPriceSum(double priceSum) {
        this.priceSum = priceSum;
    }

    /**
     * Gets order name.
     *
     * @return the order name
     */
    public String getOrderName() {
        return orderName;
    }
}


