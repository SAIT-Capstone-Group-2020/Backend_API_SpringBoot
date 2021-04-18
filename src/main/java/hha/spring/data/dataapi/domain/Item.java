package hha.spring.data.dataapi.domain;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 * <p>
 * retrieve product list with the detail of discount
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@SqlResultSetMapping(
        name = "itemDataMapping",
        classes = @ConstructorResult(
                targetClass = Item.class,
                columns = {
                        @ColumnResult(name = "product_id"),
                        @ColumnResult(name = "product_name"),
                        @ColumnResult(name = "description"),
                        @ColumnResult(name = "original_price"),
                        @ColumnResult(name = "is_dicount"),
                        @ColumnResult(name = "discount_price"),
                        @ColumnResult(name = "image_url"),
                        @ColumnResult(name = "category_name"),
                        @ColumnResult(name = "quantity"),
                        @ColumnResult(name = "weight_value"),
                        @ColumnResult(name = "weight_type_name"),
                        @ColumnResult(name = "brand_name"),
                }
        )
)

@NamedNativeQuery(name = "singleItemDataMapping", resultClass = Item.class,
        query = "SELECT * FROM "
                +"(SELECT p.product_id AS product_id, p.product_name AS product_name, p.description AS description, p.retail_price as original_price, TRUE AS is_discount, d.discount_price AS discount_price, p.image_url AS image_url, c.category_name AS category_name, p.quantity AS quantity, p.weight_value AS weight_value, wt.weight_type_name AS weight_type_name, p.brand_name AS brand_name "
                +"FROM product AS p JOIN weight_type AS wt on(wt.weight_type_id = p.weight_type_id) JOIN category AS c on(c.category_id = p.category_id) "
                +"JOIN discount AS d on(d.product_id = p.product_id) JOIN event AS e on(e.event_id = d.event_id) "
                +"WHERE "
                +"(p.product_id LIKE ?1) "
                +"AND CURRENT_DATE >= e.start_date and CURRENT_DATE < e.end_date + INTERVAL 1 DAY "
                +"AND p.active = 1 "

                +"UNION ALL "
                +"SELECT pp.product_id AS product_id, pp.product_name AS product_name, pp.description AS description, pp.retail_price AS original_price, FALSE AS is_discount, pp.retail_price AS discount_price, pp.image_url AS image_url, cc.category_name AS category_name, pp.quantity AS quantity, pp.weight_value AS weight_value, wtt.weight_type_name AS weight_type_name, pp.brand_name AS brand_name "
                +"FROM product AS pp JOIN weight_type AS wtt on(wtt.weight_type_id = pp.weight_type_id) JOIN category AS cc on(cc.category_id = pp.category_id) "
                +"WHERE pp.product_id NOT IN (SELECT dd.product_id FROM discount AS dd JOIN event AS ee on(ee.event_id = dd.event_id) "
                +"WHERE (CURRENT_DATE >= ee.start_date AND CURRENT_DATE < ee.end_date + INTERVAL 1 DAY)) "
                +"AND (pp.product_id LIKE ?1) "
                +"AND pp.active = 1) AS s1"
)

@NamedNativeQuery(name = "allItemDataMapping", resultClass = Item.class,
        query = "SELECT * FROM "
                +"(SELECT p.product_id AS product_id, p.product_name AS product_name, p.description AS description, p.retail_price as original_price, TRUE AS is_discount, d.discount_price AS discount_price, p.image_url AS image_url, c.category_name AS category_name, p.quantity AS quantity, p.weight_value AS weight_value, wt.weight_type_name AS weight_type_name, p.brand_name AS brand_name "
                +"FROM product AS p JOIN weight_type AS wt on(wt.weight_type_id = p.weight_type_id) JOIN category AS c on(c.category_id = p.category_id) "
                +"JOIN discount AS d on(d.product_id = p.product_id) JOIN event AS e on(e.event_id = d.event_id) "
                +"WHERE "
                +"CURRENT_DATE >= e.start_date and CURRENT_DATE < e.end_date + INTERVAL 1 DAY "
                +"AND p.active = 1 "

                +"UNION ALL "
                +"SELECT pp.product_id AS product_id, pp.product_name AS product_name, pp.description AS description, pp.retail_price AS original_price, FALSE AS is_discount, pp.retail_price AS discount_price, pp.image_url AS image_url, cc.category_name AS category_name, pp.quantity AS quantity, pp.weight_value AS weight_value, wtt.weight_type_name AS weight_type_name, pp.brand_name AS brand_name "
                +"FROM product AS pp JOIN weight_type AS wtt on(wtt.weight_type_id = pp.weight_type_id) JOIN category AS cc on(cc.category_id = pp.category_id) "
                +"WHERE pp.product_id NOT IN (SELECT dd.product_id FROM discount AS dd JOIN event AS ee on(ee.event_id = dd.event_id) "
                +"WHERE (CURRENT_DATE >= ee.start_date AND CURRENT_DATE < ee.end_date + INTERVAL 1 DAY)) "
                +"AND pp.active = 1) AS s1"
)
public class Item {
    @Id
    private int product_id;
    private String product_name;
    private String description;
    private double original_price;
    private boolean is_discount;
    private double discount_price;
    private String image_url;
    private String category_name;
    private int quantity;
    private double weight_value;
    private String weight_type_name;
    private String brand_name;

    /**
     * Instantiates a new Item.
     *
     * @param product_id       the product id
     * @param product_name     the product name
     * @param description      the description
     * @param original_price   the original price
     * @param is_discount      the is discount
     * @param discount_price   the discount price
     * @param image_url        the image url
     * @param category_name    the category name
     * @param quantity         the quantity
     * @param weight_value     the weight value
     * @param weight_type_name the weight type name
     * @param brand_name       the brand name
     */
    public Item(int product_id, String product_name, String description, double original_price, boolean is_discount, double discount_price, String image_url, String category_name, int quantity, double weight_value, String weight_type_name, String brand_name) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.original_price = original_price;
        this.is_discount = is_discount;
        this.discount_price = discount_price;
        this.image_url = image_url;
        this.category_name = category_name;
        this.quantity = quantity;
        this.weight_value = weight_value;
        this.weight_type_name = weight_type_name;
        this.brand_name = brand_name;
    }

    /**
     * Instantiates a new Item.
     */
    public Item(){
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProduct_id() {
        return product_id;
    }

    /**
     * Gets product name.
     *
     * @return the product name
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets original price.
     *
     * @return the original price
     */
    public double getOriginal_price() {
        return original_price;
    }

    /**
     * Gets is discount.
     *
     * @return the is discount
     */
    public boolean getIs_discount() {
        return is_discount;
    }

    /**
     * Gets discount price.
     *
     * @return the discount price
     */
    public double getDiscount_price() {
        return discount_price;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImage_url() {
        return image_url;
    }

    /**
     * Gets category name.
     *
     * @return the category name
     */
    public String getCategory_name() {
        return category_name;
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
     * Gets weight value.
     *
     * @return the weight value
     */
    public double getWeight_value() {
        return weight_value;
    }

    /**
     * Gets weight type name.
     *
     * @return the weight type name
     */
    public String getWeight_type_name() {
        return weight_type_name;
    }

    /**
     * Gets brand name.
     *
     * @return the brand name
     */
    public String getBrand_name() {
        return brand_name;
    }
}
