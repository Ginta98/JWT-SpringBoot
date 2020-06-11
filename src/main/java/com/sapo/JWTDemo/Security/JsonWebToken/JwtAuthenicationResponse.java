package com.sapo.JWTDemo.Security.JsonWebToken;

public class JwtAuthenicationResponse {
    private final String jwt;

    public String getJwt() {
        return jwt;
    }

    public JwtAuthenicationResponse(String jwt) {
        this.jwt = jwt;
    }
}
