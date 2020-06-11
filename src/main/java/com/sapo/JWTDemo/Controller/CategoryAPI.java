package com.sapo.JWTDemo.Controller;

import com.sapo.JWTDemo.DAO.CategoryDAO;
import com.sapo.JWTDemo.DTO.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

}
