package com.example.fluxify;

import static com.example.fluxify.R.id.discountAmt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Discount extends AppCompatActivity {

    // Declare UI elements
    EditText originalPrice, discountAmt;
    Button button;
    TextView discountedPrice, saveAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount);

        // Initialize UI elements
        originalPrice = findViewById(R.id.originalPrice);
        discountAmt = findViewById(R.id.discountAmt);
        button = findViewById(R.id.discountBtn);
        discountedPrice = findViewById(R.id.disountedPrice);
        saveAmt = findViewById(R.id.saveAmt);

        // Set on click listener for the discount calculation button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values from EditText
                String strOriginalPrice = originalPrice.getText().toString();
                String strDiscountAmt = discountAmt.getText().toString();

                // Validate input values
                if (TextUtils.isEmpty(strOriginalPrice)) {
                    originalPrice.setError("Enter Value");
                    originalPrice.requestFocus();
                }
                else if (TextUtils.isEmpty(strDiscountAmt)) {
                    discountAmt.setError("Enter Value");
                    discountAmt.requestFocus();
                }
                else {
                    // Parse input values to double
                    double originalPriceValue = Double.parseDouble(strOriginalPrice);
                    double discountAmtValue = Double.parseDouble(strDiscountAmt);

                    // Calculate discounted price and amount saved
                    double discountedPriceValue = originalPriceValue - (originalPriceValue * discountAmtValue / 100);
                    double amountSaved = originalPriceValue - discountedPriceValue;

                    // Display the calculated values
                    discountedPrice.setText(String.format("%.2f", discountedPriceValue));
                    saveAmt.setText(String.format("%.2f", amountSaved));
                }
            }
        });
    }
}