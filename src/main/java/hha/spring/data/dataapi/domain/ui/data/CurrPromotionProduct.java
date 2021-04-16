package hha.spring.data.dataapi.domain.ui.data;


/**
 * The type Curr promotion product.
 */
public class CurrPromotionProduct {
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

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Gets product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets product name.
     *
     * @param productName the product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets retail price.
     *
     * @return the retail price
     */
    public Double getRetailPrice() {
        return retailPrice;
    }

    /**
     * Sets retail price.
     *
     * @param retailPrice the retail price
     */
    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * Gets discount price.
     *
     * @return the discount price
     */
    public Double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * Sets discount price.
     *
     * @param discountPrice the discount price
     */
    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * Gets product image url.
     *
     * @return the product image url
     */
    public String getProductImageUrl() {
        return productImageUrl;
    }

    /**
     * Sets product image url.
     *
     * @param productImageUrl the product image url
     */
    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    /**
     * Instantiates a new Curr promotion product.
     */
    public CurrPromotionProduct() {
    }

    /**
     * Instantiates a new Curr promotion product.
     *
     * @param productId       the product id
     * @param productName     the product name
     * @param retailPrice     the retail price
     * @param discountPrice   the discount price
     * @param productImageUrl the product image url
     */
    public CurrPromotionProduct(Long productId, String productName, Double retailPrice, Double discountPrice, String productImageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.retailPrice = retailPrice;
        this.discountPrice = discountPrice;
        this.productImageUrl = productImageUrl;
    }
}
