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

    EditText bmiWeight, bmiHeight;
    Button button;
    TextView bmiResult, bmiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        bmiWeight = findViewById(R.id.bmiWeight);
        bmiHeight = findViewById(R.id.bmiHeight);
        button = findViewById(R.id.bmiBtn);
        bmiResult = findViewById(R.id.bmiResult);
        bmiInfo = findViewById(R.id.bmiInfo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strWeight = bmiWeight.getText().toString();
                String strHeight = bmiHeight.getText().toString();

                if (TextUtils.isEmpty(strWeight)) {
                    bmiWeight.setError("Enter Weight");
                    bmiWeight.requestFocus();
                }
                else if (TextUtils.isEmpty(strHeight)) {
                    bmiHeight.setError("Enter Height");
                    bmiHeight.requestFocus();
                }
                else {
                    double weight = Double.parseDouble(strWeight);
                    double height = Double.parseDouble(strHeight) / 100;

                    if (height <= 0 || weight <= 0) {
                        Toast.makeText(BMI.this, "Invalid Input. Please enter valid values.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        double bmi = weight / (height * height);
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        String formattedBMI = decimalFormat.format(bmi);
                        bmiResult.setText(formattedBMI);
                        displayBMIInfo(bmi);
                    }
                }
            }
        });
    }
    private void displayBMIInfo(double bmi) {
        String bmiInfoText;

        if (bmi < 18.5) {
            bmiInfoText = "You are Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            bmiInfoText = "You are Normal Weight";
        } else if (bmi >= 25 && bmi < 30) {
            bmiInfoText = "You are Overweight";
        } else {
            bmiInfoText = "You are Obese";
        }
        bmiInfo.setText(bmiInfoText);
    }
}
