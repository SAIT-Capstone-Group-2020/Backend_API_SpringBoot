package hha.spring.data.dataapi.domain.event;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 * <p>
 * discount table
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private int id;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "event_id")
    private int eventId;
    @Column(name = "discount_price")
    private double discountPrice;
    @Column(name = "discount_limit")
    private int limit;


    /**
     * Instantiates a new Discount.
     *
     * @param productId     the product id
     * @param discountPrice the discount price
     */
    public Discount(int productId, double discountPrice) {
        this.productId = productId;
        this.discountPrice = discountPrice;
    }

    /**
     * Instantiates a new Discount.
     *
     * @param productId     the product id
     * @param eventId       the event id
     * @param discountPrice the discount price
     * @param limit         the limit
     */
    public Discount(int productId, int eventId, double discountPrice, int limit) {
        this.productId = productId;
        this.eventId = eventId;
        this.discountPrice = discountPrice;
        this.limit = limit;
    }

    /**
     * Instantiates a new Discount.
     */
    public Discount() {
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
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Gets event id.
     *
     * @return the event id
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * Gets discount price.
     *
     * @return the discount price
     */
    public double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

}
