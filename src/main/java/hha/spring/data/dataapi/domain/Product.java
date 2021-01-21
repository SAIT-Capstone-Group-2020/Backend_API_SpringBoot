package hha.spring.data.dataapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private float price;
	@Column(name = "active")
	private boolean active;
	@Column(name = "image_url")
	private String image;
	@Column(name = "category")
	private int category;
	@Column(name = "inventory")
	private int inventory;
	@Column(name = "last_updated_by")
	private String last_updated;
	@Column(name = "expired_date")
	private Date expired_date;

	public Product() {
	}

	public Product(String name, String description, float price, boolean active, String image, int category, int inventory, String last_updated_by, Date expired_date) {
		this.name = name;
		this.description = description;
		this.active = active;
		this.price = price;
		this.image = image;
		this.category = category;
		this.inventory = inventory;
		this.last_updated = last_updated_by;
		this.expired_date = expired_date;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}

	public Date getExpired_date() {
		return expired_date;
	}

	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}
}
