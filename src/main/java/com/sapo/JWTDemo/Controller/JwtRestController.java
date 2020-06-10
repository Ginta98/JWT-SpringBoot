package com.sapo.JWTDemo.Controller;

import com.sapo.JWTDemo.DTO.AuthenicationRequest;
import com.sapo.JWTDemo.DTO.AuthenicationResponse;
import com.sapo.JWTDemo.Service.MyUserDetailsService;
import com.sapo.JWTDemo.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello world";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenicationToken(@RequestBody AuthenicationRequest authenicationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenicationRequest.getUsername()
                            , authenicationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);

        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenicationRequest.getUsername());`
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenicationResponse(jwt));

    }
}
