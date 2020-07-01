package com.sapo.JWTDemo.DTO;

import com.sapo.JWTDemo.Entities.Product;

import java.util.List;


public class ProductMessageForm {
    String message;
    int size = 0;
    int totalData;
    List<Product> data;

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public ProductMessageForm(String message, List<Product> data, int totalData) {
        this.message = message;
        this.data = data;
        this.size = data.size();
        this.totalData = totalData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
