package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    private View commonBtn;
    private View conversionBtn;
    private View healthBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        commonBtn = findViewById(R.id.commonButton);
        conversionBtn = findViewById(R.id.conversionButton);
        healthBtn = findViewById(R.id.healthButton);

        commonBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, CommonConverterScreen.class);
                startActivity(intent);
            }
        });
        conversionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, ConversionCalculatorScreen.class);
                startActivity(intent);
            }
        });
        healthBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, HealthCalculatorScreen.class);
                startActivity(intent);
            }
        });
    }
}