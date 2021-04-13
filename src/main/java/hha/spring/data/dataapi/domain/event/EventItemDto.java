package hha.spring.data.dataapi.domain.event;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 *
 * retrieve product list related to the event
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@SqlResultSetMapping(
        name = "eventDataMapping",
        classes = @ConstructorResult(
                targetClass = EventItemDto.class,
                columns = {
                        @ColumnResult(name = "discount_id"),
                        @ColumnResult(name = "discount_price"),
                        @ColumnResult(name = "product_id"),
                        @ColumnResult(name = "product_name"),
                        @ColumnResult(name = "original_price"),
                        @ColumnResult(name = "image_url"),
                        @ColumnResult(name = "category_name"),
                        @ColumnResult(name = "brand_name")
                }
        )
)
@NamedNativeQuery(name = "eventItemDataMapping", resultClass = EventItemDto.class,
        query = "SELECT d.discount_id AS discount_id, " +
                "d.discount_price AS discount_price, " +
                "p.product_id AS product_id, " +
                "p.product_name AS product_name, " +
                "p.retail_price AS original_price, " +
                "p.image_url AS image_url, " +
                "c.category_name AS category_name, " +
                "p.brand_name AS brand_name " +
                "FROM event AS e JOIN discount AS d on(e.event_id = d.event_id) " +
                "JOIN product AS p on(p.product_id = d.product_id) " +
                "JOIN category AS c on(c.category_id = p.category_id) " +
                "WHERE e.event_id = ?1"
)
public class EventItemDto {

    @Id
    private int discount_id;
    private double discount_price;
    private int product_id;
    private String product_name;
    private double original_price;
    private String image_url;
    private String category_name;
    private String brand_name;

    public EventItemDto() {
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getBrand_name() {
        return brand_name;
    }
}
