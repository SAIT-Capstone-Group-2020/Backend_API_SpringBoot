package hha.spring.data.dataapi.domain.event;

import javax.persistence.*;

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


    public Discount(int productId, double discountPrice) {
        this.productId = productId;
        this.discountPrice = discountPrice;
    }

    public Discount(int productId, int eventId, double discountPrice, int limit) {
        this.productId = productId;
        this.eventId = eventId;
        this.discountPrice = discountPrice;
        this.limit = limit;
    }

    public Discount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double dicountPrice) {
        this.discountPrice = dicountPrice;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
