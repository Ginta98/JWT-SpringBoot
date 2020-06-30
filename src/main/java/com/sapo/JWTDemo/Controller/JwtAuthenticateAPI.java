package com.sapo.JWTDemo.Controller;

import com.sapo.JWTDemo.Security.JsonWebToken.JwtAuthenicationRequest;
import com.sapo.JWTDemo.Security.JsonWebToken.JwtAuthenicationResponse;
import com.sapo.JWTDemo.Security.JsonWebToken.JwtUserDetailsService;
import com.sapo.JWTDemo.Security.JsonWebToken.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtAuthenticateAPI {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenicationToken(@RequestBody JwtAuthenicationRequest jwtAuthenicationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtAuthenicationRequest.getUsername()
                            , jwtAuthenicationRequest.getPassword())
            );
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(jwtAuthenicationRequest.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtAuthenicationResponse(jwt,userDetails));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<String>("Wrong username or password", HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

}
