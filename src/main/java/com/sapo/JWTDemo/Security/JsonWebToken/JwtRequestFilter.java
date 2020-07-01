package com.sapo.JWTDemo.Security.JsonWebToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);


        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // get authority from token, then add into new UserDetail
            String grantListGetFromClaims = jwtUtil.extractRoles(jwt);
            List<GrantedAuthority> authorities = new ArrayList<>();
            // regex
            List<String> dataRoleForeach = new ArrayList<>();
            if (grantListGetFromClaims.equals("[]")) {
                System.out.println("No role");
            } else {
                for (String role :
                        grantListGetFromClaims.split(", ")) {
                    dataRoleForeach.add(role);
                }
                for (int i = 0; i < dataRoleForeach.size(); i++) {
                    if (i == 0) {
                        String roleTemp = dataRoleForeach.get(i).substring(12);
                        String roleTempFinal = roleTemp.substring(0, roleTemp.length() - 1);
                        GrantedAuthority authority = new SimpleGrantedAuthority(roleTempFinal);
                        authorities.add(authority);
                    } else if (i == dataRoleForeach.size() - 1) {
                        String roleTemp = dataRoleForeach.get(i).substring(11);
                        String roleTempFinal = roleTemp.substring(0, roleTemp.length() - 2);
                        GrantedAuthority authority = new SimpleGrantedAuthority(roleTempFinal);
                        authorities.add(authority);
                    } else {
                        String roleTemp = dataRoleForeach.get(i).substring(11);
                        String roleTempFinal = roleTemp.substring(0, roleTemp.length() - 1);
                        GrantedAuthority authority = new SimpleGrantedAuthority(roleTempFinal);
                        authorities.add(authority);
                    }
                }
            }
            // User has username, and authorities
            UserDetails userDetails = new User(username, "secret", authorities);
            if (jwtUtil.validateToken(jwt)) {
                System.out.println("found User:" + userDetails.toString());
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
