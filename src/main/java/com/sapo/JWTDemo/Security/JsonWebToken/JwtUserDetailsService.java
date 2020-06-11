package com.sapo.JWTDemo.Security.JsonWebToken;


import com.sapo.JWTDemo.DAO.AccountDAO;
import com.sapo.JWTDemo.DTO.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    AccountDAO accountDAO = (AccountDAO) context.getBean("DAOAccount");

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountDAO.getAccountByUsername(userName);
        System.out.println(account.toString());
        List<String> roles = null;
        if (account != null) {
            roles = accountDAO.getRoles(account.getId());
        }
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (String role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
                System.out.println("role:"+role);
            }
        }
        return new User(account.getUsername(), account.getPassword(), grantList);
    }
}
