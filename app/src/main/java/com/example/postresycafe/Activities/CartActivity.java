package com.example.postresycafe.Activities;


import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.R;

import java.util.ArrayList;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private ArrayList<String> cartItems = new ArrayList<>();
    private ArrayList<Integer> cartQuantities = new ArrayList<>();
    private ArrayList<Double> cartPrices = new ArrayList<>();
    private LinearLayout cartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartLayout = findViewById(R.id.cartLayout);

        // Obtener datos pasados desde OrderActivity
        String itemName = getIntent().getStringExtra("item_name");
        int quantity = getIntent().getIntExtra("quantity", 0);
        double totalPrice = getIntent().getDoubleExtra("total_price", 0.0);

        if (itemName != null && quantity > 0) {
            cartItems.add(itemName);
            cartQuantities.add(quantity);
            cartPrices.add(totalPrice);
        }

        // Actualizar la vista del carrito
        updateCartView();
    }

    private void updateCartView() {
        cartLayout.removeAllViews(); // Limpia la vista antes de actualizar

        for (int i = 0; i < cartItems.size(); i++) {
            String item = cartItems.get(i);
            int quantity = cartQuantities.get(i);
            double price = cartPrices.get(i);

            // Crear un TextView para cada producto
            TextView itemView = new TextView(this);
            itemView.setText(String.format(Locale.getDefault(),"%s - Cantidad: %d - Precio: $%.2f", item, quantity, price));
            itemView.setTextSize(18);
            cartLayout.addView(itemView);
        }
    }
}
