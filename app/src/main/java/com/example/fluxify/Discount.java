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

    EditText originalPrice, discountAmt;
    Button button;
    TextView discountedPrice, saveAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount);

        originalPrice = findViewById(R.id.originalPrice);
        discountAmt = findViewById(R.id.discountAmt);
        button = findViewById(R.id.discountBtn);
        discountedPrice = findViewById(R.id.disountedPrice);
        saveAmt = findViewById(R.id.saveAmt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOriginalPrice = originalPrice.getText().toString();
                String strDiscountAmt = discountAmt.getText().toString();

                if (TextUtils.isEmpty(strOriginalPrice)) {
                    originalPrice.setError("Enter Value");
                    originalPrice.requestFocus();
                }
                else if (TextUtils.isEmpty(strDiscountAmt)) {
                    discountAmt.setError("Enter Value");
                    discountAmt.requestFocus();
                }
                else {
                    double originalPriceValue = Double.parseDouble(strOriginalPrice);
                    double discountAmtValue = Double.parseDouble(strDiscountAmt);
                    double discountedPriceValue = originalPriceValue - (originalPriceValue * discountAmtValue / 100);
                    double amountSaved = originalPriceValue - discountedPriceValue;

                    discountedPrice.setText(String.format("%.2f", discountedPriceValue));
                    saveAmt.setText(String.format("%.2f", amountSaved));
                }
            }
        });
    }
}