package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Area extends AppCompatActivity {

    // Declare UI elements
    EditText areaNo;
    Button areaBtn;
    TextView result;
    Spinner areaspinner1, areaspinner2;
    ArrayAdapter<CharSequence>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area);

        // Initialize UI elements
        areaNo = findViewById(R.id.areaNo);
        areaBtn = findViewById(R.id.areaBtn);
        result = findViewById(R.id.result);
        areaspinner1 = findViewById(R.id.areaSpinner1);
        areaspinner2 = findViewById(R.id.areaSpinner2);

        // Create an ArrayAdapter using the string array
        adapter = ArrayAdapter.createFromResource(this, R.array.area_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        // Apply the adapter to the spinner
        areaspinner1.setAdapter(adapter);
        areaspinner2.setAdapter(adapter);

        // Set on click listener for the area button
        areaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertArea();
            }
        });
    }

    // Method to convert area
    private void convertArea() {
        // Get the input value from the EditText
        String inputValue = areaNo.getText().toString();
        if (inputValue.isEmpty()) {
            areaNo.setError("Enter Value");
            areaNo.requestFocus();
            return;
        } else {
            // Convert the input value to a double
            double inputArea = Double.parseDouble(inputValue);

            // Check for invalid input
            if (inputArea <= 0.0) {
                Toast.makeText(Area.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
            } else {
                // Get the selected units from the spinners
                String unitFrom = areaspinner1.getSelectedItem().toString();
                String unitTo = areaspinner2.getSelectedItem().toString();

                // Conversion of area
                double convertedArea = convert(unitFrom, unitTo, inputArea);

                // Display the result
                String resultText = String.format("%.2f %s", convertedArea, unitTo);
                result.setText(resultText);
            }
        }
    }

    // Method to convert area based on selected units
    private double convert(String unitFrom, String unitTo, double inputArea) {
        if (unitFrom.equals("km2") && unitTo.equals("m2")) {
            return inputArea * 1000000;
        } else if (unitFrom.equals("km2") && unitTo.equals("cm2")) {
            return inputArea * 10000000000.0;
        } else if (unitFrom.equals("km2") && unitTo.equals("mm2")) {
            return inputArea * 1000000000000.0;
        } else if (unitFrom.equals("km2") && unitTo.equals("ft2")) {
            return inputArea * 10763910.4;
        } else if (unitFrom.equals("km2") && unitTo.equals("in2")) {
            return inputArea * 1550003100.0;
        } else if (unitFrom.equals("m2") && unitTo.equals("km2")) {
            return inputArea / 1000000.0;
        } else if (unitFrom.equals("m2") && unitTo.equals("cm2")) {
            return inputArea * 10000.0;
        } else if (unitFrom.equals("m2") && unitTo.equals("mm2")) {
            return inputArea * 1000000.0;
        } else if (unitFrom.equals("m2") && unitTo.equals("ft2")) {
            return inputArea * 10.764;
        } else if (unitFrom.equals("m2") && unitTo.equals("in2")) {
            return inputArea * 1550.0031;
        } else if (unitFrom.equals("cm2") && unitTo.equals("km2")) {
            return inputArea / 10000000000.0;
        } else if (unitFrom.equals("cm2") && unitTo.equals("m2")) {
            return inputArea / 10000.0;
        } else if (unitFrom.equals("cm2") && unitTo.equals("mm2")) {
            return inputArea * 100.0;
        } else if (unitFrom.equals("cm2") && unitTo.equals("ft2")) {
            return inputArea * 0.00107639;
        } else if (unitFrom.equals("cm2") && unitTo.equals("in2")) {
            return inputArea * 0.15500031;
        } else if (unitFrom.equals("mm2") && unitTo.equals("km2")) {
            return inputArea / 1000000000000.0;
        } else if (unitFrom.equals("mm2") && unitTo.equals("m2")) {
            return inputArea / 1000000.0;
        } else if (unitFrom.equals("mm2") && unitTo.equals("cm2")) {
            return inputArea / 100.0;
        } else if (unitFrom.equals("mm2") && unitTo.equals("ft2")) {
            return inputArea * 0.000010764;
        } else if (unitFrom.equals("mm2") && unitTo.equals("in2")) {
            return inputArea * 0.0015500031;
        } else if (unitFrom.equals("ft2") && unitTo.equals("km2")) {
            return inputArea / 10763910.4;
        } else if (unitFrom.equals("ft2") && unitTo.equals("m2")) {
            return inputArea / 10.764;
        } else if (unitFrom.equals("ft2") && unitTo.equals("cm2")) {
            return inputArea * 929.0304;
        } else if (unitFrom.equals("ft2") && unitTo.equals("mm2")) {
            return inputArea * 92903.04;
        } else if (unitFrom.equals("ft2") && unitTo.equals("in2")) {
            return inputArea * 144.0;
        } else if (unitFrom.equals("in2") && unitTo.equals("km2")) {
            return inputArea / 1550003100.0;
        } else if (unitFrom.equals("in2") && unitTo.equals("m2")) {
            return inputArea / 1550.0031;
        } else if (unitFrom.equals("in2") && unitTo.equals("cm2")) {
            return inputArea / 0.15500031;
        } else if (unitFrom.equals("in2") && unitTo.equals("mm2")) {
            return inputArea / 0.0015500031;
        } else if (unitFrom.equals("in2") && unitTo.equals("ft2")) {
            return inputArea / 144.0;
        } else if (unitFrom.equals(unitTo)) {
            return inputArea;
        } else {
            return 0.0;
        }
    }
}