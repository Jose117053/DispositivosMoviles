package com.example.postresycafe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.postresycafe.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button nextButton = findViewById(R.id.buttonNext); // Botón de "Registrarse"

        // Listener para el botón "Registrarse"
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar al método para ir a la ventana de registro
                goToMainScreen();
            }
        });
    }

    private void goToMainScreen() {
        // Crear el Intent para ir a RegisterActivity
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

}