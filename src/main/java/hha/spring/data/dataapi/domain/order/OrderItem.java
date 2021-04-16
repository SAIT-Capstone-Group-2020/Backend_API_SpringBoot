package hha.spring.data.dataapi.domain.order;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 * <p>
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

    /**
     * Instantiates a new Order item.
     */
    public OrderItem() {

    }

    /**
     * Instantiates a new Order item.
     *
     * @param total     the total
     * @param productId the product id
     * @param quantity  the quantity
     * @param ordersId  the orders id
     */
    public OrderItem(double total, int productId, int quantity, int ordersId) {
        this.total = total;
        this.productId = productId;
        this.quantity = quantity;
        this.ordersId = ordersId;
    }

    /**
     * Instantiates a new Order item.
     *
     * @param total     the total
     * @param proudctId the proudct id
     */
    public OrderItem(double total, int proudctId) {
        this.total=total;
        this.productId=proudctId;
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets orders id.
     *
     * @return the orders id
     */
    public int getOrdersId() {
        return ordersId;
    }
}
