package com.sapo.JWTDemo.DAO;

import com.sapo.JWTDemo.DTO.Category;
import com.sapo.JWTDemo.Mapper.CategoryMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource data) {
        this.dataSource = data;
        this.jdbcTemplate = new JdbcTemplate(data);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteCategory(@RequestParam int id) {
//        ApplicationContext contextTransaction = new ClassPathXmlApplicationContext("Beans.xml");
//        TransactionDAO transactionDAO = (TransactionDAO) contextTransaction.getBean("DAOTransaction");
//        transactionDAO.startTransaction(); // start transaction
//        try {
//            productDAO.deleteProductWithCatId(id);  //query 1
//            String sql = "delete from category where id = ?;";
//            jdbcTemplate.update(sql, id);//query 2
//            transactionDAO.commitTransaction(); // commit
//            return 1;
//        } catch (Exception e) {
//            transactionDAO.rollbackTransaction(); //rollback
//            return -1;
//
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        ProductDAO productDAO = (ProductDAO) context.getBean("DAOProduct");
        productDAO.deleteProductWithCatId(id);
        String sql = "delete from category where id = ?;";
        jdbcTemplate.update(sql, id);
        return 0;

    }

    public int createCategory(Category c) {
        try {
            String sql = "insert into category (name,date_created,date_modified,description) values  (?,?,?,?);";
            jdbcTemplate.update(sql, c.getName(), c.getCreatedDate(), c.getModifiedDate(), c.getDescription());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        try {
            String sql = "select * from category";
            categories = jdbcTemplate.query(sql, new CategoryMapper());
            return categories;
        } catch (Exception e) {
            return null;
        } finally {
            return categories;
        }
    }

    public int updateCategory(int id, String name) {
        try {
            String sql = "update category set name =? where id = ?;";
            return jdbcTemplate.update(sql, name, id);
        } catch (Exception e) {
            return -1;
        }
    }
}
