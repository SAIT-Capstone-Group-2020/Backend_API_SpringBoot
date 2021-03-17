package hha.spring.data.dataapi.domain.order;

import hha.spring.data.dataapi.domain.Item;

import javax.persistence.*;
import java.util.List;
@Entity
@SqlResultSetMapping(
        name = "orderDataMapping",
        classes = @ConstructorResult(
                targetClass = OrderItemDto.class,
                columns = {
                        @ColumnResult(name = "order_items_id"),
                        @ColumnResult(name = "product_id"),
                        @ColumnResult(name = "product_name"),
                        @ColumnResult(name = "image_url"),
                        @ColumnResult(name = "brand_name"),
                        @ColumnResult(name = "category_name"),
                        @ColumnResult(name = "quantity"),
                        @ColumnResult(name = "weight_value"),
                        @ColumnResult(name = "weight_type_name"),
                        @ColumnResult(name = "total"),
                        @ColumnResult(name = "gst"),
                        @ColumnResult(name = "order_sum")

                }
        )
)
@NamedNativeQuery(name = "orderItemDataMapping", resultClass = OrderItemDto.class,
        query = "SELECT oi.order_items_id AS order_items_id, " +
                "oi.product_id AS product_id, " +
                "p.product_name AS product_name, " +
                "p.image_url AS image_url, " +
                "p.brand_name AS brand_name, " +
                "c.category_name AS category_name, " +
                "oi.qty AS quantity, " +
                "(p.weight_value*oi.qty) AS weight_value, " +
                "wt.weight_type_name AS weight_type_name, " +
                "oi.total_price AS total, " +
                "oi.gst_sum AS gst, " +
                "oi.sum AS order_sum " +
                "FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) " +
                "JOIN product AS p on(p.product_id = oi.product_id) " +
                "JOIN weight_type AS wt on(wt.weight_type_id = p.weight_type_id) " +
                "JOIN category AS c on(c.category_id = p.category_id) " +
                "WHERE o.orders_id = ?1"
)
public class OrderItemDto {

    @Id
    private int order_items_id;
    private int product_id;
    private String product_name;
    private String image_url;
    private String brand_name;
    private String category_name;

    private int quantity;
    private double weight_value;
    private String weight_type_name;

    private double total;
    private double gst;
    private double order_sum;

    public OrderItemDto() {

    }

    public int getOrder_items_id() {
        return order_items_id;
    }

    public void setOrder_items_id(int order_items_id) {
        this.order_items_id = order_items_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight_value() {
        return weight_value;
    }

    public void setWeight_value(double weight_value) {
        this.weight_value = weight_value;
    }

    public String getWeight_type_name() {
        return weight_type_name;
    }

    public void setWeight_type_name(String weight_type_name) {
        this.weight_type_name = weight_type_name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getSum() {
        return order_sum;
    }

    public void setSum(double sum) {
        this.order_sum = sum;
    }
}


