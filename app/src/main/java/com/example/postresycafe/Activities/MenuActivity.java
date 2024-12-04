package com.example.postresycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.postresycafe.R;
import com.google.android.material.navigation.NavigationView;


public class MenuActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deslizable);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonDrawerToggle.setOnClickListener(view -> drawerLayout.open());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Recibir datos del Intent
        String username = getIntent().getStringExtra("username");

        // Acceder al header del NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        // Actualizar los TextView en el header
        TextView userNameTextView = headerView.findViewById(R.id.user_name);
        if (userNameTextView != null && username != null) {
            userNameTextView.setText(username);
        }
        configureImageClicks();
    }

    private void configureImageClicks() {
        ImageView imageViewCafe = findViewById(R.id.imageViewCafe);
        imageViewCafe.setOnClickListener(v -> openOrderActivity("Cafe", R.drawable.cafefinal, 50.0));

        ImageView imageViewPastel = findViewById(R.id.imageViewPastel);
        imageViewPastel.setOnClickListener(v -> openOrderActivity("Pastel", R.drawable.pastelfinal, 100.0));

        ImageView imageViewChocolate = findViewById(R.id.imageViewChocolate);
        imageViewChocolate.setOnClickListener(v -> openOrderActivity("Chocolate", R.drawable.chocholatefinal, 75.0));

        ImageView imageViewPan = findViewById(R.id.imageViewPan);
        imageViewPan.setOnClickListener(v -> openOrderActivity("Pan", R.drawable.panfinal, 30.0));

        ImageView imageViewYogurt = findViewById(R.id.imageViewYogurtConFrutas);
        imageViewYogurt.setOnClickListener(v -> openOrderActivity("Yogurt con Frutas", R.drawable.yogurtconfrutas, 60.0));

        ImageView imageViewBatido = findViewById(R.id.imageViewBatido);
        imageViewBatido.setOnClickListener(v -> openOrderActivity("Batido", R.drawable.batidos, 70.0));
    }

    private void openOrderActivity(String itemName, int imageResId, double basePrice) {
        Intent intent = new Intent(MenuActivity.this, OrderActivity.class);
        intent.putExtra("item_name", itemName);
        intent.putExtra("item_image", imageResId);
        intent.putExtra("item_price", basePrice); // Pasar el precio base
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}