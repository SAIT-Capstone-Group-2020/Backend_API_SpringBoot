package hha.spring.data.dataapi.domain;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
