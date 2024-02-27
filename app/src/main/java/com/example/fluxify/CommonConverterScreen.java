package com.example.fluxify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

public class CommonConverterScreen extends AppCompatActivity {

    // Declare UI elements
    View lengthBtn;
    View areaBtn;
    View weightBtn;
    View volumeBtn;
    View speedBtn;
    View timeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_converter_screen);

        // Enable the back button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize UI elements
        lengthBtn = findViewById(R.id.lengthView);
        areaBtn = findViewById(R.id.areaView);
        weightBtn = findViewById(R.id.weightView);
        volumeBtn = findViewById(R.id.volumeView);
        speedBtn = findViewById(R.id.speedView);
        timeBtn = findViewById(R.id.timeView);

        // Set on click listeners for each common converter category
        lengthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonConverterScreen.this, Length.class);
                startActivity(intent);
            }
        });

        areaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonConverterScreen.this, Area.class);
                startActivity(intent);
            }
        });

        weightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonConverterScreen.this, Weight.class);
                startActivity(intent);
            }
        });

        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonConverterScreen.this, Volume.class);
                startActivity(intent);
            }
        });

        speedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonConverterScreen.this, Speed.class);
                startActivity(intent);
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonConverterScreen.this, Time.class);
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