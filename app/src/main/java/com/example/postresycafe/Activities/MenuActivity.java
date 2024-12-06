package com.example.postresycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.postresycafe.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MenuActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deslizable);

        // Configuración inicial
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Abre el menú lateral al hacer clic en el botón de menú
        buttonDrawerToggle.setOnClickListener(view -> drawerLayout.open());

        // Oculta el título por defecto en el Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Recibir datos del Intent
        String username = getIntent().getStringExtra("username");

        // Configuración del NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        // Actualizar el nombre de usuario en el encabezado del menú lateral
        TextView userNameTextView = headerView.findViewById(R.id.user_name);
        if (userNameTextView != null && username != null) {
            userNameTextView.setText(username);
        }

        // Configurar el listener para los ítems del menú
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            handleMenuClick(menuItem.getItemId());
            drawerLayout.closeDrawers(); // Cierra el menú lateral
            return true;
        });

        // Configurar clics en imágenes y botones
        configureImageClicks();
        buttonSeeCart();
    }

    // Método para manejar clics en los ítems del menú lateral
    private void handleMenuClick(int itemId) {
        String message;

        if (itemId == R.id.menu_itemPastel) {
            message = "Seleccionaste: Pasteles";
        } else if (itemId == R.id.menu_itemCafe) {
            message = "Seleccionaste: Café";
        } else if (itemId == R.id.menu_itemCrepas) {
            message = "Seleccionaste: Crepas";
        } else if (itemId == R.id.menu_itemGalletas) {
            message = "Seleccionaste: Galletas";
        } else if (itemId == R.id.menu_item3) {
            message = "Seleccionaste: Variado";
        } else if (itemId == R.id.menu_itemCerrarSesion) {
            message = "Cerrando sesión...";
        } else {
            message = "Opción no reconocida";
        }

        // Mostrar el Snackbar con el mensaje correspondiente
        Snackbar.make(drawerLayout, message, Snackbar.LENGTH_SHORT).show();

        if (itemId == R.id.menu_itemCerrarSesion) {
            // Redirigir al Login
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


    // Método para manejar clics en los ítems del Overflow Menu
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        String message;

        if (item.getItemId() == R.id.OverflowSobreNosotros) {
            message = "Seleccionaste: Sobre nosotros";
        } else if (item.getItemId() == R.id.OverflowContactanos) {
            message = "Seleccionaste: Contáctenos";
        } else if (item.getItemId() == R.id.OverflowCompartir) {
            message = "Seleccionaste: Compartir";
        } else {
            message = "Opción no reconocida";
        }

        // Mostrar el Snackbar con el mensaje correspondiente
        Snackbar.make(findViewById(R.id.drawerLayout), message, Snackbar.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }





    // Método para configurar el botón "Ver carrito"
    public void buttonSeeCart() {
        Button buttonSeeCart = findViewById(R.id.buttonSeeCart);
        buttonSeeCart.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    // Método para configurar clics en imágenes del catálogo
    private void configureImageClicks() {
        ImageView imageViewCafe = findViewById(R.id.imageViewCafe);
        imageViewCafe.setOnClickListener(v -> openOrderActivity(1, "Café", R.drawable.cafefinal, 50.0));

        ImageView imageViewPastel = findViewById(R.id.imageViewPastel);
        imageViewPastel.setOnClickListener(v -> openOrderActivity(2, "Pastel", R.drawable.pastelfinal, 100.0));

        ImageView imageViewChocolate = findViewById(R.id.imageViewChocolate);
        imageViewChocolate.setOnClickListener(v -> openOrderActivity(3, "Chocolate", R.drawable.chocholatefinal, 75.0));

        ImageView imageViewPan = findViewById(R.id.imageViewPan);
        imageViewPan.setOnClickListener(v -> openOrderActivity(4, "Pan", R.drawable.panfinal, 30.0));

        ImageView imageViewYogurt = findViewById(R.id.imageViewYogurtConFrutas);
        imageViewYogurt.setOnClickListener(v -> openOrderActivity(5, "Yogurt con Frutas", R.drawable.yogurtconfrutas, 60.0));

        ImageView imageViewBatido = findViewById(R.id.imageViewBatido);
        imageViewBatido.setOnClickListener(v -> openOrderActivity(6, "Batido", R.drawable.batidos, 70.0));
    }

    // Método para abrir la actividad de detalles del pedido
    private void openOrderActivity(int idProduct, String itemName, int imageResId, double basePrice) {
        Intent intent = new Intent(MenuActivity.this, OrderActivity.class);
        intent.putExtra("item_id", idProduct);
        intent.putExtra("item_name", itemName);
        intent.putExtra("item_image", imageResId);
        intent.putExtra("item_price", basePrice);
        startActivity(intent);
    }

    // Inflar el menú de opciones
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    // Método para ir a la pantalla del carrito
    private void goToCartScreen() {
        Intent intent = new Intent(MenuActivity.this, CartActivity.class);
        startActivity(intent);
    }
}
