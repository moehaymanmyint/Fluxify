package com.example.fluxify;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Temperature extends AppCompatActivity {

    // Declare UI elements
    EditText tempNo;
    Spinner tempSpinner1, tempSpinner2;
    Button tempBtn;
    TextView result;
    ArrayAdapter<CharSequence>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);

        // Initialize UI elements
        tempNo = findViewById(R.id.tempNo);
        tempBtn = findViewById(R.id.tempBtn);
        result = findViewById(R.id.result);
        tempSpinner1 = findViewById(R.id.tempSpinner1);
        tempSpinner2 = findViewById(R.id.tempSpinner2);

        // Create an ArrayAdapter using the string array
        adapter = ArrayAdapter.createFromResource(this, R.array.temp_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        // Apply the adapter to the spinner
        tempSpinner1.setAdapter(adapter);
        tempSpinner2.setAdapter(adapter);

        // Set on click listener for the temperature button
        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }

    // Method to convert temperature
    private void convertTemperature() {
        String inputValue = tempNo.getText().toString();
        if (inputValue.isEmpty()) {
            tempNo.setError("Enter Value");
            tempNo.requestFocus();
            return;
        } else {
            // Convert the input value to a double
            double inputTemperature = Double.parseDouble(inputValue);

            // Check for invalid input
            if (inputTemperature <= 0.0) {
                Toast.makeText(Temperature.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
            } else {
                // Get the selected units from the spinners
                String unitFrom = tempSpinner1.getSelectedItem().toString();
                String unitTo = tempSpinner2.getSelectedItem().toString();

                // Conversion of temperature
                double convertedTemperature = convert(unitFrom, unitTo, inputTemperature);

                // Display the result
                String resultText = String.format("%.2f %s", convertedTemperature, unitTo);
                result.setText(resultText);
            }
        }
    }

    // Method to convert temperature based on selected units
    private double convert(String unitFrom, String unitTo, double inputTemperature) {
        if (unitFrom.equals("°F") && unitTo.equals("°C")) {
            return (inputTemperature - 32) * 5.0 / 9.0;
        } else if (unitFrom.equals("°C") && unitTo.equals("°F")) {
            return (inputTemperature * 9.0 / 5.0) + 32;
        } else if (unitFrom.equals(unitTo)) {
            return inputTemperature;
        } else {
            return 0.0;
        }
    }
}
