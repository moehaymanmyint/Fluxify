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

public class Volume extends AppCompatActivity {

    // Declare UI elements
    EditText volumeNo;
    Button volumeBtn;
    TextView result;
    Spinner volumespinner1, volumespinner2;
    ArrayAdapter<CharSequence>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume);

        // Initialize UI elements
        volumeNo = findViewById(R.id.volumeNo);
        volumeBtn = findViewById(R.id.volumeBtn);
        result = findViewById(R.id.result);
        volumespinner1 = findViewById(R.id.volumeSpinner1);
        volumespinner2 = findViewById(R.id.volumeSpinner2);

        // Create an ArrayAdapter using the string array
        adapter = ArrayAdapter.createFromResource(this, R.array.volume_units, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        // Apply the adapter to the spinner
        volumespinner1.setAdapter(adapter);
        volumespinner2.setAdapter(adapter);

        // Set on click listener for the volume button
        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertVolume();
            }
        });
    }

    // Method to convert volume
    private void convertVolume() {
        String inputValue = volumeNo.getText().toString();
        if (inputValue.isEmpty()) {
            volumeNo.setError("Enter Value");
            volumeNo.requestFocus();
            return;
        } else {
            // Convert the input value to a double
            double inputVolume = Double.parseDouble(inputValue);

            // Check for invalid input
            if (inputVolume <= 0.0) {
                Toast.makeText(Volume.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
            } else {
                // Get the selected units from the spinners
                String unitFrom = volumespinner1.getSelectedItem().toString();
                String unitTo = volumespinner2.getSelectedItem().toString();

                // Conversion of volume
                double convertedVolume = convert(unitFrom, unitTo, inputVolume);

                // Display the result
                String resultText = String.format("%.2f %s", convertedVolume, unitTo);
                result.setText(resultText);
            }
        }
    }

    // Method to convert volume based on selected units
    private double convert(String unitFrom, String unitTo, double inputVolume) {
        if (unitFrom.equals("m3") && unitTo.equals("cm3")) {
            return inputVolume * 1000000.0;
        } else if (unitFrom.equals("m3") && unitTo.equals("L")) {
            return inputVolume * 1000.0;
        } else if (unitFrom.equals("m3") && unitTo.equals("mL")) {
            return inputVolume * 1000000.0;
        } else if (unitFrom.equals("cm3") && unitTo.equals("m3")) {
            return inputVolume / 1000000.0;
        } else if (unitFrom.equals("cm3") && unitTo.equals("L")) {
            return inputVolume * 0.001;
        } else if (unitFrom.equals("cm3") && unitTo.equals("mL")) {
            return inputVolume;
        } else if (unitFrom.equals("L") && unitTo.equals("m3")) {
            return inputVolume / 1000.0;
        } else if (unitFrom.equals("L") && unitTo.equals("cm3")) {
            return inputVolume * 1000.0;
        } else if (unitFrom.equals("L") && unitTo.equals("mL")) {
            return inputVolume * 1000.0;
        } else if (unitFrom.equals("mL") && unitTo.equals("m3")) {
            return inputVolume / 1000000.0;
        } else if (unitFrom.equals("mL") && unitTo.equals("cm3")) {
            return inputVolume;
        } else if (unitFrom.equals("mL") && unitTo.equals("L")) {
            return inputVolume * 0.001;
        } else if (unitFrom.equals(unitTo)) {
            return inputVolume;
        } else {
            return 0.0;
        }
    }
}