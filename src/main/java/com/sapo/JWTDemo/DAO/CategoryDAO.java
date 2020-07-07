package com.sapo.JWTDemo.DAO;

import com.sapo.JWTDemo.Entities.Category;
import com.sapo.JWTDemo.Mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductDAO productDAO;
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
//            StopWatch stopWatch = new StopWatch();
//            stopWatch.start("first call db");
            String sql = "SELECT * from category ORDER BY id DESC";
            categories = jdbcTemplate.query(sql, new CategoryMapper());
//            stopWatch.stop();
//            stopWatch.start("second call db");
//            List<Category> test = jdbcTemplate.query(sql, new CategoryMapper());
//            stopWatch.stop();
//            System.out.println(stopWatch.prettyPrint());
            return categories;
        } catch (Exception e) {
            return null;
        } finally {
            return categories;
        }
    }

    public List<Category> getCategoryByPage(int page) {

        List<Category> categories = new ArrayList<>();
        int number = 8;
        try {
            String sql = "select * from category order by id desc limit ?,?";
            categories = jdbcTemplate.query(sql, new Object[]{page*number, number}, new CategoryMapper());

        } catch (Exception e) {
            System.out.println(e);

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

    public int getCategoryPageNumber() {
        try {
            int number = 8;
            String sql = "Select count(id) from category";
            double totalData = jdbcTemplate.queryForObject(sql, Integer.class);
            int totalPage=(int) Math.ceil(totalData/number)-1;
            return totalPage;
        } catch (Exception e) {
            return -1;
        }
    }
}
