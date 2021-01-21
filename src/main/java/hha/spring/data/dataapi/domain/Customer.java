package hha.spring.data.dataapi.domain;

import javax.persistence.*;

import java.util.Date;


@Entity
@Table(name = "customer")
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "email")
	private String email;
	@Column(name = "last_login")
	private Date last_login;
	@Column(name = "active")
	private boolean active;
	@Column(name = "uuid")
	private String uuid;

	public Customer() {
	}

	public Customer(int user_id, String username, String password, String email) {
		this.user_id = user_id;
		this.email = email;
		this.last_login = new Date();
		this.active = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
