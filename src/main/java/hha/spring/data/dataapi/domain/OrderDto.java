package hha.spring.data.dataapi.domain;

import java.util.ArrayList;
import java.util.List;

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
