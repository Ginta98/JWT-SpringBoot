package com.sapo.JWTDemo.Controller;


import com.sapo.JWTDemo.DAO.ProductDAO;
import com.sapo.JWTDemo.DTO.Product;
import com.sapo.JWTDemo.DTO.ProductMessageForm;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductAPI {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    ProductDAO productDAO = (ProductDAO) context.getBean("DAOProduct");

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        List<Product> products = productDAO.getAllProduct();
        return products;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        productDAO.createProduct(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> searchProduct(@PathVariable int id) {
        return new ResponseEntity<Product>(productDAO.searchProduct(id), HttpStatus.OK);
    }

    @PutMapping("/product")
    public ResponseEntity<JSONObject> updateProduct(@RequestParam int id, String name) {
        if (productDAO.updateProduct(id, name) == 1) {
            productDAO.updateProduct(id, name);
            JSONObject res = new JSONObject();
            res.put("message", "Success");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            productDAO.updateProduct(id, name);
            JSONObject res = new JSONObject();
            res.put("message", "Fail");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @GetMapping("/product_pagination")
    public ResponseEntity<ProductMessageForm> getProductByPage(@RequestParam int page) {
        int totalData = productDAO.countProduct();
        List<Product> products = productDAO.getProducts(page);
        return new ResponseEntity<>(new ProductMessageForm("Thanh cong", products,totalData), HttpStatus.OK);
    }


}
