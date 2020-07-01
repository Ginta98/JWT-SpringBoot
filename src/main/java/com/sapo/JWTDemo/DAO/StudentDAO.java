package com.sapo.JWTDemo.DAO;

import com.sapo.JWTDemo.Entities.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource data) {
        this.dataSource = data;
        this.jdbcTemplate = new JdbcTemplate(data);
    }

    public List<Student> getAllStudents() {
        String query = "Select * from Student;";
        return jdbcTemplate.query(query,
                new RowMapper<Student>() {
                    @Override
                    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                        Student returnValue = new Student();
                        returnValue.setId(resultSet.getInt("id"));
                        returnValue.setAge(resultSet.getInt("age"));
                        returnValue.setName(resultSet.getString("name"));
                        returnValue.setUsername(resultSet.getString("username"));
                        returnValue.setPassword(resultSet.getString("password"));
                        return returnValue;
                    }
                }
        );
    }

}
