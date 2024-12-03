package com.example.postresycafe.DataBase.Entities;

public class Product {
    private int idProduct;
    private String nameProduct;
    private String description;
    private double price;

    public Product() {}

    public Product(String nameProduct, String description, double price) {
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
