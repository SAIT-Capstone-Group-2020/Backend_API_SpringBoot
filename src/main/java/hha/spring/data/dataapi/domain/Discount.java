package hha.spring.data.dataapi.domain;

import javax.persistence.*;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "event_id")
    private int eventId;
    @Column(name = "discount_price")
    private double dicountPrice;
    @Column(name = "limit")
    private int limit;

    public Discount(int productId, int eventId, double dicountPrice, int limit) {
        this.productId = productId;
        this.eventId = eventId;
        this.dicountPrice = dicountPrice;
        this.limit = limit;
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

    public double getDicountPrice() {
        return dicountPrice;
    }

    public void setDicountPrice(double dicountPrice) {
        this.dicountPrice = dicountPrice;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
