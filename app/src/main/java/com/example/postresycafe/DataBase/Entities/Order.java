package com.example.postresycafe.DataBase.Entities;

public class Order {
    private int idOrder;
    private int idUser;
    private double totalPrice;

    public Order() {}

    public Order(int idUser, double totalPrice) {
        this.idUser = idUser;
        this.totalPrice = totalPrice;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
