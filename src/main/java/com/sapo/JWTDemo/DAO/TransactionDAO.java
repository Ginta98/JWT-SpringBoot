package com.sapo.JWTDemo.DAO;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TransactionDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource data) {
        this.dataSource = data;
        this.jdbcTemplate = new JdbcTemplate(data);
    }
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
