package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Speed extends AppCompatActivity {

    // Declare UI elements
    EditText speedNo;
    Button speedBtn;
    TextView result;
    Spinner speedspinner1, speedspinner2;
    ArrayAdapter<CharSequence> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed);

        // Initialize UI elements
        speedNo = findViewById(R.id.speedNo);
        speedBtn = findViewById(R.id.speedBtn);
        result = findViewById(R.id.result);
        speedspinner1 = findViewById(R.id.speedSpinner1);
        speedspinner2 = findViewById(R.id.speedSpinner2);

        // Create an ArrayAdapter using the string array
        adapter = ArrayAdapter.createFromResource(this, R.array.speed_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        // Apply the adapter to the spinner
        speedspinner1.setAdapter(adapter);
        speedspinner2.setAdapter(adapter);

        // Set on click listener for the speed button
        speedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertSpeed();
            }
        });
    }

    // Method to convert speed
    private void convertSpeed() {
        // Get the input value from the EditText
        String inputValue = speedNo.getText().toString();
        if (inputValue.isEmpty()) {
            speedNo.setError("Enter Value");
            speedNo.requestFocus();
            return;
        } else {
            // Convert the input value to a double
            double inputSpeed = Double.parseDouble(inputValue);

            // Check for invalid input
            if (inputSpeed <= 0.0) {
                Toast.makeText(Speed.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
            } else {
                // Get the selected units from the spinners
                String unitFrom = speedspinner1.getSelectedItem().toString();
                String unitTo = speedspinner2.getSelectedItem().toString();

                // Conversion of speed
                double convertedSpeed = convert(unitFrom, unitTo, inputSpeed);

                // Display the result
                String resultText = String.format("%.2f %s", convertedSpeed, unitTo);
                result.setText(resultText);
            }
        }
    }

    // Method to convert speed based on selected units
    private double convert(String unitFrom, String unitTo, double inputSpeed) {
        if (unitFrom.equals("m/s") && unitTo.equals("km/s")) {
            return inputSpeed * 0.001;
        } else if (unitFrom.equals("m/s") && unitTo.equals("miles/hr")) {
            return inputSpeed * 2.237;
        } else if (unitFrom.equals("km/s") && unitTo.equals("m/s")) {
            return inputSpeed * 1000.0;
        } else if (unitFrom.equals("km/s") && unitTo.equals("miles/hr")) {
            return inputSpeed * 2237;
        } else if (unitFrom.equals("miles/hr") && unitTo.equals("m/s")) {
            return inputSpeed / 2.237;
        } else if (unitFrom.equals("miles/hr") && unitTo.equals("km/s")) {
            return inputSpeed / 2237;
        } else if (unitFrom.equals(unitTo)) {
            return inputSpeed;
        } else {
            return 0.0;
        }
    }
}