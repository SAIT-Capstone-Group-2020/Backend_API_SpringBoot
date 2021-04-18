package hha.spring.data.dataapi.domain;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 * <p>
 * product table
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
	private int id;
	@Column(name = "product_name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "brand_name")
	private String brand;
	@Column(name = "retail_price")
	private double price;
	@Column(name = "active")
	private boolean active;
	@Column(name = "image_url")
	private String image;
	@Column(name = "category_id")
	private int category;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "weight_value")
	private double weightValue;
	@Column(name = "weight_type_id")
	private int weightType;

    /**
     * Instantiates a new Product.
     */
    public Product() {
	}

    /**
     * Instantiates a new Product.
     *
     * @param name        the name
     * @param description the description
     * @param brand       the brand
     * @param price       the price
     * @param category    the category
     * @param quantity    the quantity
     * @param weightValue the weight value
     * @param weightType  the weight type
     */
    public Product(String name, String description, String brand, double price, int category, int quantity, double weightValue, int weightType) {
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
		this.weightValue = weightValue;
		this.weightType = weightType;
	}


    /**
     * Instantiates a new Product.
     *
     * @param name        the name
     * @param description the description
     * @param brand       the brand
     * @param price       the price
     * @param active      the active
     * @param image       the image
     * @param category    the category
     * @param quantity    the quantity
     * @param weightValue the weight value
     * @param weightType  the weight type
     */
    public Product(String name, String description, String brand, double price, boolean active, String image, int category, int quantity, double weightValue, int weightType) {
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.active = active;
		this.image = image;
		this.category = category;
		this.quantity = quantity;
		this.weightValue = weightValue;
		this.weightType = weightType;
	}

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
		return id;
	}

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
		this.id = id;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
		return description;
	}

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Gets brand.
     *
     * @return the brand
     */
    public String getBrand() {
		return brand;
	}

    /**
     * Sets brand.
     *
     * @param brand the brand
     */
    public void setBrand(String brand) {
		this.brand = brand;
	}

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
		return price;
	}

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
		this.price = price;
	}

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
		return active;
	}

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
		this.active = active;
	}

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
		return image;
	}

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
		this.image = image;
	}

    /**
     * Gets category.
     *
     * @return the category
     */
    public int getCategory() {
		return category;
	}

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(int category) {
		this.category = category;
	}

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
		return quantity;
	}

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    /**
     * Gets weight value.
     *
     * @return the weight value
     */
    public double getWeightValue() {
		return weightValue;
	}

    /**
     * Sets weight value.
     *
     * @param weightValue the weight value
     */
    public void setWeightValue(double weightValue) {
		this.weightValue = weightValue;
	}

    /**
     * Gets weight type.
     *
     * @return the weight type
     */
    public int getWeightType() {
		return weightType;
	}

    /**
     * Sets weight type.
     *
     * @param weightType the weight type
     */
    public void setWeightType(int weightType) {
		this.weightType = weightType;
	}
}
