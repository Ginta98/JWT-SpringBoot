package com.sapo.JWTDemo.Security.JsonWebToken;

import com.sapo.JWTDemo.DTO.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthenicationResponse {
    private final String jwt;
    private String username;
    private Collection<GrantedAuthority> authorities;

    public JwtAuthenicationResponse(String jwt, UserDetails userDetails) {
        this.jwt = jwt;
        this.username = userDetails.getUsername();
        authorities = (Collection<GrantedAuthority>) userDetails.getAuthorities();

    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
