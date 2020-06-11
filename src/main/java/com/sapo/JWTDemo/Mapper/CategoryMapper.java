package com.sapo.JWTDemo.Mapper;

import com.sapo.JWTDemo.DTO.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        category.setCreatedDate(resultSet.getString("date_created"));
        category.setModifiedDate(resultSet.getString("date_modified"));
        category.setDescription(resultSet.getString("description"));
        return  category;
    }
}
