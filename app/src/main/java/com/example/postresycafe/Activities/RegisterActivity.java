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
import com.example.postresycafe.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button nextButton;
    private UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        nextButton = findViewById(R.id.buttonNext);


        // Listener para el botón "Registrarse"
        userDB = new UserDB(this);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método para ir a la ventana de registro
                registerUser();
            }
        });



    }

    private void registerUser() {
        // Obtenemos los valores de los campos de entrada
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

        // Abrimos la base de datos para escritura
        userDB.openForWrite();

        // Creamos el objeto User y lo insertamos en la base de datos
        long result = userDB.insertUser(new User(username, email, password));

        userDB.close();

        // Verificamos si el usuario se insertó correctamente
        if (result != -1) {
            Toast.makeText(RegisterActivity.this, "Te haz registrado con exito", Toast.LENGTH_SHORT).show();
            goToMainScreen(); // Navegar a la siguiente actividad (MainActivity)
        } else {
            Toast.makeText(RegisterActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToMainScreen() {
        // Crear el Intent para ir a RegisterActivity
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}