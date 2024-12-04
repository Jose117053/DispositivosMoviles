package com.example.postresycafe.Utils;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
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

    public void addItem(String itemName, int quantity, double totalPrice) {
        cartItems.add(itemName);
        cartQuantities.add(quantity);
        cartPrices.add(totalPrice);
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
        cartItems.clear();
        cartQuantities.clear();
        cartPrices.clear();
    }
}
