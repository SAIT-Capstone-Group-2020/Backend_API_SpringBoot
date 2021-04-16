package hha.spring.data.dataapi.domain.order;

import java.util.List;

/**
 * This class is a data object that works as response entity
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
public class OrderDto {

    private List<OrderItem> orderItem;
    private String email;
    private String confirmEmail;
    private String phone;
    private String name;

    /**
     * Instantiates a new Order dto.
     *
     * @param orderItem    the order item
     * @param email        the email
     * @param confirmEmail the confirm email
     * @param phone        the phone
     * @param name         the name
     */
    public OrderDto(List<OrderItem> orderItem, String email, String confirmEmail, String phone, String name) {
        this.orderItem = orderItem;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.phone = phone;
        this.name = name;
    }


    /**
     * Gets order item.
     *
     * @return the order item
     */
    public List<OrderItem> getOrderItem() {
        return orderItem;
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
     * Gets confirm email.
     *
     * @return the confirm email
     */
    public String getConfirmEmail() {
        return confirmEmail;
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
}
