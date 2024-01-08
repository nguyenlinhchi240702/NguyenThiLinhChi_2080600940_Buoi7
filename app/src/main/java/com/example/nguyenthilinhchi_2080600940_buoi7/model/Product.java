package com.example.nguyenthilinhchi_2080600940_buoi7.model;

public class Product {
    private int ID;
    private String Name;
    public int getID() {
        return ID;
    }
    public Product(){
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getImage() {
        return Image;
    }
    public void setImage(String image) {
        Image = image;
    }
    public float getPrice() {
        return Price;
    }
    public void setPrice(float price) {
        Price = price;
    }
    public Product(int ID, String name, String image, float price) {
        this.ID = ID;
        Name = name;
        Image = image;
        Price = price;

    }
    private String Image;
    private float Price;
}