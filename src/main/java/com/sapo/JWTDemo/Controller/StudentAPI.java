package com.sapo.JWTDemo.Controller;

import com.sapo.JWTDemo.DAO.StudentDAO;
import com.sapo.JWTDemo.Entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentAPI {
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
    StudentDAO studentDAO = (StudentDAO) context.getBean("DAOStudent");

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudentAPI() {
        return new ResponseEntity<>(studentDAO.getAllStudents(), HttpStatus.OK);
    }

}
