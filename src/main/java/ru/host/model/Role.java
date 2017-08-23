package ru.host.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="role")
public class Role implements GrantedAuthority, Serializable {

    @Id
    @Column(name="role", length = 50)
    private String role;

    @Transient
    public String getAuthority() {
        return role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        return role.equals(role1.role);

    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
