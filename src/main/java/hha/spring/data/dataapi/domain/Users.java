package hha.spring.data.dataapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 *
 * retrieve product list with the detail of discount
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@Table(name="user")
public class Users {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    //1 true / 0 false
    @Column(name = "active")
    private int active;

    @Column(name = "user_name")
    private String name;

    @Column(name="uuid")
    @JsonIgnore
    private String uuid;

    public Users(String email, String password, String name, String uuid, Role role) {
        this.email = email;
        this.password = password;
        this.active = 0;
        this.name = name;
        this.uuid = uuid;
        this.roles = Arrays.asList(role);
    }

    protected Users() {
    }

    //fetch user_roles table when users row inserted or updated
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "email")},
    inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "role_id")})
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
