package com.sapo.JWTDemo.Mapper;

import com.sapo.JWTDemo.Entities.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setImg_link(resultSet.getString("image_link"));
        product.setPrice(resultSet.getInt("price"));
        product.setNumber(resultSet.getInt("number"));
        product.setSold(resultSet.getInt("sold"));
        product.setDescription(resultSet.getString("description"));
        product.setCat_id(resultSet.getInt("cat_id"));
        return product;
    }
}