package com.sapo.JWTDemo.Controller;

import com.sapo.JWTDemo.DAO.AccountDAO;
import com.sapo.JWTDemo.Entities.Account;
import com.sapo.JWTDemo.Security.JsonWebToken.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AccountAPI {
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/account_regist")
    public ResponseEntity<Integer> register(@RequestBody Account account) {
        return new ResponseEntity<>(accountDAO.addAccount(account), HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

    @GetMapping("/account_list")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountDAO.getAllAccount(), HttpStatus.OK);
    }

    @GetMapping("/account_detail")
    public ResponseEntity<?> getAccountDetail(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        Account responseAccount = accountDAO.getAccountByUsername(username);
        responseAccount.setPassword("******");
        return ResponseEntity.ok(responseAccount);
    }
}
