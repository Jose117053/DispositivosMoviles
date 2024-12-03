package com.example.postresycafe.Activities;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
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
        String userEmail = getIntent().getStringExtra("user_email");

        // Acceder al header del NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        // Actualizar los TextView en el header
        TextView emailTextView = headerView.findViewById(R.id.user_email);
        if (emailTextView != null && userEmail != null) {
            emailTextView.setText(userEmail);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}