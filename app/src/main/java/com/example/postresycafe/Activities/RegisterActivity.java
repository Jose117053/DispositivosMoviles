package com.example.postresycafe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postresycafe.DataBase.CRUD.UserDB;
import com.example.postresycafe.DataBase.Entities.User;
import com.example.postresycafe.DataBase.Services.UserManager;
import com.example.postresycafe.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button nextButton;
    private UserDB userDB;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        nextButton = findViewById(R.id.buttonNext);


        userDB = new UserDB(this);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método para ir a la ventana de registro
                registerUser();
            }
        });

        onClickBack();



    }

    private void onClickBack(){
        backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainScreen();
            }
        });
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Validación básica de los campos
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userDB.getUser(username)) {
            Toast.makeText(RegisterActivity.this, "Nombre de usuario ya registrado", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }


        UserManager userManager=new UserManager(this);
        long result = userManager.insert(new User(username, email, password));

        if (result != -1) {
            Toast.makeText(RegisterActivity.this, "Te haz registrado con exito", Toast.LENGTH_SHORT).show();
            goToMainScreen();
        } else {
            Toast.makeText(RegisterActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToMainScreen() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}