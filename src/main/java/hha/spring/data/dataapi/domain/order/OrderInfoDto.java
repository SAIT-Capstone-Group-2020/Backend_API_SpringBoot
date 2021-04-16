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

    /**
     * Instantiates a new Order info dto.
     */
    public OrderInfoDto() {
    }

    /**
     * Instantiates a new Order info dto.
     *
     * @param email    the email
     * @param phone    the phone
     * @param name     the name
     * @param order_id the order id
     * @param odate    the odate
     * @param pdate    the pdate
     * @param status   the status
     */
    public OrderInfoDto(String email, String phone, String name, int order_id, Date odate, Date pdate, String status) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.order_id = order_id;
        this.orderDate = odate;
        this.paidDate = pdate;
        this.status = status;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrder_id() {
        return order_id;
    }

    /**
     * Gets item list.
     *
     * @return the item list
     */
    public List<OrderItemDto> getItemList() {
        return itemList;
    }

    /**
     * Sets item list.
     *
     * @param itemList the item list
     */
    public void setItemList(List<OrderItemDto> itemList) {
        this.itemList = itemList;
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
     * Sets order date.
     *
     * @param orderDate the order date
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
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
     * Sets paid date.
     *
     * @param paidDate the paid date
     */
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }
}


