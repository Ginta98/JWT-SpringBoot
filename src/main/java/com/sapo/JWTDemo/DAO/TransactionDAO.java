package com.sapo.JWTDemo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
@Repository
public class TransactionDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void turnOffAutoCommit() {
        String sql = "SET autocommit = 0;";
        jdbcTemplate.update(sql);
        System.out.println("turn off");
    }

    public void turnOnAutoCommit() {
        String sql = "SET autocommit = 1;";
        jdbcTemplate.update(sql);
        System.out.println("turn on");
    }

    public void startTransaction() {
        String sql = "START TRANSACTION;";
        jdbcTemplate.update(sql);
        System.out.println("start trans");
    }

    public void commitTransaction() {
        String sql = "COMMIT;";
        jdbcTemplate.update(sql);
        System.out.println("commit trans");
    }

    public void rollbackTransaction() {
        String sql = "ROLLBACK;";
        jdbcTemplate.update(sql);
        System.out.println("rollback");
    }
}
