package com.sapo.JWTDemo.DAO;

import com.sapo.JWTDemo.DTO.Product;
import com.sapo.JWTDemo.Mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource data) {
        this.dataSource = data;
        this.jdbcTemplate = new JdbcTemplate(data);
    }

    public boolean createProduct(Product product) {
        try {
            String sql = "insert into product (name,image_link,price,number,sold,description,cat_id) values  (?,?,?,?,?,?,?);";
            jdbcTemplate.update(sql, product.getName(), product.getImg_link(), product.getPrice(), product.getNumber(), product.getSold(), product.getDescription(), product.getCat_id());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> getAllProduct() {
        List<Product> returnValue = new ArrayList<>();
        try {
            String sql = "select * from product;";
            returnValue = jdbcTemplate.query(sql, new ProductMapper());

        } catch (Exception e) {
            returnValue = null;
        } finally {
            return returnValue;
        }
    }

    public int updateProduct(int id, String newName) {
        try {
            String sql = "update product set name = ? where id= ?;";
            return jdbcTemplate.update(sql, newName, id);

        } catch (Exception e) {
            return -1;
        }
    }

    public int deleteProduct(int id) {
        try {
            String sql = "delete from product where id = ?;";
            return jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            return -1;
        }
    }

    public int deleteProductWithCatId(int cat_id) {
        try {
            String sql = "delete from product where cat_id = ?; ";
            System.out.println("delete product");
            return jdbcTemplate.update(sql, cat_id);
        } catch (Exception e) {
            return -1;
        }
    }

    public Product searchProduct(int id) {
        String sql = "Select * from product where id=?;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductMapper());
    }

    public List<Product> getProducts(int page) {
        int number = 10;
        String sql = "select * from product limit ?,?";
        return jdbcTemplate.query(sql, new Object[]{page, number}, new ProductMapper());
    }

    public int countProduct() {
        String sql = "select count(id) from product;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
