package com.example.fluxify;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BMR extends AppCompatActivity {

    // Declare UI elements
    Button btn;
    EditText weight, height, age;
    TextView result, bmrInfo;
    LinearLayout maleLayout, femaleLayout;
    ImageView mimg,fimg;
    String user = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmr);

        // Initialize UI elements
        btn = findViewById(R.id.bmrBtn);
        weight = findViewById(R.id.bmrWeight);
        height = findViewById(R.id.bmrHeight);
        age = findViewById(R.id.bmrAge);
        result = findViewById(R.id.bmrResult);
        bmrInfo = findViewById(R.id.bmrInfo);
        maleLayout = findViewById(R.id.male);
        femaleLayout = findViewById(R.id.female);
        mimg = findViewById(R.id.maleImg);
        fimg = findViewById(R.id.femaleImg);

        // Set on click listeners for gender selection
        maleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mimg.setColorFilter(getResources().getColor(R.color.tab));
                fimg.setColorFilter(getResources().getColor(R.color.black));
                user = "Male";
            }
        });
        femaleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mimg.setColorFilter(getResources().getColor(R.color.black));
                fimg.setColorFilter(getResources().getColor(R.color.tab));
                user = "Female";
            }
        });

        // Set on click listener for the BMR calculation button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values from EditText
                String str1 = weight.getText().toString();
                String str2 = height.getText().toString();
                String str3 = age.getText().toString();

                // Validate input values
                if (user.equals("0")) {
                    Toast.makeText(BMR.this, "Select your Gender", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(str1)) {
                    weight.setError("Select Weight");
                    weight.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(str2)) {
                    height.setError("Select Height");
                    height.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(str3)) {
                    age.setError("Select Age");
                    age.requestFocus();
                    return;
                }
                else {
                    calculate(); // Call the calculate method
                }
            }
        });
    }

    // Method to calculate BMR
    private void calculate() {
        try {
            // Parse input values to double
            double h = Double.parseDouble(height.getText().toString());
            double w = Double.parseDouble(weight.getText().toString());
            double a = Double.parseDouble(age.getText().toString());
            double bmrResult;

            // Calculate BMR based on gender
            if (user.equals("Male")) {
                bmrResult = ((10 * w) + (6.25 * h) - (5 * a) + 5);
            } else if (user.equals("Female")) {
                bmrResult = ((10 * w) + (6.25 * h) - (5 * a) - 161);
            } else {
                Toast.makeText(BMR.this, "Select your Gender", Toast.LENGTH_SHORT).show();
                return;
            }
            // Display the calculated BMR
            result.setText(String.valueOf(bmrResult));
            // Calling Method to display BMR information
            displayBMRInfo(bmrResult);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(BMR.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to display BMR information
    private void displayBMRInfo(double bmrResult) {
        String bmrInfoText;

        // Categorize BMR and provide relevant information
        if (bmrResult < 1487) {
            bmrInfoText = "Sedentary \n(Little or No exercise)";
        } else if (bmrResult < 1704) {
            bmrInfoText = "Lightly Active \n(Light exercise)";
        } else if (bmrResult < 1920) {
            bmrInfoText = "Moderately Active \n(Moderate exercise)";
        } else if (bmrResult < 2137) {
            bmrInfoText = "Very Active \n(Hard exercise)";
        } else {
            bmrInfoText = "Extra Active \n(Very hard exercise)";
        }
        // Display BMR information
        bmrInfo.setText(bmrInfoText);
    }
}