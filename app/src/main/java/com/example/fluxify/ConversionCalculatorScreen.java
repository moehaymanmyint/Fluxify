package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ConversionCalculatorScreen extends AppCompatActivity {

    // Declare UI elements
    View tempBtn;
    View ageBtn;
    View discountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_calculator_screen);

        // Enable the back button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize UI elements
        tempBtn = findViewById(R.id.tempView);
        ageBtn = findViewById(R.id.ageView);
        discountBtn = findViewById(R.id.discountView);

        // Set on click listeners for each conversion converter category
        tempBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConversionCalculatorScreen.this, Temperature.class);
                startActivity(intent);
            }
        });

        ageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConversionCalculatorScreen.this, Age.class);
                startActivity(intent);
            }
        });

        discountBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConversionCalculatorScreen.this, Discount.class);
                startActivity(intent);
            }
        });
    }

    // Handle the back button click in the action bar
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}