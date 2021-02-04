package hha.spring.data.dataapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;


//need to be updated when make the products endpoints for admin side


@Entity
@Table(name="user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private String uuid;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    public Users(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.roles = Arrays.asList(role);
    }

    protected Users() {
    }

    //fetch user_roles table when users row inserted or updated
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "user_id")},
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
}
