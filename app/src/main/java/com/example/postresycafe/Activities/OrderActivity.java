package com.example.postresycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.R;
import com.example.postresycafe.Utils.CartManager;

import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    private int quantity = 1;
    private double basePrice; // Precio base del ítem
    private double totalPrice; // Precio total inicial


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Recibir el nombre del ítem y el ID de la imagen desde MenuActivity
        String itemName = getIntent().getStringExtra("item_name");
        int itemImageResId = getIntent().getIntExtra("item_image", -1); // -1 por defecto si no se encuentra
        basePrice = getIntent().getDoubleExtra("item_price", 50.0); // Por defecto 50.0 si no se encuentra
        totalPrice = basePrice;

        // Mostrar el nombre del ítem en el TextView
        TextView itemNameTextView = findViewById(R.id.textViewItemName);
        if (itemName != null) {
            itemNameTextView.setText(itemName);
        }

        // Muestra la imagen recibida dependiendo en donde se dio click ImageView
        ImageView itemImageView = findViewById(R.id.imageViewRandom);
        if (itemImageResId != -1) {
            itemImageView.setImageResource(itemImageResId);
        }


        TextView textViewQuantity = findViewById(R.id.textViewQuantityChanging);
        TextView textViewPrice = findViewById(R.id.textViewDigitsPrice); // Mostrar el precio total
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);

        textViewPrice.setText(String.format(Locale.getDefault(), "$%.2f", totalPrice));


        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++; // Incrementar la cantidad
                totalPrice = basePrice * quantity; // Calcular el precio total
                textViewQuantity.setText(String.valueOf(quantity)); // Actualizar la cantidad
                textViewPrice.setText(String.format(Locale.getDefault(), "$%.2f", totalPrice));
            }
        });

        // Configurar el botón "-"
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) { // Prevenir números menores a 1
                    quantity--; // Decrementar la cantidad
                    totalPrice = basePrice * quantity; // Calcular el precio total
                    textViewQuantity.setText(String.valueOf(quantity)); // Actualizar la cantidad
                    textViewPrice.setText(String.format(Locale.getDefault(), "$%.2f", totalPrice)); // Actualizar el precio total
                }
            }
        });

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMenuScreen();
            }
        });

        Button buttonAddToCart = findViewById(R.id.buttonAddToCart);

        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Agregar el producto al carrito usando CartManager
                CartManager.getInstance().addItem(itemName, quantity, totalPrice);

                Log.d("OrderActivity", "Producto añadido al carrito:");
                Log.d("OrderActivity", "Nombre: " + itemName);
                Log.d("OrderActivity", "Cantidad: " + quantity);
                Log.d("OrderActivity", "Precio total: " + totalPrice);

                // Navegar a CartActivity
                Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                startActivity(intent);

                // Finalizar OrderActivity para regresar al menú después
                finish();
            }
        });

    }

    private void goToMenuScreen() {
        Intent intent = new Intent(OrderActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}
