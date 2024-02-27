package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Weight extends AppCompatActivity {

    // Declare UI elements
    EditText weightNo;
    Button weightBtn;
    TextView result;
    Spinner weightspinner1, weightspinner2;
    ArrayAdapter<CharSequence>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight);

        // Initialize UI elements
        weightNo = findViewById(R.id.weightNo);
        weightBtn = findViewById(R.id.weightBtn);
        result = findViewById(R.id.result);
        weightspinner1 = findViewById(R.id.weightSpinner1);
        weightspinner2 = findViewById(R.id.weightSpinner2);

        // Create an ArrayAdapter using the string array
        adapter = ArrayAdapter.createFromResource(this, R.array.weight_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        // Apply the adapter to the spinner
        weightspinner1.setAdapter(adapter);
        weightspinner2.setAdapter(adapter);

        // Set on click listener for the weight button
        weightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeight();
            }
        });
    }

    // Method to convert weight
    private void convertWeight() {
        String inputValue = weightNo.getText().toString();
        if (inputValue.isEmpty()) {
            weightNo.setError("Enter Value");
            weightNo.requestFocus();
            return;
        } else {
            // Convert the input value to a double
            double inputWeight = Double.parseDouble(inputValue);

            // Check for invalid input
            if (inputWeight <= 0.0) {
                Toast.makeText(Weight.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
            } else {
                // Get the selected units from the spinners
                String unitFrom = weightspinner1.getSelectedItem().toString();
                String unitTo = weightspinner2.getSelectedItem().toString();

                // Conversion of weight
                double convertedWeight = convert(unitFrom, unitTo, inputWeight);

                // Display the result
                String resultText = String.format("%.2f %s", convertedWeight, unitTo);
                result.setText(resultText);
            }
        }
    }

    // Method to convert weight based on selected units
    private double convert(String unitFrom, String unitTo, double inputWeight) {
        if (unitFrom.equals("kg") && unitTo.equals("g")) {
            return inputWeight * 1000;
        } else if (unitFrom.equals("kg") && unitTo.equals("lb")) {
            return inputWeight * 2.20462;
        } else if (unitFrom.equals("g") && unitTo.equals("kg")) {
            return inputWeight / 1000;
        } else if (unitFrom.equals("g") && unitTo.equals("lb")) {
            return inputWeight * 0.00220462;
        } else if (unitFrom.equals("lb") && unitTo.equals("kg")) {
            return inputWeight / 2.20462;
        } else if (unitFrom.equals("lb") && unitTo.equals("g")) {
            return inputWeight / 0.00220462;
        } else if (unitFrom.equals(unitTo)) {
            return inputWeight;
        } else {
            return 0.0;
        }
    }
}