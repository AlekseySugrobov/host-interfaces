package ru.host.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="users")
public class DetailUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, length = 50)
    private String username;

    @Column
    private String password;

    @Column
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable
            (
                    name="user_role",
                    joinColumns= @JoinColumn(name="username"),inverseJoinColumns = @JoinColumn(name="role")
            )
    private Set<Role> roleList = new HashSet<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return enabled;
    }

    public boolean isAccountNonLocked() {
        return enabled;
    }

    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }

    public String rolesInString() {
        if (roleList == null) {
            return "";
        }

        return roleList.stream().map(Role::getRole).collect(Collectors.joining(", "));
    }
}
