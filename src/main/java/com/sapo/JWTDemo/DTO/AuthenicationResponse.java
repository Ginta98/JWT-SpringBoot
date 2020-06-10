package com.sapo.JWTDemo.DTO;

public class AuthenicationResponse {
    private final String jwt;

    public String getJwt() {
        return jwt;
    }

    public AuthenicationResponse(String jwt) {
        this.jwt = jwt;
    }
}
