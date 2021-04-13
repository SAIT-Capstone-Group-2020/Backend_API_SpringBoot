package hha.spring.data.dataapi.domain.order;

import java.util.Date;
import java.util.List;

/**
 * This class is a data object that works as response entity
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
public class OrderInfoDto {

    private String email;
    private String phone;
    private String name;
    private int order_id;
    private Date orderDate;
    private Date paidDate;
    private String status;
    private List<OrderItemDto> itemList;

    public OrderInfoDto() {
    }

    public OrderInfoDto(String email, String phone, String name, int order_id, Date odate, Date pdate, String status) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.order_id = order_id;
        this.orderDate = odate;
        this.paidDate = pdate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder_id() {
        return order_id;
    }

    public List<OrderItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemDto> itemList) {
        this.itemList = itemList;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }
}


