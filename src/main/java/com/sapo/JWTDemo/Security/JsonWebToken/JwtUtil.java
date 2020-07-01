package com.sapo.JWTDemo.Security.JsonWebToken;

import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Component
public class JwtUtil {
    private String SECRET_KEY = "thangdeptraithatsu";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractRoles(String token){
        return extractAllClaims(token).get("roles").toString();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
//       ArrayList<GrantedAuthority> roles = (ArrayList<GrantedAuthority>) Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("roles");
//        System.out.println("claims:"+roles.get(0));
//        System.out.println("claims:"+roles.get(1));
//        System.out.println("claims:"+roles.get(2));
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token){
        return  extractExpiration(token).before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles",userDetails.getAuthorities());
        return createToken(claims,userDetails.getUsername());
    }
    public String createToken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*12))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();



    }
    public Boolean validateToken(String token){
        return (!isTokenExpired(token));
    }
}
