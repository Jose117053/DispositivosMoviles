package com.example.postresycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.R;
import com.example.postresycafe.Utils.CartManager;

import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartLayout = findViewById(R.id.cartLayout);

        CartManager cartManager = CartManager.getInstance();
        updateCartView(cartManager);
        Button addMoreButton = findViewById(R.id.buttonAddMore); // Botón de "Registrarse"

        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método para ir a la ventana de registro
                goToMenuScreen();
            }
        });
    }

    private void updateCartView(CartManager cartManager) {
        cartLayout.removeAllViews(); // Limpia la vista antes de actualizar

        LayoutInflater inflater = LayoutInflater.from(this);
        double finalPrice = 0; // suma todos los precios de los productos que estan en el carrito

        for (int i = 0; i < cartManager.getCartItems().size(); i++) {
            String item = cartManager.getCartItems().get(i);
            int quantity = cartManager.getCartQuantities().get(i);
            double price = cartManager.getCartPrices().get(i);

            // Inflar el diseño del ítem del carrito, acumula uno tras otro
            View cartItemView = inflater.inflate(R.layout.cart_item, cartLayout, false);

            TextView itemNameView = cartItemView.findViewById(R.id.itemName);
            TextView itemQuantityView = cartItemView.findViewById(R.id.itemQuantity);
            TextView itemPriceView = cartItemView.findViewById(R.id.itemPrice);

            itemNameView.setText(item);
            itemQuantityView.setText(String.format(Locale.getDefault(), "Cantidad: %d", quantity));
            itemPriceView.setText(String.format(Locale.getDefault(), "Precio: $%.2f", price));

            finalPrice += price;


            cartLayout.addView(cartItemView);
        }

        TextView finalPriceTextView = findViewById(R.id.textViewFinalPriceNumber);
        finalPriceTextView.setText(String.format(Locale.getDefault(), "$%.2f", finalPrice));

    }


    private void goToMenuScreen() {
        Intent intent = new Intent(CartActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
