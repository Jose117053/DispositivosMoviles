package com.example.postresycafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referenciar los elementos del layout
        EditText emailField = findViewById(R.id.editTextText8); // Campo de Email
        EditText passwordField = findViewById(R.id.editTextText5); // Campo de Password
        Button loginButton = findViewById(R.id.button); // Botón LOGIN

        // Listener para el botón LOGIN
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método para ir a la siguiente pantalla
                goToMainScreen(emailField);
            }
        });
    }

    private void goToMainScreen(EditText emailField) {
        // Crear el Intent para ir a la siguiente pantalla
        Intent intent = new Intent(MainActivity.this, Menu.class); // Cambia "Menu.class" por la clase correcta
        String email = emailField.getText().toString(); // Obtener el email ingresado
        intent.putExtra("user_email", email); // Pasar el email al Intent

        // Iniciar la siguiente actividad
        startActivity(intent);
    }
}
//