package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    // Declare UI elements
    View commonBtn;
    View conversionBtn;
    View healthBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        // Initialize UI elements
        commonBtn = findViewById(R.id.commonButton);
        conversionBtn = findViewById(R.id.conversionButton);
        healthBtn = findViewById(R.id.healthButton);

        // Set on click listener for the Common Converter button
        commonBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, CommonConverterScreen.class);
                startActivity(intent);
            }
        });

        // Set on click listener for the Conversion Calculator button
        conversionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, ConversionCalculatorScreen.class);
                startActivity(intent);
            }
        });

        // Set on click listener for the Health Calculator button
        healthBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, HealthCalculatorScreen.class);
                startActivity(intent);
            }
        });
    }
}