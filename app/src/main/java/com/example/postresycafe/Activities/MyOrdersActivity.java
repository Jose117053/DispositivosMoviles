package com.example.postresycafe.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.DataBase.Entities.Order;
import com.example.postresycafe.DataBase.Services.OrderManager;
import com.example.postresycafe.R;

import java.util.ArrayList;
import java.util.Locale;

public class MyOrdersActivity extends AppCompatActivity {

    private LinearLayout cartLayout;
    private int userId; // ID del usuario actual

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        // Recuperar ID del usuario desde SharedPreferences o Intent

        SharedPreferences sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", -1);

        if (userId == -1) {
            Toast.makeText(this, "Error: Usuario no identificado.", Toast.LENGTH_SHORT).show();
            Log.e("MyOrdersActivity", "Usuario no válido");
            return;
        }

        cartLayout = findViewById(R.id.cartLayout);
        displayOrders(userId);
        onClickBack();
    }



    private void displayOrders(int userId) {
        // Limpia las vistas existentes
        cartLayout.removeAllViews();

        // Recupera las órdenes del usuario
        OrderManager orderManager = new OrderManager(this);
        ArrayList<Order> orders = orderManager.getOrdersByUserId(userId);

        if (orders.isEmpty()) {
            TextView emptyTextView = new TextView(this);
            emptyTextView.setText("There is no orders");
            emptyTextView.setTextSize(18);
            cartLayout.addView(emptyTextView);
            return;
        }


        LayoutInflater inflater = LayoutInflater.from(this);
        for (Order order : orders) {
            View orderView = inflater.inflate(R.layout.myorder_item, cartLayout, false);

            TextView orderNumber = orderView.findViewById(R.id.itemOrder);
            TextView orderPrice = orderView.findViewById(R.id.itemPrice);
            Button detailsButton = orderView.findViewById(R.id.buttonDetails);

            orderNumber.setText(String.format(Locale.getDefault(), "#%d", order.getIdOrder()));
            orderPrice.setText(String.format(Locale.getDefault(), "$%.2f", order.getTotalPrice()));

            // Configura el botón "Details"
            /*
            detailsButton.setOnClickListener(v -> {
                Intent intent = new Intent(MyOrdersActivity.this, OrderDetailsActivity.class);
                intent.putExtra("orderId", order.getIdOrder());
                startActivity(intent);
            });

             */

            // Agrega la vista inflada al contenedor principal
            cartLayout.addView(orderView);
        }
    }

    private void onClickBack(){
        View backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainScreen();
            }
        });
    }

    private void goToMainScreen() {
        Intent intent = new Intent(MyOrdersActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
