package com.example.postresycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.R;

import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    private int quantity = 1;
    private double basePrice = 50.0; // Precio base del ítem
    private double totalPrice = basePrice; // Precio total inicial


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Recibir el nombre del ítem y el ID de la imagen desde MenuActivity
        String itemName = getIntent().getStringExtra("item_name");
        int itemImageResId = getIntent().getIntExtra("item_image", -1); // -1 por defecto si no se encuentra

        // Mostrar el nombre del ítem en el TextView
        TextView itemNameTextView = findViewById(R.id.textViewItemName);
        if (itemName != null) {
            itemNameTextView.setText(itemName);
        }

        // Mostrar la imagen seleccionada en el ImageView
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
                // Crear un Intent para pasar datos al carrito
                Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                intent.putExtra("item_name", itemName);
                intent.putExtra("quantity", quantity);
                intent.putExtra("total_price", totalPrice);
                startActivity(intent);

                // Opcional: Finaliza OrderActivity para regresar al menú después
                finish();
            }
        });

    }

    private void goToMenuScreen() {
        Intent intent = new Intent(OrderActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}
