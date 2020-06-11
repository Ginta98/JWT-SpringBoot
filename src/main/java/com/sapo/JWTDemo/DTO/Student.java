package com.sapo.JWTDemo.DTO;

public class Student {
    int id, age;
    String name, username, password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student() {
    }

    public Student(int id, int age, String name, String username, String password) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
