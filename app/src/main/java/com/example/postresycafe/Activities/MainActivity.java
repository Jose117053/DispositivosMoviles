package com.example.postresycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.R;

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


        Button registerButton = findViewById(R.id.buttonRegister); // Botón de "Registrarse"

        // Listener para el botón "Registrarse"
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método para ir a la ventana de registro
                goToRegisterScreen();
            }
        });






    }

    private void goToMainScreen(EditText emailField) {
        // Crear el Intent para ir a la siguiente pantalla
        Intent intent = new Intent(MainActivity.this, MenuActivity.class); // Cambia "Menu.class" por la clase correcta
        String email = emailField.getText().toString(); // Obtener el email ingresado
        intent.putExtra("user_email", email); // Pasar el email al Intent

        // Iniciar la siguiente actividad
        startActivity(intent);
    }

    private void goToRegisterScreen() {
        // Crear el Intent para ir a RegisterActivity
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
//