package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

public class Length extends AppCompatActivity {

    // Declare UI elements
    EditText lengthNo;
    Button lengthBtn;
    TextView result;
    Spinner lengthspinner1, lengthspinner2;
    ArrayAdapter<CharSequence>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.length);

        // Initialize UI elements
        lengthNo = findViewById(R.id.lengthNo);
        lengthBtn = findViewById(R.id.lengthbBtn);
        result = findViewById(R.id.result);
        lengthspinner1 = findViewById(R.id.lengthSpinner1);
        lengthspinner2 = findViewById(R.id.lengthSpinner2);

        // Create an ArrayAdapter using the string array
        adapter = ArrayAdapter.createFromResource(this, R.array.length_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        // Apply the adapter to the spinner
        lengthspinner1.setAdapter(adapter);
        lengthspinner2.setAdapter(adapter);

        // Set on click listener for the length button
        lengthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLength();
            }
        });
    }

    // Method to convert length
    private void convertLength() {
        // Get the input value from the EditText
        String inputValue = lengthNo.getText().toString();
        if (inputValue.isEmpty()) {
            lengthNo.setError("Enter Value");
            lengthNo.requestFocus();
            return;
        } else {
            // Convert the input value to a double
            double inputLength = Double.parseDouble(inputValue);

            // Check for invalid input
            if (inputLength <= 0.0) {
                Toast.makeText(Length.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
            } else {
                // Get the selected units from the spinners
                String unitFrom = lengthspinner1.getSelectedItem().toString();
                String unitTo = lengthspinner2.getSelectedItem().toString();

                // Conversion of length
                double convertedLength = convert(unitFrom, unitTo, inputLength);

                // Display the result
                String resultText = String.format("%.2f %s", convertedLength, unitTo);
                result.setText(resultText);
            }
        }
    }

    // Method to convert length based on selected units
    private double convert(String unitFrom, String unitTo, double inputLength) {
        if (unitFrom.equals("km") && unitTo.equals("m")) {
            return inputLength * 1000;
        } else if (unitFrom.equals("km") && unitTo.equals("cm")) {
            return inputLength * 100000;
        } else if (unitFrom.equals("km") && unitTo.equals("mm")) {
            return inputLength * 1000000;
        } else if (unitFrom.equals("km") && unitTo.equals("ft")) {
            return inputLength * 3280.84;
        } else if (unitFrom.equals("km") && unitTo.equals("in")) {
            return inputLength * 39370.1;
        } else if (unitFrom.equals("m") && unitTo.equals("km")) {
            return inputLength / 1000;
        } else if (unitFrom.equals("m") && unitTo.equals("cm")) {
            return inputLength * 100;
        } else if (unitFrom.equals("m") && unitTo.equals("mm")) {
            return inputLength * 1000;
        } else if (unitFrom.equals("m") && unitTo.equals("ft")) {
            return inputLength * 3.28084;
        } else if (unitFrom.equals("m") && unitTo.equals("in")) {
            return inputLength * 39.3701;
        } else if (unitFrom.equals("cm") && unitTo.equals("km")) {
            return inputLength / 100000;
        } else if (unitFrom.equals("cm") && unitTo.equals("m")) {
            return inputLength / 100;
        } else if (unitFrom.equals("cm") && unitTo.equals("mm")) {
            return inputLength * 10;
        } else if (unitFrom.equals("cm") && unitTo.equals("ft")) {
            return inputLength * 0.0328084;
        } else if (unitFrom.equals("cm") && unitTo.equals("in")) {
            return inputLength * 0.393701;
        } else if (unitFrom.equals("mm") && unitTo.equals("km")) {
            return inputLength / 1e+6;
        } else if (unitFrom.equals("mm") && unitTo.equals("m")) {
            return inputLength / 1000;
        } else if (unitFrom.equals("mm") && unitTo.equals("cm")) {
            return inputLength / 10;
        } else if (unitFrom.equals("mm") && unitTo.equals("ft")) {
            return inputLength * 0.00328084;
        } else if (unitFrom.equals("mm") && unitTo.equals("in")) {
            return inputLength * 0.0393701;
        } else if (unitFrom.equals("ft") && unitTo.equals("km")) {
            return inputLength / 3280.84;
        } else if (unitFrom.equals("ft") && unitTo.equals("m")) {
            return inputLength / 3.28084;
        } else if (unitFrom.equals("ft") && unitTo.equals("cm")) {
            return inputLength / 0.0328084;
        } else if (unitFrom.equals("ft") && unitTo.equals("mm")) {
            return inputLength / 0.00328084;
        } else if (unitFrom.equals("ft") && unitTo.equals("in")) {
            return inputLength * 12;
        } else if (unitFrom.equals("in") && unitTo.equals("km")) {
            return inputLength / 39370.1;
        } else if (unitFrom.equals("in") && unitTo.equals("m")) {
            return inputLength / 39.3701;
        } else if (unitFrom.equals("in") && unitTo.equals("cm")) {
            return inputLength / 0.393701;
        } else if (unitFrom.equals("in") && unitTo.equals("mm")) {
            return inputLength / 0.0393701;
        } else if (unitFrom.equals("in") && unitTo.equals("ft")) {
            return inputLength / 12;
        } else if (unitFrom.equals(unitTo)) {
            return inputLength;
        } else {
            return 0.0;
        }
    }
}