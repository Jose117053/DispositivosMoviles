package com.example.postresycafe.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.DataBase.CRUD.UserDB;
import com.example.postresycafe.DataBase.Entities.User;
import com.example.postresycafe.R;

public class MainActivity extends AppCompatActivity {

    private UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDB = new UserDB(this);

        EditText usernameField = findViewById(R.id.editTextUsername); // Campo de Email
        EditText passwordField = findViewById(R.id.editTextPassword); // Campo de Password
        Button loginButton = findViewById(R.id.button); // Botón LOGIN


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                if (verifyLogin(username, password)) {
                    goToMenuScreen(username);
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button registerButton = findViewById(R.id.buttonRegister); // Botón de "Registrarse"

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterScreen();
            }
        });














    }

    private boolean verifyLogin(String username, String password) {
        Log.d("LoginDebug", "Método verifyLogin fue llamado");  // Mensaje de depuración

        User user = userDB.getUserByUsername(username);

        return user != null && user.getPassword().equals(password);
    }

    private void goToMenuScreen(String username) {
        // Crear el Intent para ir a la siguiente pantalla
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        intent.putExtra("username", username); // Pasar el username al Intent

        // Iniciar la siguiente actividad
        startActivity(intent);
    }

    private void goToRegisterScreen() {
        // Crear el Intent para ir a RegisterActivity
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}