package hha.spring.data.dataapi.domain;

import java.io.Serializable;

public class CartItem {

    private int productId;
    private double total;
    private double originalPrice;
    private boolean isDicounted;
    private int quantity;
    private String productName;
    private String categoryName;
    private String brandName;
    private double weightValue;
    private String weightTypeName;
    private String url;

    public CartItem(int productId, double total, double originalPrice, boolean isDiscounted, int quantity, String productName, String categoryName, String brandName, double weightValue, String weightTypeName, String url) {
        this.productId = productId;
        this.total = total;
        this.originalPrice = originalPrice;
        this.isDicounted = isDiscounted;
        this.quantity = quantity;
        this.productName = productName;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.weightValue = weightValue;
        this.weightTypeName = weightTypeName;
        this.url = url;
    }

    public CartItem() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(double weightValue) {
        this.weightValue = weightValue;
    }

    public String getWeightTypeName() {
        return weightTypeName;
    }

    public void setWeightTypeName(String weightTypeName) {
        this.weightTypeName = weightTypeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDicounted() {
        return isDicounted;
    }

    public void setDicounted(boolean dicounted) {
        isDicounted = dicounted;
    }
}
