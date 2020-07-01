package com.sapo.JWTDemo.Controller;

import com.sapo.JWTDemo.DAO.CategoryDAO;
import com.sapo.JWTDemo.DTO.CategoryResponseDTO;
import com.sapo.JWTDemo.Entities.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryAPI {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    CategoryDAO categoryDAO = (CategoryDAO) context.getBean("DAOCategory");

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable int id) {
        return new ResponseEntity<>(categoryDAO.deleteCategory(id), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<Integer> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryDAO.createCategory(category), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        return new ResponseEntity<>(categoryDAO.getAllCategory(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Integer> updateCategory(@RequestParam int id, @RequestParam String name) {
        return new ResponseEntity<>(categoryDAO.updateCategory(id, name), HttpStatus.OK);
    }

    @GetMapping("/category_page")
    public ResponseEntity<CategoryResponseDTO> getCategoryByPage(@RequestParam String page) {
        if (page.equals("")) {
            page = "0";
        }
        List<Category> cats = categoryDAO.getCategoryByPage(Integer.valueOf(page));
        int totalPage = categoryDAO.getCategoryPageNumber();
        return new ResponseEntity<>(new CategoryResponseDTO(cats, totalPage), HttpStatus.OK);
    }

}
