package hha.spring.data.dataapi.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 * <p>
 * role_info table
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@Table(name="role_info")
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int id;

    @Column(name="role")
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
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
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }


}
