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

    public OrderDto(List<OrderItem> orderItem, String email, String confirmEmail, String phone, String name) {
        this.orderItem = orderItem;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.phone = phone;
        this.name = name;
    }


    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }
}
