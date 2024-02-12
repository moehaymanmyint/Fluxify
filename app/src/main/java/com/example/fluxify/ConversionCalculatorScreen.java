package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ConversionCalculatorScreen extends AppCompatActivity {

    View tempBtn;
    View ageBtn;
    View discountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_calculator_screen);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tempBtn = findViewById(R.id.tempView);
        ageBtn = findViewById(R.id.ageView);
        discountBtn = findViewById(R.id.discountView);

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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}