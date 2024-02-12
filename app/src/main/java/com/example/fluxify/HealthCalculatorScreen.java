package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class HealthCalculatorScreen extends AppCompatActivity {

    View bmiBtn;
    View bmrBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_calculator_screen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bmiBtn = findViewById(R.id.bmiView);
        bmrBtn = findViewById(R.id.bmrView);

        bmiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthCalculatorScreen.this, BMI.class);
                startActivity(intent);
            }
        });

        bmrBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthCalculatorScreen.this, BMR.class);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}