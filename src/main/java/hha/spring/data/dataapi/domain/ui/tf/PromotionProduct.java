package hha.spring.data.dataapi.domain.ui.tf;


public class PromotionProduct {
    private Long productId;
    private String productName;
    private Double retailPrice;
    private Double discountPrice;
    private String productImageUrl;

    @Override
    public String toString() {
        return "PromotionProduct{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", retailPrice=" + retailPrice +
                ", discountPrice=" + discountPrice +
                ", productImageUrl='" + productImageUrl + '\'' +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public PromotionProduct() {
    }

    public PromotionProduct(Long productId, String productName, Double retailPrice, Double discountPrice, String productImageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.retailPrice = retailPrice;
        this.discountPrice = discountPrice;
        this.productImageUrl = productImageUrl;
    }
}
