package com.example.postresycafe.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.DataBase.CRUD.UserDB;
import com.example.postresycafe.DataBase.Entities.User;
import com.example.postresycafe.DataBase.Services.ProductManager;
import com.example.postresycafe.DataBase.Services.UserManager;
import com.example.postresycafe.R;

public class LoginActivity extends AppCompatActivity {

    private UserDB userDB;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_DEFAULT_INITIALIZED = "products_initialized";


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
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
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

        initializeDefaultData();


    }

    private boolean verifyLogin(String username, String password) {
        Log.d("LoginDebug", "Método verifyLogin fue llamado");  // Mensaje de depuración

        User user = userDB.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Despues accedere a el a la hora de crea una orden
            getSharedPreferences("user_session", MODE_PRIVATE)
                    .edit()
                    .putInt("user_id", user.getIdUser())
                    .apply();

            getSharedPreferences("user_email", MODE_PRIVATE)
                    .edit()
                    .putString("user_email", user.getEmail())
                    .apply();

            return true;
        }
        return false;
    }

    private void goToMenuScreen(String username) {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void goToRegisterScreen() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void initializeDefaultData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean productsInitialized = sharedPreferences.getBoolean(KEY_DEFAULT_INITIALIZED, false);

        if (!productsInitialized) {

            ProductManager productManager = new ProductManager(this);
            int countProducts = productManager.initializeDefaultProducts();

            UserManager userManager = new UserManager(this);
            int countUsers = userManager.initializeDefaultUserr();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_DEFAULT_INITIALIZED, true);
            editor.apply();
        }
    }
}