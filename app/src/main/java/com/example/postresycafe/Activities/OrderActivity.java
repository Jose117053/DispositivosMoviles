package com.example.postresycafe.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postresycafe.R;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Recibir el nombre del ítem y el ID de la imagen desde MenuActivity
        String itemName = getIntent().getStringExtra("item_name");
        int itemImageResId = getIntent().getIntExtra("item_image", -1); // -1 por defecto si no se encuentra

        // Mostrar el nombre del ítem en el TextView
        TextView itemNameTextView = findViewById(R.id.textViewItemName);
        if (itemName != null) {
            itemNameTextView.setText(itemName);
        }

        // Mostrar la imagen seleccionada en el ImageView
        ImageView itemImageView = findViewById(R.id.imageViewRandom);
        if (itemImageResId != -1) {
            itemImageView.setImageResource(itemImageResId);
        }
    }
}
