package hha.spring.data.dataapi.domain;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 *
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

	public Product() {
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getWeightValue() {
		return weightValue;
	}

	public void setWeightValue(double weightValue) {
		this.weightValue = weightValue;
	}

	public int getWeightType() {
		return weightType;
	}

	public void setWeightType(int weightType) {
		this.weightType = weightType;
	}
}
