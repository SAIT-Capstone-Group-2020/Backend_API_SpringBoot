package hha.spring.data.dataapi.domain;

import javax.persistence.Entity;

/*
*THIS CLASS IS TO DISPLAY ITEM INFORMATION ON CUSTOMER SIDE
 */

@Entity
public class Item {

    private int id;
    private String name;
    private double sales_price;
    //private booelan active;
    private String img_url;
    private String category;
    private int quantity;
    private double weightValue;
    private String weightType;
    private String brandName;

    public Item(int id, String name, double sales_price, String img_url, String category, int quantity, double weightValue, String weightType, String brandName) {
        this.id = id;
        this.name = name;
        this.sales_price = sales_price;
        this.img_url = img_url;
        this.category = category;
        this.quantity = quantity;
        this.weightValue = weightValue;
        this.weightType = weightType;
        this.brandName = brandName;
    }
}
