package com.sapo.JWTDemo.DTO;

public class Product {
    String name, img_link, description;
    int id, cat_id, price, number, sold;

    public Product() {
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", img_link='" + img_link + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", cat_id=" + cat_id +
                ", price=" + price +
                ", number=" + number +
                ", sold=" + sold +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Product(String name, String img_link, String description, int cat_id, int price, int number, int sold) {
        this.name = name;
        this.img_link = img_link;
        this.description = description;
        this.cat_id = cat_id;
        this.price = price;
        this.number = number;
        this.sold = sold;
    }
}
