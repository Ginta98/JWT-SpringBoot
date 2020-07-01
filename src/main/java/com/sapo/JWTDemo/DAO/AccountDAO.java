package com.sapo.JWTDemo.DAO;

import com.sapo.JWTDemo.Entities.Account;
import com.sapo.JWTDemo.Mapper.AccountMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.List;

public class AccountDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource data) {
        this.dataSource = data;
        this.jdbcTemplate = new JdbcTemplate(data);
    }

    public Account getAccountByUsername(String username) {
        String sql = "Select * from account where username=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new AccountMapper());
    }

    public int addAccount(Account account) {
        try {
            String sql = "Insert into account (username,password) values (?,?);";
            String passwordEncoded = new BCryptPasswordEncoder().encode(account.getPassword());
            System.out.println("pass:" + passwordEncoded);
            jdbcTemplate.update(sql, account.getUsername(), passwordEncoded);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<String> getRoles(int userId) {
        String sql = "Select r.role_name "
                + " from account_role ur, role r "
                + " where ur.role_id = r.id and ur.account_id = ? ";

        Object[] params = new Object[]{userId};
        List<String> roles = jdbcTemplate.queryForList(sql, params, String.class);
        return roles;
    }

    public List<Account> getAllAccount() {
        String sql = "Select * from account";
        return jdbcTemplate.query(sql, new AccountMapper());
    }

}
