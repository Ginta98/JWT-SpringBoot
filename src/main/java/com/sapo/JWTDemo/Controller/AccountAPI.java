package com.sapo.JWTDemo.Controller;

import com.sapo.JWTDemo.DAO.AccountDAO;
import com.sapo.JWTDemo.DTO.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountAPI {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    AccountDAO accountDAO = (AccountDAO) context.getBean("DAOAccount");

    @PostMapping("/account_regist")
    public ResponseEntity<Integer> register(@RequestBody Account account) {
        return new ResponseEntity<>(accountDAO.addAccount(account), HttpStatus.OK);
    }

    //    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")

    @GetMapping("/account_list")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountDAO.getAllAccount(), HttpStatus.OK);
    }
}
