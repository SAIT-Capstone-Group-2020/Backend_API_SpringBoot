package hha.spring.data.dataapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "brand_name")
	private String brand;
	@Column(name = "retail_price")
	private float price;
	@Column(name = "active")
	private boolean active;
	@Column(name = "image_url")
	private String image;
	@Column(name = "category")
	private int category;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "weight_value")
	private String weightValue;
	@Column(name = "weight_type")
	private int weightType;

	public Product() {
	}

	public Product(String name, String description, String brand, float price, boolean active, String image, int category, int quantity, String weightValue, int weightType) {
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
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

	public String getWeightValue() {
		return weightValue;
	}

	public void setWeightValue(String weightValue) {
		this.weightValue = weightValue;
	}

	public int getWeightType() {
		return weightType;
	}

	public void setWeightType(int weightType) {
		this.weightType = weightType;
	}
}
