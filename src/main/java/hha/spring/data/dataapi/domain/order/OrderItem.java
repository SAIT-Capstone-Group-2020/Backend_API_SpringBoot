package hha.spring.data.dataapi.domain.order;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 *
 * order_items table
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id")
    private int id;
    @Column(name = "total_price")
    private double total;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "qty")
    private int quantity;
    @Column(name = "orders_id")
    private int ordersId;

    public OrderItem() {

    }

    public OrderItem(double total, int productId, int quantity, int ordersId) {
        this.total = total;
        this.productId = productId;
        this.quantity = quantity;
        this.ordersId = ordersId;
    }

    public OrderItem(double total, int proudctId) {
        this.total=total;
        this.productId=proudctId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrdersId() {
        return ordersId;
    }
}
