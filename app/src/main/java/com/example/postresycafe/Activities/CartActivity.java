package com.example.postresycafe.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.DataBase.CRUD.OrderDB;
import com.example.postresycafe.DataBase.Entities.Order;
import com.example.postresycafe.DataBase.Entities.OrderItem;
import com.example.postresycafe.DataBase.Services.OrderItemManager;
import com.example.postresycafe.DataBase.Services.OrderManager;
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
        placeOrder();
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (int i = 0; i < CartManager.getInstance().getCartPrices().size(); i++) {
            totalPrice += CartManager.getInstance().getCartPrices().get(i);
        }
        return totalPrice;
    }


    private void placeOrder(){

        Button buttonPlaceOrder = findViewById(R.id.buttonPlaceOrder);
        buttonPlaceOrder.setOnClickListener(view -> {
            // Obtener el precio final del carrito
            double totalPrice = calculateTotalPrice();

            // Obtener el ID del usuario de la sesión activa
            SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
            int userId = sharedPreferences.getInt("user_id", -1);
            if (userId == -1) {
                Toast.makeText(this, "Error: Usuario no identificado.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insertar la orden en la base de datos
            OrderManager orderManager = new OrderManager(this);
            OrderItemManager orderItemManager = new OrderItemManager(this);

            Order newOrder = new Order(userId, totalPrice);
            long orderId = orderManager.add(newOrder);

            Log.d("ORDERIdDebug", "El orderID obtenido es: " + orderId);


            if (orderId < 0) {
                Toast.makeText(this, "Error al crear la orden.", Toast.LENGTH_SHORT).show();
                return;
            }


            //Insertar detalles de la orden en la tabla orderItems
            for (int i = 0; i < CartManager.getInstance().getCartItems().size(); i++) {
                int productId = CartManager.getInstance().getCartProductIds().get(i);
                int quantity = CartManager.getInstance().getCartQuantities().get(i);
                double localPrice = CartManager.getInstance().getCartPrices().get(i);
                OrderItem orderItem = new OrderItem((int) orderId, productId, quantity, localPrice);

                long result = orderItemManager.add(orderItem);
                if (result < 0) {
                    Toast.makeText(this, "Error al agregar un producto a la orden.", Toast.LENGTH_SHORT).show();
                }
            }

            CartManager.getInstance().clearCart();

            // Confirmar al usuario y regresar al menú
            Toast.makeText(this, "Orden creada exitosamente.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CartActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
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
