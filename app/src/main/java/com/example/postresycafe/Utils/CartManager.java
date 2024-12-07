package com.example.postresycafe.Utils;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private final ArrayList<Integer> cartProductIds = new ArrayList<>(); // Lista de IDs de productos
    private final ArrayList<String> cartItems = new ArrayList<>();
    private final ArrayList<Integer> cartQuantities = new ArrayList<>();
    private final ArrayList<Double> cartPrices = new ArrayList<>();

    private CartManager() {
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(int productId, String itemName, int quantity, double totalPrice) {
        cartProductIds.add(productId);
        cartItems.add(itemName);
        cartQuantities.add(quantity);
        cartPrices.add(totalPrice);
    }

    public ArrayList<Integer> getCartProductIds() {
        return cartProductIds;
    }

    public ArrayList<String> getCartItems() {
        return cartItems;
    }

    public ArrayList<Integer> getCartQuantities() {
        return cartQuantities;
    }

    public ArrayList<Double> getCartPrices() {
        return cartPrices;
    }

    public void clearCart() {
        cartProductIds.clear();
        cartItems.clear();
        cartQuantities.clear();
        cartPrices.clear();
    }

    public void removeItem(int index) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
            cartQuantities.remove(index);
            cartPrices.remove(index);
            cartProductIds.remove(index);
        }
    }

}
