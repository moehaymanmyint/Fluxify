package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMI extends AppCompatActivity {

    // Declare UI elements
    EditText bmiWeight, bmiHeight;
    Button button;
    TextView bmiResult, bmiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        // Initialize UI elements
        bmiWeight = findViewById(R.id.bmiWeight);
        bmiHeight = findViewById(R.id.bmiHeight);
        button = findViewById(R.id.bmiBtn);
        bmiResult = findViewById(R.id.bmiResult);
        bmiInfo = findViewById(R.id.bmiInfo);

        // Set on click listener for BMI calculation button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get weight and height input
                String strWeight = bmiWeight.getText().toString();
                String strHeight = bmiHeight.getText().toString();

                // Check if weight is empty
                if (TextUtils.isEmpty(strWeight)) {
                    bmiWeight.setError("Enter Weight");
                    bmiWeight.requestFocus();
                }
                // Check if height is empty
                else if (TextUtils.isEmpty(strHeight)) {
                    bmiHeight.setError("Enter Height");
                    bmiHeight.requestFocus();
                }
                else {
                    // Parse weight and height to double
                    double weight = Double.parseDouble(strWeight);
                    double height = Double.parseDouble(strHeight) / 100;

                    // Check for invalid input
                    if (height <= 0 || weight <= 0) {
                        Toast.makeText(BMI.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // Calculate BMI
                        double bmi = weight / (height * height);
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String formattedBMI = decimalFormat.format(bmi);

                        // Display the calculated BMI
                        bmiResult.setText(formattedBMI);
                        // Calling Method to display BMI information
                        displayBMIInfo(bmi);
                    }
                }
            }
        });
    }

    // Method to display BMI information based on BMI value
    private void displayBMIInfo(double bmi) {
        String bmiInfoText;

        // Categorize BMI and provide relevant information
        if (bmi < 18.5) {
            bmiInfoText = "You are Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiInfoText = "You are Normal Weight";
        } else if (bmi >= 25 && bmi < 30) {
            bmiInfoText = "You are Overweight";
        } else {
            bmiInfoText = "You are Obese";
        }
        // Display BMI information
        bmiInfo.setText(bmiInfoText);
    }
}
