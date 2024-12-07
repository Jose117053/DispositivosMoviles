package com.example.postresycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.DataBase.Entities.OrderItem;
import com.example.postresycafe.DataBase.Services.OrderItemManager;
import com.example.postresycafe.DataBase.Services.ProductManager;
import com.example.postresycafe.R;

import java.util.ArrayList;
import java.util.Locale;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);

        int orderId = getIntent().getIntExtra("orderId", -1); // Recibir el ID de la orden
        if (orderId == -1) {
            finish();
            return;
        }

        displayOrderDetails(orderId);
        onClickBack();
    }

    private void displayOrderDetails(int orderId) {
        LinearLayout cartLayout = findViewById(R.id.cartLayout);
        cartLayout.removeAllViews();

        OrderItemManager orderItemManager = new OrderItemManager(this);
        ProductManager productManager = new ProductManager(this);

        ArrayList<OrderItem> orderItems = orderItemManager.getItemsByOrderId(orderId);
        LayoutInflater inflater = LayoutInflater.from(this);
        double totalPrice = 0;

        for (OrderItem orderItem : orderItems) {
            View detailItemView = inflater.inflate(R.layout.detailsorderitem, cartLayout, false);

            TextView itemProductView = detailItemView.findViewById(R.id.itemProductItem);
            TextView itemQuantityView = detailItemView.findViewById(R.id.itemQuantityItem);
            TextView itemPriceView = detailItemView.findViewById(R.id.itemkPriceItem);

            String productName = productManager.getById(orderItem.getIdProduct()).getNameProduct();

            itemProductView.setText(productName);
            itemQuantityView.setText(String.valueOf(orderItem.getQuantity()));
            itemPriceView.setText(String.format(Locale.getDefault(), "$%.2f", orderItem.getLocalPrice()));

            totalPrice += orderItem.getLocalPrice();

            cartLayout.addView(detailItemView);
        }

        // Actualizar el precio final en el layout
        TextView finalPriceView = findViewById(R.id.textViewFinalPriceNumber);
        finalPriceView.setText(String.format(Locale.getDefault(), "$%.2f", totalPrice));


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
        Intent intent = new Intent(OrderDetailsActivity.this, MyOrdersActivity.class);
        startActivity(intent);
        finish();
    }
}
