package com.example.postresycafe.DataBase.Entities;

public class OrderItem {
    private int idOrder;
    private int idProduct;
    private int quantity;
    private double localPrice; //es la suma total de la cantidad de este producto en especifico

    public OrderItem() {}


    public OrderItem(int idOrder, int idProduct, int quantity, double localPrice) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.localPrice = localPrice;
    }


    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLocalPrice(){
        return localPrice;
    }

    public void setLocalPrice(double localPrice){
        this.localPrice=localPrice;
    }
}
