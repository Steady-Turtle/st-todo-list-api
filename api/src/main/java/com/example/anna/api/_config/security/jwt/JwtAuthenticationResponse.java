package com.example.anna.api._config.security.jwt;

import com.example.anna.entity.user.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class JwtAuthenticationResponse {

    private final String tokenType = "Bearer";

    private String accessToken;

    private String userId;

    private Set<User.Role> roles;


    public JwtAuthenticationResponse(String accessToken,
                                     String userId,
                                     Set<User.Role> roles) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.roles = roles;
    }

    private Set<String> getRoleSet(Collection<? extends GrantedAuthority> roles) {
        return roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }
}
