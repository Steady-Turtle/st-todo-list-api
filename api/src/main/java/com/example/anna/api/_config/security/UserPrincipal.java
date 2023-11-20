package com.example.anna.api._config.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.anna.entity.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserPrincipal implements UserDetails {

    private String userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private User.Role role;

    private boolean accountNonLocked;

    private boolean enabled;

    private User user;

    public UserPrincipal(
                         String userId,
                         String password,
                         User.Role role,
                         boolean accountNonLocked,
                         boolean enabled,
                         User user) {
        this.userId = userId;
        this.password = password;
        this.authorities = List.of(role).stream()
                .map(it -> new SimpleGrantedAuthority(it.getCode()))
                .collect(Collectors.toList());
        this.role = role;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.user = user;
    }


    public String getUserId() {
        return userId;
    }

    public User.Role getRole() { return this.role; }

    public User getUser() { return this.user; }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal that = (UserPrincipal) o;

        if (!userId.equals(that.userId)) return false;
        if (!password.equals(that.password)) return false;
        return authorities != null ? authorities.equals(that.authorities) : that.authorities == null;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (authorities != null ? authorities.hashCode() : 0);
        return result;
    }
}
