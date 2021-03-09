package hha.spring.data.dataapi.domain.order;

import java.util.List;

public class OrderInfoDto {

    private String email;
    private String phone;
    private String name;
    private int order_id;
    private List<OrderItemDto> itemList;

    public OrderInfoDto() {
    }

    public OrderInfoDto(String email, String phone, String name, int order_id) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.order_id = order_id;
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

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public List<OrderItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemDto> itemList) {
        this.itemList = itemList;
    }
}


