package com.sapo.JWTDemo.DTO;

import com.sapo.JWTDemo.Entities.Category;

import java.util.List;

public class CategoryResponseDTO {
    List<Category> categoryList;
    int totalPage;

    public CategoryResponseDTO(List<Category> categoryList, int totalPage) {
        this.categoryList = categoryList;
        this.totalPage = totalPage;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
